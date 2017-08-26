package net.alkaonline.alkatutorial.tutorialstr;

public class Page {

	private String[] str;
	private int firstblank;

	public Page(int first, String... messages) {
		str = messages;
		firstblank = first;
	}

	public String[] getString() {
		return str;
	}

	public int getFirst() {
		return firstblank;
	}
}
