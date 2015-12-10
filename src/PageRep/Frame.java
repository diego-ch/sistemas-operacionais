package PageRep;

public class Frame {

	int id;
	int age;
	int flag;

	public Frame(int id, int time) {
		this.id		= id;
		this.age	= time;
		this.flag	= -1;
	}

	public String toString() {
		return Integer.toString(this.id);
	}
}