package de.unidue.ltl.vocabularyprofile.io;

public class MewsItem {

	private String id;
	private String text;
	private float score;

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public float getScore() {
		return score;
	}

	public MewsItem(String id, String text, float score) {
		this.id = id;
		this.text = text;
		this.score = score;
	}

}
