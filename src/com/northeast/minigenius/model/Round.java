package com.northeast.minigenius.model;

import java.util.ArrayList;
import java.util.List;

import com.northeast.minigenius.exception.AmountNotAvailableException;
import com.northeast.minigenius.exception.ThemeNotFoundException;
import com.northeast.minigenius.model.theme.Theme;
import com.northeast.minigenius.model.theme.ThemeName;

public class Round {

	public final static int NUMBER_OF_QUESTIONS_BY_ROUND = 10;

	private List<Question> questions;
	private int questionIndex;
	private int numberOfCorrects;
	private ThemeName currentTheme;

	public Round(ThemeName themeName, int amountOfQuestions)
			throws ThemeNotFoundException, AmountNotAvailableException {
		questions = new ArrayList<Question>();
		questionIndex = 0;
		numberOfCorrects = 0;
		currentTheme = themeName;

		Theme theme = new Theme(themeName);
		questions = theme.getQuestions(amountOfQuestions);
	}

	public Question getCurrentQuestion() {
		return questions.get(questionIndex);
	}

	public boolean answerQuestion(int reply) {
		if (reply == getCurrentQuestion().getCorrect()) {
			numberOfCorrects++;
			return true;
		}
		return false;
	}

	public void nextQuestion() {
		if (hasNextQuestion()) {
			questionIndex++;
		}
	}

	public int getNumberOfQuestions() {
		return NUMBER_OF_QUESTIONS_BY_ROUND;
	}

	public int getNumberOfCorrectQuestions() {
		return numberOfCorrects;
	}

	public int getQuestionIndex() {
		return questionIndex;
	}

	public void setQuestionIndex(int questionIndex) {
		this.questionIndex = questionIndex;
	}

	public boolean hasNextQuestion() {
		if (questionIndex < NUMBER_OF_QUESTIONS_BY_ROUND) {
			return true;
		}
		return false;
	}

	public ThemeName getCurrentTheme() {
		return currentTheme;
	}

	public void setCurrentTheme(ThemeName currentTheme) {
		this.currentTheme = currentTheme;
	}

}
