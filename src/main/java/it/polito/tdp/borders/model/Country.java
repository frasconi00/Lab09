package it.polito.tdp.borders.model;

public class Country {
	
	private int CCode;
	private String StateNme;

	public Country(int cCode, String StateNme) {
		CCode = cCode;
		this.StateNme = StateNme;
	}

	public String getStateNme() {
		return StateNme;
	}

	public void setStateNme(String stateNme) {
		StateNme = stateNme;
	}

	public int getCCode() {
		return CCode;
	}

	public void setCCode(int cCode) {
		CCode = cCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CCode;
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
		Country other = (Country) obj;
		if (CCode != other.CCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [CCode=" + CCode + ", StateNme=" + StateNme + "]";
	}

}
