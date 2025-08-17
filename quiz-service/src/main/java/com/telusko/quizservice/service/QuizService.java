package com.telusko.quizservice.service;

import com.telusko.quizservice.dao.QuizDao;
import com.telusko.quizservice.dao.QuizResultDao;
import com.telusko.quizservice.feign.QuizInterface;
import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Quiz;
import com.telusko.quizservice.model.QuizResult;
import com.telusko.quizservice.model.Response;
import com.telusko.quizservice.model.UserQuizStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    @Autowired
    QuizResultDao quizResultDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
          Quiz quiz = quizDao.findById(id).get();
          List<Integer> questionIds = quiz.getQuestionIds();
          ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
          return questions;

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> scoreResponse = quizInterface.getScore(responses);
        Integer score = scoreResponse.getBody();
        
        // Get quiz details for saving result
        Optional<Quiz> quizOptional = quizDao.findById(id);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            
            // For now, using a default user ID. In a real app, this would come from authentication
            String userId = "default_user"; // TODO: Get from authentication context
            
            // Calculate total questions
            int totalQuestions = quiz.getQuestionIds().size();
            
            // Save quiz result
            QuizResult quizResult = new QuizResult(id, quiz.getTitle(), userId, score, totalQuestions);
            quizResultDao.save(quizResult);
        }
        
        return scoreResponse;
    }

    public ResponseEntity<Integer> calculateResultWithUser(Integer id, String userId, List<Response> responses) {
        ResponseEntity<Integer> scoreResponse = quizInterface.getScore(responses);
        Integer score = scoreResponse.getBody();
        
        // Get quiz details for saving result
        Optional<Quiz> quizOptional = quizDao.findById(id);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            
            // Calculate total questions
            int totalQuestions = quiz.getQuestionIds().size();
            
            // Save quiz result with specific user ID
            QuizResult quizResult = new QuizResult(id, quiz.getTitle(), userId, score, totalQuestions);
            quizResultDao.save(quizResult);
        }
        
        return scoreResponse;
    }

    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizDao.findAll();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }
    
    // Get user quiz statistics
    public ResponseEntity<UserQuizStats> getUserQuizStats(String userId) {
        try {
            Long totalQuizzesTaken = quizResultDao.countQuizzesTakenByUserId(userId);
            Double averageScore = quizResultDao.findAveragePercentageByUserId(userId);
            Double bestScore = quizResultDao.findBestPercentageByUserId(userId);
            
            // Handle null values
            averageScore = (averageScore != null) ? averageScore : 0.0;
            bestScore = (bestScore != null) ? bestScore : 0.0;
            totalQuizzesTaken = (totalQuizzesTaken != null) ? totalQuizzesTaken : 0L;
            
            UserQuizStats stats = new UserQuizStats(userId, totalQuizzesTaken, averageScore, bestScore);
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Get user quiz history
    public ResponseEntity<List<QuizResult>> getUserQuizHistory(String userId) {
        try {
            List<QuizResult> quizResults = quizResultDao.findByUserIdOrderByCompletedAtDesc(userId);
            return new ResponseEntity<>(quizResults, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Get recent quiz results (last 10)
    public ResponseEntity<List<QuizResult>> getRecentQuizResults(String userId) {
        try {
            List<QuizResult> recentResults = quizResultDao.findRecentQuizResultsByUserId(userId);
            // Limit to 10 results
            if (recentResults.size() > 10) {
                recentResults = recentResults.subList(0, 10);
            }
            return new ResponseEntity<>(recentResults, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
