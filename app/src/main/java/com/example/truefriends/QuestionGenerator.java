package com.example.truefriends;

import com.example.truefriends.Difficulty;
import com.example.truefriends.Question;
import com.example.truefriends.Category;

import java.util.ArrayList;
import java.util.List;

public class QuestionGenerator {

    public static List<Question> generateQuestions() {
        List<Question> questions = new ArrayList<>();

        // Generating questions for each category
        questions.addAll(generateQuestionsForCategory(Category.ANCIENT_HISTORY));
        questions.addAll(generateQuestionsForCategory(Category.NAMES));
        questions.addAll(generateQuestionsForCategory(Category.DATES));
        questions.addAll(generateQuestionsForCategory(Category.HOBBIES));
        questions.addAll(generateQuestionsForCategory(Category.TRUE_FRIEND));
        questions.addAll(generateQuestionsForCategory(Category.HOPES_DREAMS));
        questions.addAll(generateQuestionsForCategory(Category.GOSSIP));
        questions.addAll(generateQuestionsForCategory(Category.BINARY_QUESTIONS));
        questions.addAll(generateQuestionsForCategory(Category.TOP_5));

        return questions;
    }

    private static List<Question> generateQuestionsForCategory(Category category) {
        List<Question> questions = new ArrayList<>();

        // Adding Easy questions
        questions.add(new Question(1, category, Difficulty.EASY, "Easy question 1 for " + category));
        questions.add(new Question(2, category, Difficulty.EASY, "Easy question 2 for " + category));
        questions.add(new Question(3, category, Difficulty.EASY, "Easy question 3 for " + category));
        questions.add(new Question(4, category, Difficulty.EASY, "Easy question 4 for " + category));

        // Adding Medium questions
        questions.add(new Question(5, category, Difficulty.MEDIUM, "Medium question 1 for " + category));
        questions.add(new Question(6, category, Difficulty.MEDIUM, "Medium question 2 for " + category));
        questions.add(new Question(7, category, Difficulty.MEDIUM, "Medium question 3 for " + category));

        // Adding Hard questions
        questions.add(new Question(8, category, Difficulty.HARD, "Hard question 1 for " + category));
        questions.add(new Question(9, category, Difficulty.HARD, "Hard question 2 for " + category));
        questions.add(new Question(10, category, Difficulty.HARD, "Hard question 3 for " + category));

        return questions;
    }

    public static void main(String[] args) {
        List<Question> allQuestions = generateQuestions();

        for (Question question : allQuestions) {
            System.out.println("ID: " + question.getId() + ", Category: " + question.getCategory() +
                    ", Difficulty: " + question.getDifficulty() + ", Question: " + question.getQuestion());
        }
    }
}
