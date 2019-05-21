package com.dextroxd.hackeroyalefree.Models;

public class QuestionModel
{
    private String Question;
    private String Option1;
    private String Option2;
    private String Option3;
    private String Option4;
    private String Correct;

    public QuestionModel(String question, String option1, String option2, String option3, String option4, String correct) {
        Question = question;
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Option4 = option4;
        Correct = correct;
    }

    public String getQuestion() {
        return Question;
    }

    public String getOption1() {
        return Option1;
    }

    public String getOption2() {
        return Option2;
    }

    public String getOption3() {
        return Option3;
    }

    public String getOption4() {
        return Option4;
    }

    public String getCorrect() {
        return Correct;
    }
}
