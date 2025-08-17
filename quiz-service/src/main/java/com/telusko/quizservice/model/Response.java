package com.telusko.quizservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Integer id;
    private String response;
    
    // Explicit getters and setters for JSON serialization
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }
}
