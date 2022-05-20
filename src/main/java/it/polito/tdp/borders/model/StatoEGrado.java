package it.polito.tdp.borders.model;

public class StatoEGrado {
	
	private Country country;
	private int grado;
	
	public StatoEGrado(Country country, int grado) {
		this.country = country;
		this.grado = grado;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getGrado() {
		return grado;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}

	@Override
	public String toString() {
		return "StatoEGrado [country=" + country + ", grado=" + grado + "]";
	}

}
