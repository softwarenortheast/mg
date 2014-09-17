package com.northeast.minigenius.model;

public enum MedalType {

	NONE(0), NEWBIE(1), STUDENT(2), EXPERT(3), GRADUATE(4), MASTER(5), GENIUS(6);

	private int valor;

	private MedalType(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public boolean greaterThan(MedalType type) {
		if (this.valor > type.getValor()) {
			return true;
		}
		return false;
	}

}
