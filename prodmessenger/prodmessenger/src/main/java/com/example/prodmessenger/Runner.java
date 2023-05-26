package com.example.prodmessenger;

import java.time.LocalDate;
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
            System.out.println("Digite a mensagem:");
            String[] msg = new String[4];
            LocalDateTime now = LocalDateTime.now();
            // Definir o formato desejado da string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            // Converter a data em uma string formatada
            String dataFormatada = now.format(formatter);

            msg[0] = ler.nextLine();
            msg[1] = ler.nextLine();
            msg[2] = dataFormatada;
            System.out.print("Digite a data (formato: dd/MM/yyyy): ");
            String dataEstimada = ler.nextLine();
            msg[3] = dataEstimada;

            if(msg[0].contains("sair"))
                break;


            rabbitTemplate.convertAndSend(ProdApplication.directExchangeName, ProdApplication.routingKey, msg);

        }
        context.close();
    }

}