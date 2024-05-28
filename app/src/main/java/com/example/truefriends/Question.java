package com.example.truefriends;

public class Question {

    private final int id;
    private final Category category;
    private final Difficulty difficulty;
    private final String question;

    public Question(int id, Category category, Difficulty difficulty, String question){
        this.id = id;
        this.category = category;
        this.question = question;
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty(){
        return difficulty;
    }
    public int getId() { return id; }
    public Category getCategory() {return category;}
    public String getQuestion() {return question;}

}
