package com.martinvinke.wordprocessing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.martinvinke.wordprocessing.assignment_one.TextProcessor;

@SpringBootTest
class WordprocessingApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void itReturnsTheMostFrequentWord() {
		String value = "the brown fox jumps over the fence";
		
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
	private TextProcessor getProcessor() {
		return new TextProcessor();
	}
}
