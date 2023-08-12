package com.martinvinke.wordprocessing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.martinvinke.wordprocessing.assignment_one.TextProcessor;
import com.martinvinke.wordprocessing.assignment_one.Word;
import com.martinvinke.wordprocessing.assignment_one.WordFrequency;

@SpringBootTest
class WordprocessingApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void itReturnsTheMostFrequentWord() {
		String value = "the brown fox jumps over the lazy dog";
		
		TextProcessor processor = getProcessor();
		assertEquals(2,processor.calculateHighestFrequency(value));
		
		value = "";
		assertEquals(0,processor.calculateHighestFrequency(value));
		
		value = "word";
		assertEquals(1,processor.calculateHighestFrequency(value));
	}
	
	@Test
	void itIgnoresCaseInsensitivity() {
		String value = "It shoUld sHoulD return 2";
		
		TextProcessor processor = getProcessor();
		assertEquals(2,processor.calculateHighestFrequency(value));
	}
	
	@Test
	void itIgnoresWordsWithNumbers() {
		String value = "It1 it1 it1 it1 should should return 2";
		
		TextProcessor processor = getProcessor();
		assertEquals(2,processor.calculateHighestFrequency(value));
	}

	@Test
	void itReturnsHighestFrequencieForWord() {
		String value = "the brown fox jumps of the lazy dog";
		
		TextProcessor processor = getProcessor();
		
		assertEquals(2, processor.calculateHighestFrequencyForWord(value, "the"));
		assertEquals(1, processor.calculateHighestFrequencyForWord(value, "dog"));
	}
	
	@Test
	void itReturnsZeroWhenSearchingForNonExistingWord() {
		String value = "the brown fox jumps over the lazy dog";
		
		TextProcessor processor = getProcessor();
		
		assertEquals(0, processor.calculateHighestFrequencyForWord(value, "cat"));
	}
	
	@Test
	void itReturnsAListOfWordFrequencies() {
		String value = "the brown fox jumps over the lazy dog";
		List<WordFrequency> result = new ArrayList<WordFrequency>();
		result.add(new Word("the", 2));
		result.add(new Word("brown", 1));
		TextProcessor processor = getProcessor();
		
		assertEquals(
			result.get(0).getWord(), 
			processor.calculateMostFrequentNWord(value, 2).get(0).getWord()
		);
//		assertEquals(result, processor.calculateMostFrequentNWord(value,  2));
	}
	
	private TextProcessor getProcessor() {
		return new TextProcessor();
	}
}
