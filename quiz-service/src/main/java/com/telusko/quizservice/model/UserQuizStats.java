package com.telusko.quizservice.model;

public class UserQuizStats {
    private String userId;
    private Long totalQuizzesTaken;
    private Double averageScore;
    private Double bestScore;
    private Integer totalCorrectAnswers;
    private Integer totalQuestions;
    
    public UserQuizStats() {}
    
    public UserQuizStats(String userId, Long totalQuizzesTaken, Double averageScore, Double bestScore) {
        this.userId = userId;
        this.totalQuizzesTaken = totalQuizzesTaken;
        this.averageScore = averageScore;
        this.bestScore = bestScore;
    }
    
    // Getters and Setters
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public Long getTotalQuizzesTaken() {
        return totalQuizzesTaken;
    }
    
    public void setTotalQuizzesTaken(Long totalQuizzesTaken) {
        this.totalQuizzesTaken = totalQuizzesTaken;
    }
    
    public Double getAverageScore() {
        return averageScore;
    }
    
    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }
    
    public Double getBestScore() {
        return bestScore;
    }
    
    public void setBestScore(Double bestScore) {
        this.bestScore = bestScore;
    }
    
    public Integer getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }
    
    public void setTotalCorrectAnswers(Integer totalCorrectAnswers) {
        this.totalCorrectAnswers = totalCorrectAnswers;
    }
    
    public Integer getTotalQuestions() {
        return totalQuestions;
    }
    
    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}
