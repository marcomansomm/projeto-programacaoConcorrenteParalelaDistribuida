package com.example.prodmessenger;

import java.time.LocalDateTime;
import java.util.Scanner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;


@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;

    public Runner(RabbitTemplate rabbitTemplate,
                  ConfigurableApplicationContext context) {
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner ler = new Scanner(System.in);

        while(true) {
            String[] msg = new String[5];
            LocalDateTime now = LocalDateTime.now();
            // Definir o formato desejado da string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            // Converter a data em uma string formatada
            String dataAtualFormatada = now.format(formatter);
            String setorDeVenda;

            System.out.println("Digite O item que você está precisando:");
            msg[0] = ler.nextLine();
            System.out.println("Qual o nome do seu mercado:");
            msg[1] = ler.nextLine();
            msg[2] = dataAtualFormatada;
            System.out.print("Digite a data (formato: dd/MM/yyyy): ");
            String dataEstimada = ler.nextLine();
            msg[3] = dataEstimada;
            System.out.println("Digite o Setor");
            msg[4] = ler.nextLine();

            if(msg[0].contains("sair"))
                break;


            rabbitTemplate.convertAndSend(ProdApplication.directExchangeName, ProdApplication.routingKey, msg);

        }
        context.close();
    }

}