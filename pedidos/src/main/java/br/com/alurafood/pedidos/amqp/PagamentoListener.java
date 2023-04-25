package br.com.alurafood.pedidos.amqp;

import br.com.alurafood.pedidos.dto.PagamentoDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;
@Component
public class PagamentoListener {

    @RabbitListener(queues = "pagamentos.detalhes-pedido")
    public void recebeMensagem(PagamentoDto pagamentoDto){
        String message = """
                Dados do pagamento: %s
                Número do Pedido: %s
                Valor R$: %s
                Status: %s                
                """.formatted(pagamentoDto.getId(),pagamentoDto.getNumero(),pagamentoDto.getValor(),pagamentoDto.getStatus());
        System.out.println("Recebi a mensagem: "+ message);

    }
}
