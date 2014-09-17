package com.northeast.minigenius.model;

import java.util.List;

import com.northeast.minigenius.exception.ThemeNotFoundException;
import com.northeast.minigenius.model.theme.ThemeName;

public interface QuestionService {
	public List<Question> getQuestions(ThemeName themeName)
			throws ThemeNotFoundException;

}
