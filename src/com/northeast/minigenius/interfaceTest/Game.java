package com.northeast.minigenius.interfaceTest;

import java.util.Map;
import java.util.Scanner;

import com.northeast.minigenius.exception.AmountNotAvailableException;
import com.northeast.minigenius.exception.ThemeNotFoundException;
import com.northeast.minigenius.model.Manager;
import com.northeast.minigenius.model.MedalType;
import com.northeast.minigenius.model.Question;
import com.northeast.minigenius.model.QuestionStatus;
import com.northeast.minigenius.model.Round;
import com.northeast.minigenius.model.UserService;
import com.northeast.minigenius.model.theme.ThemeName;

public class Game {

	private static Scanner sc;

	public static void main(String[] args) {

		sc = new Scanner(System.in);

		UserService userService = new UserBean(); // TODO Vladwoguer Bezerra May
													// 2014 MOCK
		Manager manager = new Manager(userService);

		System.out.println("Digite o seu login");

		manager.updateUser(sc.nextLine());// TODO Vladwoguer Bezerra May 2014
											// MOCK
		boolean run = false;

		System.out.println("OLÁ " + manager.getUserLogin()
				+ ", BEM VINDO AO MINIGENIUS!");
		System.out.println("SELECIONE A OPCAO");
		System.out.println("1 - JOGAR");
		System.out.println("2 - VER CEREBRO");
		System.out.println("OUTRO - SAIR");

		int op = sc.nextInt();

		if (op == 1 || op == 2) {
			run = true;
		}

		while (run) {

			System.out.println("ESCOLHA O TEMA DE QUESTOES");
			System.out.println("1 - TESTE");

			int op2 = sc.nextInt();
			System.out.println(op2);
			ThemeName tema = null;
			if (op2 == 1) {
				tema = ThemeName.TEST;
			}

			if (op == 1) {
				try {
					manager.startNewRound(tema);
				} catch (ThemeNotFoundException e) {
					System.out.println("O TEMA ESCOLHIDO NAO EXISTE");
					manager.finishRound();
				}

				if (manager.hasActiveRound()) {
					while (manager.roundHasNextQuestion()) {
						Question question = manager.getCurrentQuestion();
						System.out.println("QUESTAO "
								+ manager.roundNumberOfQuestion() + " DE "
								+ Round.NUMBER_OF_QUESTIONS_BY_ROUND);
						System.out.println(question.getEnunciation());
						for (int i = 0; i < question.getResponses().length; i++) {
							System.out.println(i + " - "
									+ question.getResponses()[i]);
						}

						System.out.println("QUAL A RESPOSTA CERTA?");
						int resposta = sc.nextInt();

						boolean resultado = manager
								.answerCurrentQuestion(resposta);
						if (resultado) {
							System.out.println("ACERTOU!!!!");
						} else {
							System.out.println("ERROU!!!!");
						}

						manager.nextQuestion();

					}

					System.out.println("VOCE ACERTOU "
							+ manager.getNumberOfCorrectQuestions()
							+ " PERGUNTAS.");
					MedalType resultBrain = null;
					try {
						resultBrain = manager.userBrainAwards();
					} catch (ThemeNotFoundException e) {
						e.printStackTrace();
					}
					if (!MedalType.NONE.equals(resultBrain)) {
						System.out.println("PARABÉNS! VOCÊ GANHOU O TITULO DE "
								+ getMedalha(resultBrain));
					}
				}
			} else if (op == 2) {
				try {
					Map<Question, QuestionStatus> questions = manager
							.getBrainQuestions(ThemeName.TEST);
					for (Question question : questions.keySet()) {
						if (questions.get(question) != null) {
							System.out
									.println("--------------------------------------");
							System.out.println(question.getId() + ": "
									+ question.getEnunciation());
							System.out.println("Resposta: "
									+ question.getResponses()[question
											.getCorrect()]);
							if (QuestionStatus.LEARNED.equals(questions
									.get(question))) {
								System.out.println("APRENDIDA");
							}
						} else {
							System.out
									.println("--------------------------------------");
							System.out.println(question.getId() + " ????? ");
						}
					}
				} catch (ThemeNotFoundException e) {
					e.printStackTrace();
				}
			}

			System.out.println("DESEJA CONTINUAR JOGANDO?");
			System.out.println("1 - SIM");
			System.out.println("2 - VER CEREBRO");
			System.out.println("OUTRO - NAO");

			op = sc.nextInt();

			if (op != 1 && op != 2) {
				run = false;
			}

		}

	}

	private static String getMedalha(MedalType mt) {
		if (MedalType.GENIUS.equals(mt)) {
			return "GENIO";
		} else if (MedalType.MASTER.equals(mt)) {
			return "MESTRE";
		} else if (MedalType.GRADUATE.equals(mt)) {
			return "GRADUADO";
		}
		return "SEM CLASSIFICAÇÃO";
	}
}
