package com.martinvinke.wordprocessing.assignment_one;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
		HashMap<String, Word> wordsByFrequency = new HashMap<String, Word>();
		
		if (words.length == 1) {
			return 0;
		}
		
		for (String currentWord : words) {
			Word wordFrequency = null;
			if (!isWord(currentWord)) {
				continue;
			}
			
			if (wordsByFrequency.containsKey(currentWord)) {
				wordFrequency = wordsByFrequency.get(currentWord);
			} else {
				wordFrequency = new Word(currentWord);
				wordsByFrequency.put(currentWord, wordFrequency);
			}
			
			wordFrequency.incrementFrequency();
		}
		
		return wordsByFrequency.getOrDefault(word, new Word("dummy", 0)).getFrequency();
	}

	@Override
	public List<WordFrequency> calculateMostFrequentNWord(String text, int n) {
		String[] words = sanitize(text);
		String[] sortedWords = Arrays.stream(words)
				.sorted()
			    .filter(this::isWord)
			    .toArray(String[]::new);
		
		Map<String, Integer> wordFrequencyMap = new LinkedHashMap<>();

		for (String word : sortedWords) {
		    wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
		    if (wordFrequencyMap.size() == n) {
		        break;
		    }
		}
		
		List<WordFrequency> result = wordFrequencyMap.entrySet().stream()
			    .map(item -> new Word(item.getKey(), item.getValue()))
			    .collect(Collectors.toList());
		
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
	
	private String[] sanitize(String text) {
		return text.toLowerCase().split(" ");
	}
}
