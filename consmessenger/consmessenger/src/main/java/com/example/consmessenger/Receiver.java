package com.example.consmessenger;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Receiver {

    public void receiveMessage(String[] message) {
        System.out.println("[ "+ message[2] +" ] [ "+message[0]+" ] : ( "+ message[4]+" ) precisamos de [ " + message[1] + " ] para [ "+ message[3]+ " ]");
    }


}