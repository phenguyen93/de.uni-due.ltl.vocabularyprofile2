package de.unidue.ltl.vocabularyprofile;

public class Vocabulary {
	protected String wordName;
	protected String wordType ;
	public Vocabulary(String wordName, String wordType) {
		super();
		this.wordName = wordName;
		this.wordType = wordType;
	}
	public String getWordName() {
		return wordName;
	}
	public void setWordName(String name) {
		this.wordName = name;
	}
	public String getWordType() {
		return wordType;
	}
	public void setWordType(String pOS) {
		this.wordType = pOS;
	}
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((wordName == null) ? 0 : wordName.hashCode());
	    result = prime * result + ((wordType == null) ? 0 : wordType.hashCode());
	    return result;
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
