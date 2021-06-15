package de.unidue.ltl.vocabularyprofile.io;

public class MewsItem {

	private String id;
	private String text;
	private int score;

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public int getScore() {
		return score;
	}

	public MewsItem(String id, String text, int score) {
		this.id = id;
		this.text = text;
		this.score = score;
	}

}
