package com.martinvinke.wordprocessing.controller;

import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.martinvinke.wordprocessing.assignment_one.TextProcessor;
import com.martinvinke.wordprocessing.assignment_one.WordFrequency;

@Path("/text")
public class WordsResource {
	private TextProcessor textProcessor;

	public WordsResource() {
		this.textProcessor = new TextProcessor();
	}
	
    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }

    @GET
    @Path("/{text}/frequency/most-frequent")
    public Response getMostFrequentWord(@PathParam("text") String text) {
    	int mostFrequentWord = textProcessor.calculateHighestFrequency(text);
    	
    	return Response.ok(mostFrequentWord).type(MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("/{text}/frequency/word/{word}")
    public Response getMostFrequentWord(@PathParam("text") String text, @PathParam("word") String word) {
    	int wordFrequency = textProcessor.calculateHighestFrequencyForWord(text, word);
    	
    	return Response.ok(wordFrequency).type(MediaType.TEXT_PLAIN).build();
    }
    
    @GET
    @Path("/{text}/frequency/most-frequent/{amount}")
    public Response getMostFrequentNWords(@PathParam("text") String text, @PathParam("number") int amount) {
    	List<WordFrequency> mostFrequentNWords = textProcessor.calculateMostFrequentNWord(text, amount);
    	
    	return Response.ok(mostFrequentNWords).type(MediaType.APPLICATION_JSON).build();
    }
}