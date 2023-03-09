package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.dto.PagamentoDto;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.model.Status;
import br.com.alurafood.pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PagamentoDto> findAll(Pageable paginacao){
        return pagamentoRepository.findAll(paginacao).map(p-> modelMapper.map(p,PagamentoDto.class));
    }

    public PagamentoDto getById(Long id){
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());

        return modelMapper.map(pagamento,PagamentoDto.class);
    }

    public PagamentoDto savePayment(PagamentoDto dto){
        Pagamento pagamento = modelMapper.map(dto,Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        pagamentoRepository.save(pagamento);
        return modelMapper.map(pagamento,PagamentoDto.class);
    }

    public PagamentoDto updatePayment(Long id, PagamentoDto dto){
        Pagamento pagamento = modelMapper.map(dto,Pagamento.class);
        pagamento.setId(id);
        pagamentoRepository.save(pagamento);
        return modelMapper.map(pagamento,PagamentoDto.class);
    }
    public void deletePayment(Long id){
        pagamentoRepository.deleteById(id);
    }
}
