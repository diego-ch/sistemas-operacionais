package PageRep;

public class Frame {

	int id;
	int age;

	public Frame(int id, int time) {
		this.id		= id;
		this.age	= time;
	}

	public String toString() {
		return Integer.toString(this.id);
	}

}