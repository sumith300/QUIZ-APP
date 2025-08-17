package com.telusko.quizservice.controller;

import com.telusko.quizservice.model.QuizDto;
import com.telusko.quizservice.service.QuizService;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuizControllerTest {

    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuizController quizController;




    @Test
    void createQuiz() {
        QuizDto exceptedQuizDto=new QuizDto();
        exceptedQuizDto.setCategoryName("science");
        exceptedQuizDto.setNumQuestions(10);
        exceptedQuizDto.setTitle("test");

        quizController.createQuiz(exceptedQuizDto);

        assertNotNull(exceptedQuizDto);
        assertEquals(exceptedQuizDto.getCategoryName(),"science");
        assertEquals(exceptedQuizDto.getNumQuestions(),10);
        assertEquals(exceptedQuizDto.getTitle(),"test");


    }
    
}