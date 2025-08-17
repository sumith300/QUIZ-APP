package com.telusko.quizservice.controller;

import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Quiz;
import com.telusko.quizservice.model.QuizDto;
import com.telusko.quizservice.model.QuizResult;
import com.telusko.quizservice.model.Response;
import com.telusko.quizservice.model.UserQuizStats;
import com.telusko.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@CrossOrigin(origins = "*")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
    }

    @GetMapping("get")
    public ResponseEntity<List<Quiz>> getAllQuizzes(){
        return quizService.getAllQuizzes();
    }

    @PostMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

    @PostMapping("submit/{id}/user/{userId}")
    public ResponseEntity<Integer> submitQuizWithUser(@PathVariable Integer id, @PathVariable String userId, @RequestBody List<Response> responses){
        return quizService.calculateResultWithUser(id, userId, responses);
    }

    @GetMapping("stats/{userId}")
    public ResponseEntity<UserQuizStats> getUserQuizStats(@PathVariable String userId){
        return quizService.getUserQuizStats(userId);
    }

    @GetMapping("history/{userId}")
    public ResponseEntity<List<QuizResult>> getUserQuizHistory(@PathVariable String userId){
        return quizService.getUserQuizHistory(userId);
    }

    @GetMapping("recent/{userId}")
    public ResponseEntity<List<QuizResult>> getRecentQuizResults(@PathVariable String userId){
        return quizService.getRecentQuizResults(userId);
    }

    // For demo purposes - get stats for default user
    @GetMapping("stats")
    public ResponseEntity<UserQuizStats> getDefaultUserStats(){
        return quizService.getUserQuizStats("default_user");
    }

    @GetMapping("history")
    public ResponseEntity<List<QuizResult>> getDefaultUserHistory(){
        return quizService.getUserQuizHistory("default_user");
    }


}
