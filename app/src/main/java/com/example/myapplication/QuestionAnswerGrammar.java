package com.example.myapplication;

public class QuestionAnswerGrammar {

    public static String question[] ={
            "Aaron and Milan are friends and _______ of them go to the same school. They are classmates, too",
            "A: There are four radios in the attic.\n" +
                    "\n" +
                    "B: Yes, but _______ of them work. They are _______ broken.",
            "A: I have got two dictionaries, but _______ of them include the meaning of that word in it.\n" +
                    "\n" +
                    "B: You can look it up in an online dictionary then.?",
            "Hans and Klaus are from Germany, and _______ them are from the city of Frankfurt."
    };

    public static String choices[][] = {
            {"none", "both", "all", "together"},
            {"neither / none","all / both","both / neither","none / all"},
            {"none","both","neither","all"},
            {"both of","all","none of","neither"}
    };

    public static String correctAnswers[] = {
            "both",
            "none / all",
            "neither",
            "both of"
    };

}