package com.example.truefriends;

public class Question {

    private final int id;
    private final Category category;
    private final String question;
    private final int value;

    public Question(int id, Category category, String question, int value){
        this.id = id;
        this.category = category;
        this.question = question;
        this.value = value;
    }

    public int getId() { return id; }
    public Category getCategory() {return category;}
    public String getQuestion() {return question;}
    public int getValue() {return value;}

}
