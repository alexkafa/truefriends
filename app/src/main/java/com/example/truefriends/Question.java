package com.example.truefriends;

public class Question {

    private final String category;
    private final String question;
    private final int value;

    public Question(String category, String question, int value){
        this.category = category;
        this.question = question;
        this.value = value;
    }

    public String getCategory() {return category;}
    public String getQuestion() {return question;}
    public int getValue() {return value;}

}
