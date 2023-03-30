package com.english.learneasy;

import java.util.List;

public class GrammarQuizModel {
    public String question;
    public List<String> choices;
    public String answer;
    public String type;

    public GrammarQuizModel(String question, List<String> choices, String answer, String type) {

        this.question = question;
        this.choices = choices;
        this.answer = answer;
        this.type = type;

    }
}
