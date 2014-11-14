package com.talos.invertedindex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class InvertedIndexTest {

	private static final String WRITES = "writes";
	private static final String MUSIC = "music";
	private static final String[] DATA_1 = new String[] {
			"A brilliant, festive study of JS Bach uses literature and painting to illuminate his 'dance-impregnated' music, writes Peter Conrad",
			"Fatima Bhutto on Malala Yousafzai's fearless and still-controversial memoir",
			"Grisham's sequel to A Time to Kill is a solid courtroom drama about racial prejudice marred by a flawless white hero, writes John O'Connell",
			"This strange repackaging of bits and pieces does the Man Booker winner no favours, says Sam Leith",
			"Another book with music related content" };
	private List<String> englishStopWords = Arrays.asList("a", "about",
			"above", "after", "again", "against", "all", "am", "an", "and",
			"any", "are", "aren't", "as", "at", "be", "because", "been",
			"before", "being", "below", "between", "both", "but", "by",
			"can't", "cannot", "could", "couldn't", "did", "didn't", "do",
			"does", "doesn't", "doing", "don't", "down", "during", "each",
			"few", "for", "from", "further", "had", "hadn't", "has", "hasn't",
			"have", "haven't", "having", "he", "he'd", "he'll", "he's", "her",
			"here", "here's", "hers", "herself", "him", "himself", "his",
			"how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in",
			"into", "is", "isn't", "it", "it's", "its", "itself", "let's",
			"me", "more", "most", "mustn't", "my", "myself", "no", "nor",
			"not", "of", "off", "on", "once", "only", "or", "other", "ought",
			"our", "ours", "ourselves", "out", "over", "own", "same", "shan't",
			"she", "she'd", "she'll", "she's", "should", "shouldn't", "so",
			"some", "such", "than", "that", "that's", "the", "their", "theirs",
			"them", "themselves", "then", "there", "there's", "these", "they",
			"they'd", "they'll", "they're", "they've", "this", "those",
			"through", "to", "too", "under", "until", "up", "very", "was",
			"wasn't", "we", "we'd", "we'll", "we're", "we've", "were",
			"weren't", "what", "what's", "when", "when's", "where", "where's",
			"which", "while", "who", "who's", "whom", "why", "why's", "with",
			"won't", "would", "wouldn't", "you", "you'd", "you'll", "you're",
			"you've", "your", "yours", "yourself", "yourselves");

	// Queries required
	@Test
	public void testInvertedIndexWithoutStemmingIncludingStopWords1() {
		long timeBeforeIndexing = System.currentTimeMillis();
		InvertedIndex invertedIndex = new InvertedIndex(DATA_1);
		invertedIndex.indexData(false, null);
		long timeAfterIndexing = System.currentTimeMillis();
		System.out
				.println("Time indexing without stemming and including stop words: "
						+ (timeAfterIndexing - timeBeforeIndexing)
						+ " milliseconds");
		long timeBeforeSearching = System.currentTimeMillis();
		String[] results = invertedIndex.get(MUSIC);
		long timeAfterSearching = System.currentTimeMillis();
		System.out
				.println("Time searching without stemming and including stop words: "
						+ (timeAfterSearching - timeBeforeSearching)
						+ " milliseconds");
		assertTrue(results.length == 2);
		System.out.println(results.length);
		String actualResults = Arrays.toString(results);
		String expectedResults = Arrays
				.toString(
						new String[] {
								"A brilliant, festive study of JS Bach uses literature and painting to illuminate his 'dance-impregnated' music, writes Peter Conrad",
								"Another book with music related content" })
				.toString();
		assertEquals(expectedResults, actualResults);
		System.out.println(actualResults);

	}

	@Test
	public void testInvertedIndexWithoutStemmingIncludingStopWords2() {
		long timeBeforeIndexing = System.currentTimeMillis();
		InvertedIndex invertedIndex = new InvertedIndex(DATA_1);
		invertedIndex.indexData(false, null);
		long timeAfterIndexing = System.currentTimeMillis();
		System.out
				.println("Time indexing without stemming and without including stop words: "
						+ (timeAfterIndexing - timeBeforeIndexing)
						+ " milliseconds");
		long timeBeforeSearching = System.currentTimeMillis();
		String[] results = invertedIndex.get(WRITES);
		long timeAfterSearching = System.currentTimeMillis();
		System.out
				.println("Time searching without stemming and without including stop words: "
						+ (timeAfterSearching - timeBeforeSearching)
						+ " milliseconds");
		assertTrue(results.length == 2);
		System.out.println(results.length);
		String actualResults = Arrays.toString(results);
		String expectedResults = Arrays
				.toString(
						new String[] {
								"A brilliant, festive study of JS Bach uses literature and painting to illuminate his 'dance-impregnated' music, writes Peter Conrad",
								"Another book with music related content" })
				.toString();
		assertEquals(expectedResults, actualResults);
		System.out.println(actualResults);

	}

	// Additional Queries
	@Test
	public void testInvertedIndexWithoutStemmingExcludingStopWords() {
		long timeBeforeIndexing = System.currentTimeMillis();
		InvertedIndex invertedIndex = new InvertedIndex(DATA_1);
		invertedIndex.indexData(false, englishStopWords);
		long timeAfterIndexing = System.currentTimeMillis();
		System.out
				.println("Time indexing without stemming and excluding stop words: "
						+ (timeAfterIndexing - timeBeforeIndexing)
						+ " milliseconds");
		long timeBeforeSearching = System.currentTimeMillis();
		String[] results = invertedIndex.get(MUSIC);
		long timeAfterSearching = System.currentTimeMillis();
		System.out
				.println("Time searching without stemming and excluding stop words: "
						+ (timeAfterSearching - timeBeforeSearching)
						+ " milliseconds");
		assertTrue(results.length == 2);
		System.out.println(results.length);
		String actualResults = Arrays.toString(results);
		String expectedResults = Arrays
				.toString(
						new String[] {
								"A brilliant, festive study of JS Bach uses literature and painting to illuminate his 'dance-impregnated' music, writes Peter Conrad",
								"Another book with music related content" })
				.toString();
		assertEquals(expectedResults, actualResults);
		System.out.println(actualResults);

	}

	@Test
	public void testInvertedIndexWithStemmingExcludingStopWords() {
		long timeBeforeIndexing = System.currentTimeMillis();
		InvertedIndex invertedIndex = new InvertedIndex(DATA_1);
		invertedIndex.indexData(true, englishStopWords);
		long timeAfterIndexing = System.currentTimeMillis();
		System.out
				.println("Time indexing with stemming and excluding stop words: "
						+ (timeAfterIndexing - timeBeforeIndexing)
						+ " milliseconds");
		long timeBeforeSearching = System.currentTimeMillis();
		String[] results = invertedIndex.get(WRITES);
		long timeAfterSearching = System.currentTimeMillis();
		System.out
				.println("Time searching with stemming and excluding stop words: "
						+ (timeAfterSearching - timeBeforeSearching)
						+ " milliseconds");
		assertTrue(results.length == 2);
		System.out.println(results.length);
		String actualResults = Arrays.toString(results);
		String expectedResults = Arrays
				.toString(
						new String[] {
								"A brilliant, festive study of JS Bach uses literature and painting to illuminate his 'dance-impregnated' music, writes Peter Conrad",
								"Grisham's sequel to A Time to Kill is a solid courtroom drama about racial prejudice marred by a flawless white hero, writes John O'Connell" })
				.toString();
		assertEquals(expectedResults, actualResults);
		System.out.println(actualResults);

	}

	// @Test
	// public void testInvertedIndexWithStemming() {
	// InvertedIndex invertedIndex = new InvertedIndex(DATA_1);
	// invertedIndex.indexDataWithStemming();
	// String[] results = invertedIndex.get("music");
	// assertTrue(results.length == 2);
	// System.out.println(results.length);
	// String actualResults = Arrays.toString(results);
	// String expectedResults = Arrays
	// .toString(
	// new String[] {
	// "A brilliant, festive study of JS Bach uses literature and painting to illuminate his 'dance-impregnated' music, writes Peter Conrad",
	// "Another book with music related content" })
	// .toString();
	// assertEquals(expectedResults, actualResults);
	// System.out.println(actualResults);
	//
	// }
}
