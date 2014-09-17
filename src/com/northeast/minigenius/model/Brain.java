package com.northeast.minigenius.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.northeast.minigenius.exception.AmountNotAvailableException;
import com.northeast.minigenius.exception.ThemeNotFoundException;
import com.northeast.minigenius.interfaceTest.QuestionBeanTest;
import com.northeast.minigenius.model.theme.ThemeName;

public class Brain {

	private Map<Long, QuestionStatus> questionStates;
	QuestionService questionService;

	public Brain() {
		questionStates = new TreeMap<Long, QuestionStatus>();
		questionService = new QuestionBeanTest();
	}

	// FIXME SOLUCAO FEIA DE COMO SABER QUANTIDADE QUE O CARA SABE
	// ISSO DEVIDO A FORMA QUE PENSAMOS EM FAZER A USANDO MAPA
	// ACHO QUE VAI SER MELHOR CRIAR UMA CLASSE PRA DEFINIR UMA QUESTAO E SEU
	// ESTADO QUE TERIA OS ATRIBUTOS, ID DA QUESTAO, O TEMA E O STATUS
	public int getAmountOfKnowledgeByTheme(ThemeName themeName)
			throws ThemeNotFoundException {
		int amount = 0;
		List<Question> questions = questionService.getQuestions(themeName);
		for (Question question : questions) {
			if (questionStates.keySet().contains(question.getId())) {
				if (QuestionStatus.LEARNED.equals(questionStates.get(question
						.getId()))) {
					amount++;
				}
			}
		}
		return amount;
	}

	public void addQuestionState(Question question,
			QuestionStatus questionStatus) {
		if (!questionStates.containsKey(question.getId())) {
			questionStates.put(question.getId(), questionStatus);
		} else {
			if (QuestionStatus.LEARNED.equals(questionStatus)) {
				questionStates.remove(question.getId());
				questionStates.put(question.getId(), questionStatus);
			}
		}
	}

	public Map<Question, QuestionStatus> getQuestions(ThemeName name)
			throws ThemeNotFoundException {
		List<Question> questions = questionService.getQuestions(name);
		Map<Question, QuestionStatus> result = new TreeMap<Question, QuestionStatus>();
		for (Question question : questions) {
			result.put(question, questionStates.get(question.getId()));
		}
		return result;
	}

}
