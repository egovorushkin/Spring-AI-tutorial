package com.egovorushkin.spring_ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientCustomizer;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class SpringAiTutorialApplication {

    static void main(String[] args) {
        SpringApplication.run(SpringAiTutorialApplication.class, args);
    }

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.build();
    }

    @Bean
    ApplicationRunner go(ChatClient chatClient) {
        return _ -> {
            System.out.println("How can I help?\n");
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.print("> ");
                    System.out.println("\n - " +
                            chatClient.prompt(scanner.nextLine()).call().content());
                }
            }
        };
    }

    @Bean
    ChatClientCustomizer chatMemoryCustomizer() {
        return builder -> builder.defaultAdvisors(
                MessageChatMemoryAdvisor.builder(
                                MessageWindowChatMemory.builder()
                                        .maxMessages(500)
                                        .build())
                        .build());
    }

}
