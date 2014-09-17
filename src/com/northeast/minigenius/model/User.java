package com.northeast.minigenius.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.northeast.minigenius.exception.AmountNotAvailableException;
import com.northeast.minigenius.exception.ThemeNotFoundException;
import com.northeast.minigenius.model.theme.ThemeName;

/**
 * Class that represents the system's user.
 * 
 * @author Vladwoguer Bezerra
 * @author Pedro Henriques
 */
public class User {

	private String login;
	private Brain brain;
	List<Medal> brainMedals;

	public User(String login) {
		this.login = login;
		this.brain = new Brain();
		this.brainMedals = new ArrayList<Medal>();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Brain getBrain() {
		return brain;
	}

	public void setBrain(Brain brain) {
		this.brain = brain;
	}

	public void addBrainQuestionState(Question question,
			QuestionStatus questionStatus) {
		this.brain.addQuestionState(question, questionStatus);
	}

	public Map<Question, QuestionStatus> getBrainQuestions(ThemeName name)
			throws ThemeNotFoundException {
		return this.brain.getQuestions(name);
	}

	public void giveBrainMedal(Medal medal) {
		if (brainMedals.contains(medal)) {
			if (medal.getType().greaterThan(
					brainMedals.get(brainMedals.indexOf(medal)).getType())) {
				brainMedals.get(brainMedals.indexOf(medal)).setType(
						medal.getType());
			}
		} else {
			brainMedals.add(medal);
		}
	}

	public MedalType getBrainMedalType(ThemeName theme) {
		for (Medal medal : brainMedals) {
			if (medal.getTheme().equals(theme)) {
				return medal.getType();
			}
		}
		return MedalType.NONE;
	}

	public int getAmountOfKnowledgeByTheme(ThemeName themeName)
			throws ThemeNotFoundException {
		return brain.getAmountOfKnowledgeByTheme(themeName);
	}

}