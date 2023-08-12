package com.martinvinke.wordprocessing.assignment_one;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextProcessor implements WordFrequencyAnalyzer {

	@Override
	public int calculateHighestFrequency(String text) {
		if (text.equals("")) {
			return 0;
		}
		String[] words = sanitize(text);
		HashMap<String, Word> wordsByFrequency = new HashMap<String, Word>();
		Word mostFrequentWord = null;
		
		for (String currentWord : words) {
			Word word = null;
			if (!isWord(currentWord)) {
				continue;
			}
			
			if (wordsByFrequency.containsKey(currentWord)) {
				word = wordsByFrequency.get(currentWord);
			} else {
				word = new Word(currentWord);
				wordsByFrequency.put(currentWord, word);
			}
			
			word.incrementFrequency();
			mostFrequentWord = checkMostFrequentWord(word, mostFrequentWord);
		}
		
		return mostFrequentWord.getFrequency();
	}

	@Override
	public int calculateHighestFrequencyForWord(String text, String word) {
		String[] words = sanitize(text);
		Map<String, Integer> wordsByFrequency = mapWordsByFrequency(words);
		
		return wordsByFrequency.getOrDefault(word, 0);
	}

	@Override
	public List<WordFrequency> calculateMostFrequentNWord(String text, int n) {
		String[] words = sanitize(text);

		Map<String, Integer> wordFrequencyMap = mapWordsByFrequency(words);
		
		List<WordFrequency> result = wordFrequencyMap.entrySet().stream()
			    .map(item -> new Word(item.getKey(), item.getValue()))
			    .limit(n)
			    .collect(Collectors.toList());
		
		result.sort(this::compareWords);
	
		return result;
	}
	
	private Boolean isWord(String text) {
		return Pattern.matches("[a-zA-Z]+",text);
	}
	
	private Word checkMostFrequentWord(Word currentWord, Word mostFrequentWord) {
		if (mostFrequentWord == null ||
			currentWord.getFrequency() > mostFrequentWord.getFrequency()) {
			return currentWord;
		}
		
		return mostFrequentWord;
	}
	
	private Map<String, Integer> mapWordsByFrequency(String[] words) {
		Map<String, Integer> wordFrequencyMap = new HashMap<>();
		
		for (String word : words) {
			if (isWord(word)) {
				wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);	
			}
		}
		
		return wordFrequencyMap;
	}
	
	private int compareWords(WordFrequency word1, WordFrequency word2) {
		if (word1.getFrequency() == word2.getFrequency()) {
			return word1.getWord().compareTo(word2.getWord());
		}

		return Integer.compare(word2.getFrequency(), word1.getFrequency());
	}
	
	private String[] sanitize(String text) {
		return text.toLowerCase().split(" ");
	}
}
