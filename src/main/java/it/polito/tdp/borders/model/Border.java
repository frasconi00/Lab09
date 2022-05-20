package it.polito.tdp.borders.model;

public class Border {
	
	private int state1no;
	private int state2no;
	private int year;
	
	public Border(int state1no, int state2no, int year) {
		this.state1no = state1no;
		this.state2no = state2no;
		this.year = year;
	}

	public int getState1no() {
		return state1no;
	}

	public void setState1no(int state1no) {
		this.state1no = state1no;
	}

	public int getState2no() {
		return state2no;
	}

	public void setState2no(int state2no) {
		this.state2no = state2no;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + state1no;
		result = prime * result + state2no;
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
		Border other = (Border) obj;
		if (state1no != other.state1no)
			return false;
		if (state2no != other.state2no)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Border [state1no=" + state1no + ", state2no=" + state2no + ", year=" + year + "]";
	}

}
