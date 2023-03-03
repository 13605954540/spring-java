package org.example.config;

import org.springframework.stereotype.Component;

@Component
public class MessageReceive {

    public void receiveMessage(String message) {
        System.err.println("receive: " + message);
    }
}
