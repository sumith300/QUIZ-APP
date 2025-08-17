package com.telusko.quizservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_results")
public class QuizResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "quiz_id")
    private Integer quizId;
    
    @Column(name = "quiz_title")
    private String quizTitle;
    
    @Column(name = "user_id")
    private String userId; // For now using String, can be changed to Integer if needed
    
    @Column(name = "score")
    private Integer score;
    
    @Column(name = "total_questions")
    private Integer totalQuestions;
    
    @Column(name = "percentage")
    private Double percentage;
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    
    public QuizResult() {
        this.completedAt = LocalDateTime.now();
    }
    
    public QuizResult(Integer quizId, String quizTitle, String userId, Integer score, Integer totalQuestions) {
        this.quizId = quizId;
        this.quizTitle = quizTitle;
        this.userId = userId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.percentage = totalQuestions > 0 ? (double) score / totalQuestions * 100 : 0.0;
        this.completedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getQuizId() {
        return quizId;
    }
    
    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }
    
    public String getQuizTitle() {
        return quizTitle;
    }
    
    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public Integer getScore() {
        return score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
        if (this.totalQuestions != null && this.totalQuestions > 0) {
            this.percentage = (double) score / this.totalQuestions * 100;
        }
    }
    
    public Integer getTotalQuestions() {
        return totalQuestions;
    }
    
    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
        if (this.score != null && totalQuestions > 0) {
            this.percentage = (double) this.score / totalQuestions * 100;
        }
    }
    
    public Double getPercentage() {
        return percentage;
    }
    
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
    
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
    
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
