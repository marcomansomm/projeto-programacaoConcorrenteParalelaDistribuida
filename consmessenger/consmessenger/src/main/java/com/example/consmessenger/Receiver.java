package com.example.consmessenger;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Receiver {

    public void receiveMessage(String[] message) {
        System.out.println("[ "+ message[2] +" ] [ "+message[1]+" ] : ( "+ message[4]+" ) precisamos de [ " + message[0] + " ] para [ "+ message[3]+ " ]");
    }


}