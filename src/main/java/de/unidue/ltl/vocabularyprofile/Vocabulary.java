package de.unidue.ltl.vocabularyprofile;

public class Vocabulary {
	protected String wordName;
	protected String wordType ;
	public Vocabulary(String wordName, String wordType) {
		super();
		this.wordName = wordName;
		this.wordType = wordType;
	}
	public String getWordType() {
		return wordName;
	}
	public void setWordType(String name) {
		this.wordName = name;
	}
	public String getLevel() {
		return wordType;
	}
	public void setLevel(String pOS) {
		this.wordType = pOS;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vocabulary vocabulary = (Vocabulary) o;
        return wordName.equals(vocabulary.wordName) &&
               wordType.equals(vocabulary.wordType);
    }
	
}
