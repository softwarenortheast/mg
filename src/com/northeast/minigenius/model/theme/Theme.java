package com.northeast.minigenius.model.theme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.northeast.minigenius.exception.AmountNotAvailableException;
import com.northeast.minigenius.exception.ThemeNotFoundException;
import com.northeast.minigenius.interfaceTest.QuestionBeanTest;
import com.northeast.minigenius.model.Question;
import com.northeast.minigenius.model.QuestionService;

public class Theme {

	private ThemeName name;
	private QuestionService questionService;

	public Theme(ThemeName name) {
		this.name = name;
		this.questionService = new QuestionBeanTest();
	}

	public List<Question> getQuestions(int amount)
			throws AmountNotAvailableException, ThemeNotFoundException {
		List<Question> questions = questionService.getQuestions(name);
		checkAmount(amount, questions);
		return randomizeAmountQuestions(amount, questions);
	}

	private List<Question> randomizeAmountQuestions(int amount,
			List<Question> questions) {
		Random random = new Random();
		List<Question> ret = new ArrayList<Question>();
		for (int i = 0; i < amount; i++) {
			Question question = questions.get(random.nextInt(questions.size()));
			questions.remove(question);
			ret.add(question);
		}

		return ret;
	}

	private void checkAmount(int amount, List<Question> questions)
			throws AmountNotAvailableException {
		if (amount > questions.size()) {
			throw new AmountNotAvailableException();
		}
	}

	public ThemeName getThemeName() {
		return name;
	}

	public ThemeName getName() {
		return name;
	}

	public void setName(ThemeName name) {
		this.name = name;
	}

}
