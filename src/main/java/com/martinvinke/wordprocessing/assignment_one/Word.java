package com.martinvinke.wordprocessing.assignment_one;

public class Word implements WordFrequency, IncrementsFrequency {
	private String word;
	private int frequency;
	
	public Word(String word) {
		this.word = word;
		this.frequency = 0;
	}
	
	public Word(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}
	
	@Override
	public void incrementFrequency() {
		this.frequency++;
	}

	@Override
	public String getWord() {
		return word;
	}

	@Override
	public int getFrequency() {
		return frequency;
	}
}
