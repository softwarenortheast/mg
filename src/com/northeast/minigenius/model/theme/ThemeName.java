package com.northeast.minigenius.model.theme;

public enum ThemeName {
	
	TEST("Test");
	
	private String themeName;
	
	private ThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getThemeName() {
		return themeName;
	}
}
