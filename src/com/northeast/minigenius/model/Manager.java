package com.northeast.minigenius.model;

import java.util.Map;

import com.northeast.minigenius.exception.AmountNotAvailableException;
import com.northeast.minigenius.exception.ThemeNotFoundException;
import com.northeast.minigenius.model.theme.ThemeName;

public class Manager {

	private Round currentRound;
	private final double AMOUNT_FOR_NEWBIE = 0.7 * Round.NUMBER_OF_QUESTIONS_BY_ROUND;
	private final double AMOUNT_FOR_STUDENT = 0.85 * Round.NUMBER_OF_QUESTIONS_BY_ROUND;
	private final int AMOUNT_TO_GENIUS = 100;
	private final double AMOUNT_FOR_MASTER = 50;
	private final double AMOUNT_FOR_GRADUATE = 20;

	// esse atributo estara aqui temporariamente.
	// Quando criar o usuario ele deve ir para la

	private User user;
	private UserService usersRetriever;

	public Manager(UserService usersRetriver) {
		this.usersRetriever = usersRetriver;
	}

	/**
	 * Updates the user of manager.
	 * 
	 * @param login
	 *            of the user
	 * @return true if sucess
	 */
	public boolean updateUser(String login) {
		try {
			user = usersRetriever.retrieveUserByLogin(login);
			return true;
		} catch (UserNotFoundException unfe) {
			return false;
		}
	}

	public void startNewRound(ThemeName theme) throws ThemeNotFoundException {
		try {
			currentRound = new Round(theme, Round.NUMBER_OF_QUESTIONS_BY_ROUND);
		} catch (AmountNotAvailableException e) {
			e.printStackTrace();
		}
	}

	public void finishRound() {
		currentRound = null;
	}

	public boolean hasActiveRound() {
		return currentRound != null;
	}

	public Round getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(Round currentRound) {
		this.currentRound = currentRound;
	}

	public Question getCurrentQuestion() {
		return this.currentRound.getCurrentQuestion();
	}

	public boolean roundHasNextQuestion() {
		return currentRound.hasNextQuestion();
	}

	public int roundNumberOfQuestion() {
		return currentRound.getQuestionIndex() + 1;
	}

	public void nextQuestion() {
		currentRound.nextQuestion();
	}

	public boolean answerCurrentQuestion(int reply) {
		boolean ret = currentRound.answerQuestion(reply);
		if (ret) {
			user.addBrainQuestionState(currentRound.getCurrentQuestion(),
					QuestionStatus.LEARNED);
		} else {
			user.addBrainQuestionState(currentRound.getCurrentQuestion(),
					QuestionStatus.SEEN);
		}
		return ret;
	}

	public int getNumberOfCorrectQuestions() {
		return currentRound.getNumberOfCorrectQuestions();
	}

	public Map<Question, QuestionStatus> getBrainQuestions(ThemeName themeName)
			throws ThemeNotFoundException {
		return user.getBrainQuestions(themeName);
	}

	public User getUser() {
		return this.user;
	}

	public String getUserLogin() {
		return this.user.getLogin();
	}

	public MedalType userBrainAwards() throws ThemeNotFoundException {
		if (!currentRound.hasNextQuestion()) {
			int amountOfKnowledge = user
					.getAmountOfKnowledgeByTheme(currentRound.getCurrentTheme());
			Medal award = null;
			if (amountOfKnowledge == AMOUNT_TO_GENIUS) {
				award = new Medal(MedalType.GENIUS,
						currentRound.getCurrentTheme());
			} else if (amountOfKnowledge >= AMOUNT_FOR_MASTER) {
				award = new Medal(MedalType.MASTER,
						currentRound.getCurrentTheme());
			} else if (amountOfKnowledge >= AMOUNT_FOR_GRADUATE) {
				award = new Medal(MedalType.GRADUATE,
						currentRound.getCurrentTheme());
			}
			if (award != null) {
				user.giveBrainMedal(award);
				return award.getType();
			}
		}
		return MedalType.NONE;
	}

	public MedalType getUserBrainMedalType(ThemeName theme) {
		return this.user.getBrainMedalType(theme);
	}
}
