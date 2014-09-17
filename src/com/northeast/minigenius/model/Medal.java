package com.northeast.minigenius.model;

import com.northeast.minigenius.model.theme.ThemeName;

/**
 * @author Pedro Henriques
 */
public class Medal {

	MedalType type;
	ThemeName theme;

	public Medal(MedalType type, ThemeName theme) {
		this.type = type;
		this.theme = theme;
	}

	public MedalType getType() {
		return type;
	}

	public void setType(MedalType type) {
		this.type = type;
	}

	public ThemeName getTheme() {
		return theme;
	}

	public void setTheme(ThemeName theme) {
		this.theme = theme;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Medal other = (Medal) obj;
		if (theme != other.theme)
			return false;
		return true;
	}
}
