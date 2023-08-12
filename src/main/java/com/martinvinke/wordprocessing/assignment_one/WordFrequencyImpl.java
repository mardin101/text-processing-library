package com.martinvinke.wordprocessing.assignment_one;

public class WordFrequencyImpl implements WordFrequency {
	private int frequency;
	private String word;
	
	public WordFrequencyImpl(int frequency, String word) {
		this.frequency = frequency;
		this.word = word;
	}
	
	public WordFrequencyImpl(String word) {
		this.frequency = 0;
		this.word = word;
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
