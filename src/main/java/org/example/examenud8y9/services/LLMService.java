package org.example.examenud8y9.services;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;

@Service
class LLMService {
    private final OllamaChatModel chatModel;

    public LLMService(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String generateHistory(String seed){
        var response = chatModel.call(
                new Prompt(
                        "",
                        OllamaChatOptions.builder()
                                .model("llama3")
                                .temperature(1.0)
                                .build()
                ));
        return  response.getResult().getOutput().getText();
    }
}
