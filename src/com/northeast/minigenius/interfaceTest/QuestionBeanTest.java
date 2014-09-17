package com.northeast.minigenius.interfaceTest;

import java.util.ArrayList;
import java.util.List;

import com.northeast.minigenius.exception.AmountNotAvailableException;
import com.northeast.minigenius.exception.ThemeNotFoundException;
import com.northeast.minigenius.model.Question;
import com.northeast.minigenius.model.QuestionService;
import com.northeast.minigenius.model.theme.ThemeName;

public class QuestionBeanTest implements QuestionService {

	private List<Question> allTestQuestions;

	public QuestionBeanTest() {
		allTestQuestions = new ArrayList<Question>();
		generateAllQuestions();
	}

	@Override
	public List<Question> getQuestions(ThemeName themeName)
			throws ThemeNotFoundException {
		if (themeName == null) {
			throw new ThemeNotFoundException();
		}

		return allTestQuestions;

	}

	private void generateAllQuestions() {
		long id = 1;

		for (int i = 0; i < 100; i++) {
			String[] alternativas1 = new String[4];
			alternativas1[0] = "CORRETA";
			alternativas1[1] = "ERRADA";
			alternativas1[2] = "ERRADA";
			alternativas1[3] = "ERRADA";
			int correta = 0;
			allTestQuestions.add(new Question(id, "PERGUNTA TESTE",
					alternativas1, correta, ThemeName.TEST.getThemeName()));
			id++;
		}

	}

}
