package com.martinvinke.wordprocessing.assignment_one;

import java.util.List;

public interface WordFrequencyAnalyzer {
	int calculateHighestFrequency(String text);
	int calculateHighestFrequencyForWord(String text, String word);
	List<WordFrequency> calculateMostFrequentNWord(String text, int n);
}
