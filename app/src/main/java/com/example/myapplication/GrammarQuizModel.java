package com.example.myapplication;

import java.util.List;

public class GrammarQuizModel {
    public String question;
    public List<String> choices;
    public String answer;

    public GrammarQuizModel(String question, List<String> choices, String answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }
}
