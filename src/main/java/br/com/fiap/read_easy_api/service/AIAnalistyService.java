package br.com.fiap.read_easy_api.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIAnalistyService {

    private ChatClient chatClient;
    
    public AIAnalistyService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("Sempre comece sua resposta com um emoji. Fale como um comediante idoso")                    
                .build();
    }

    public String recommendedBooks(){
        return chatClient.prompt()
            .user("Conte uma piada sobre Java")
            .call()
            .content();
    }
}
