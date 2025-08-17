package com.telusko.quizservice.dao;

import com.telusko.quizservice.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultDao extends JpaRepository<QuizResult, Integer> {
    
    // Find all quiz results for a specific user
    List<QuizResult> findByUserIdOrderByCompletedAtDesc(String userId);
    
    // Find quiz results for a specific user and quiz
    List<QuizResult> findByUserIdAndQuizIdOrderByCompletedAtDesc(String userId, Integer quizId);
    
    // Calculate average score for a user
    @Query("SELECT AVG(qr.percentage) FROM QuizResult qr WHERE qr.userId = :userId")
    Double findAveragePercentageByUserId(@Param("userId") String userId);
    
    // Get total number of quizzes taken by user
    @Query("SELECT COUNT(qr) FROM QuizResult qr WHERE qr.userId = :userId")
    Long countQuizzesTakenByUserId(@Param("userId") String userId);
    
    // Get user's best score
    @Query("SELECT MAX(qr.percentage) FROM QuizResult qr WHERE qr.userId = :userId")
    Double findBestPercentageByUserId(@Param("userId") String userId);
    
    // Get recent quiz results (last 10)
    @Query("SELECT qr FROM QuizResult qr WHERE qr.userId = :userId ORDER BY qr.completedAt DESC")
    List<QuizResult> findRecentQuizResultsByUserId(@Param("userId") String userId);
}
