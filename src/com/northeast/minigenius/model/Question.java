package com.northeast.minigenius.model;

import java.util.Arrays;

public class Question implements Comparable<Question> {

	private Long id;

	private String enunciation;
	private String[] responses;
	private int correct;
	private String theme;

	public Question(long id, String enunciation, String[] responses,
			int correct, String theme) {
		this.id = id;
		this.enunciation = enunciation;
		this.responses = responses;
		this.correct = correct;
		this.theme = theme;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCorrect() {
		return correct;
	}

	public String getEnunciation() {
		return enunciation;
	}

	public void setEnunciation(String enunciation) {
		this.enunciation = enunciation;
	}

	public String[] getResponses() {
		return responses;
	}

	public void setResponses(String[] responses) {
		this.responses = responses;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + correct;
		result = prime * result
				+ ((enunciation == null) ? 0 : enunciation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(responses);
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (correct != other.correct)
			return false;
		if (enunciation == null) {
			if (other.enunciation != null)
				return false;
		} else if (!enunciation.equals(other.enunciation))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(responses, other.responses))
			return false;
		if (theme == null) {
			if (other.theme != null)
				return false;
		} else if (!theme.equals(other.theme))
			return false;
		return true;
	}

	@Override
	public int compareTo(Question another) {
		if (this.getId() < another.getId()) {
			return -1;
		} else if (this.getId() > another.getId()) {
			return 1;
		}
		return 0;
	}
}