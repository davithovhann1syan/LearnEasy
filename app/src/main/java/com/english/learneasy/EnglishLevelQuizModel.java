package com.english.learneasy;

import java.util.List;

public class EnglishLevelQuizModel {
    public String question;
    public List<String> choices;
    public String answer;

    public EnglishLevelQuizModel(String question, List<String> choices, String answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }
}