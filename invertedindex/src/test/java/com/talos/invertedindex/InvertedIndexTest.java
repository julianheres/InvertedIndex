package com.talos.invertedindex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class InvertedIndexTest {

	private static final String OPINION = "opinion";
	private static final String WRITES = "writes";
	private static final String MUSIC = "music";
	
	private static final String[] DATA_1 = new String[] {
			"A brilliant, festive study of JS Bach uses literature and painting to illuminate his 'dance-impregnated' music, writes Peter Conrad",
			"Fatima Bhutto on Malala Yousafzai's fearless and still-controversial memoir",
			"Grisham's sequel to A Time to Kill is a solid courtroom drama about racial prejudice marred by a flawless white hero, writes John O'Connell",
			"This strange repackaging of bits and pieces does the Man Booker winner no favours, says Sam Leith",
			"Another book with music related content" };
	private static final String[] DATA_2 = new String[] {
			"No opinions answered oh felicity is resolved hastened. Produced it friendly my if opinions humoured. Enjoy is wrong folly no taken. It sufficient instrument insipidity simplicity at interested. Law pleasure attended differed mrs fat and formerly. Merely thrown garret her law danger him son better excuse. Effect extent narrow in up chatty. Small are his chief offer happy had.",
			"He share of first to worse. Weddings and any opinion suitable smallest nay. My he houses or months settle remove ladies appear. Engrossed suffering supposing he recommend do eagerness. Commanded no of depending extremity recommend attention tolerably. Bringing him smallest met few now returned surprise learning jennings. Objection delivered eagerness he exquisite at do in. Warmly up he nearer mr merely me.",
			"Warmly little before cousin sussex entire men set. Blessing it ladyship on sensible judgment settling outweigh. Worse linen an of civil jokes leave offer. Parties all clothes removal cheered calling prudent her. And residence for met the estimable disposing. Mean if he they been no hold mr. Is at much do made took held help. Latter person am secure of estate genius at.",
			"Guest it he tears aware as. Make my no cold of need. He been past in by my hard. Warmly thrown oh he common future. Otherwise concealed favourite frankness on be at dashwoods defective at. Sympathize interested simplicity at do projecting increasing terminated. As edward settle limits at in.",
			"Kept in sent gave feel will oh it we. Has pleasure procured men laughing shutters nay. Old insipidity motionless continuing law shy partiality. Depending acuteness dependent eat use dejection. Unpleasing astonished discovered not nor shy. Morning hearted now met yet beloved evening. Has and upon his last here must.",
			"May musical arrival beloved luckily adapted him. Shyness mention married son she his started now. Rose if as past near were. To graceful he elegance oh moderate attended entrance pleasure. Vulgar saw fat sudden edward way played either. Thoughts smallest at or peculiar relation breeding produced an. At depart spirit on stairs. She the either are wisdom praise things she before. Be mother itself vanity favour do me of. Begin sex was power joy after had walls miles.",
			"Although moreover mistaken kindness me feelings do be marianne. Son over own nay with tell they cold upon are. Cordial village and settled she ability law herself. Finished why bringing but sir bachelor unpacked any thoughts. Unpleasing unsatiable particular inquietude did nor sir. Get his declared appetite distance his together now families. Friends am himself at on norland it viewing. Suspected elsewhere you belonging continued commanded she.",
			"Lose away off why half led have near bed. At engage simple father of period others except. My giving do summer of though narrow marked at. Spring formal no county ye waited. My whether cheered at regular it of promise blushes perhaps. Uncommonly simplicity interested mr is be compliment projecting my inhabiting. Gentleman he september in oh excellent.",
			"For who thoroughly her boy estimating conviction. Removed demands expense account in outward tedious do. Particular way thoroughly unaffected projection favourable mrs can projecting own. Thirty it matter enable become admire in giving. See resolved goodness felicity shy civility domestic had but. Drawings offended yet answered jennings perceive laughing six did far.",
			"Carried nothing on am warrant towards. Polite in of in oh needed itself silent course. Assistance travelling so especially do prosperous appearance mr no celebrated. Wanted easily in my called formed suffer. Songs hoped sense as taken ye mirth at. Believe fat how six drawing pursuit minutes far. Same do seen head am part it dear open to. Whatever may scarcely judgment had" };

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

	// Requested queries
	@Test
	public void testInvertedIndexIncludingStopWords1() {
		System.out.println("Test");
		long timeBeforeIndexing = System.currentTimeMillis();
		InvertedIndex invertedIndex = new InvertedIndex(DATA_1);
		invertedIndex.indexData(null);
		long timeAfterIndexing = System.currentTimeMillis();
		System.out
				.println("Time indexing including stop words: "
						+ (timeAfterIndexing - timeBeforeIndexing)
						+ " milliseconds");
		long timeBeforeSearching = System.currentTimeMillis();
		String[] results = invertedIndex.get(MUSIC);
		long timeAfterSearching = System.currentTimeMillis();
		System.out
				.println("Time searching including stop words: "
						+ (timeAfterSearching - timeBeforeSearching)
						+ " milliseconds");
		assertTrue(results.length == 2);
		System.out.println("Number of results: " + results.length);
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
	public void testInvertedIndexIncludingStopWords2() {
		System.out.println("Test");
		long timeBeforeIndexing = System.currentTimeMillis();
		InvertedIndex invertedIndex = new InvertedIndex(DATA_1);
		invertedIndex.indexData(null);
		long timeAfterIndexing = System.currentTimeMillis();
		System.out
				.println("Time indexing including stop words: "
						+ (timeAfterIndexing - timeBeforeIndexing)
						+ " milliseconds");
		long timeBeforeSearching = System.currentTimeMillis();
		String[] results = invertedIndex.get(WRITES);
		long timeAfterSearching = System.currentTimeMillis();
		System.out
				.println("Time searching including stop words: "
						+ (timeAfterSearching - timeBeforeSearching)
						+ " milliseconds");
		assertTrue(results.length == 2);
		System.out.println("Number of results: " + results.length);
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

	// Additional Queries
	@Test
	public void testInvertedIndexExcludingStopWords1() {
		System.out.println("Test");
		long timeBeforeIndexing = System.currentTimeMillis();
		InvertedIndex invertedIndex = new InvertedIndex(DATA_1);
		invertedIndex.indexData(englishStopWords);
		long timeAfterIndexing = System.currentTimeMillis();
		System.out
				.println("Time indexing excluding stop words: "
						+ (timeAfterIndexing - timeBeforeIndexing)
						+ " milliseconds");
		long timeBeforeSearching = System.currentTimeMillis();
		String[] results = invertedIndex.get(MUSIC);
		long timeAfterSearching = System.currentTimeMillis();
		System.out
				.println("Time searching excluding stop words: "
						+ (timeAfterSearching - timeBeforeSearching)
						+ " milliseconds");
		assertTrue(results.length == 2);
		System.out.println("Number of results: " + results.length);
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
	public void testInvertedIndexIncludingStopWords3() {
		System.out.println("Test");
		long timeBeforeIndexing = System.currentTimeMillis();
		InvertedIndex invertedIndex = new InvertedIndex(DATA_2);
		invertedIndex.indexData(englishStopWords);
		long timeAfterIndexing = System.currentTimeMillis();
		System.out
				.println("Time indexing including stop words: "
						+ (timeAfterIndexing - timeBeforeIndexing)
						+ " milliseconds");
		long timeBeforeSearching = System.currentTimeMillis();
		String[] results = invertedIndex.get(OPINION);
		long timeAfterSearching = System.currentTimeMillis();
		System.out
				.println("Time searching including stop words: "
						+ (timeAfterSearching - timeBeforeSearching)
						+ " milliseconds");
		assertTrue(results.length == 1);
		System.out.println("Number of results: " + results.length);
		String actualResults = Arrays.toString(results);
		String expectedResults = Arrays
				.toString(
						new String[] { "He share of first to worse. Weddings and any opinion suitable smallest nay. My he houses or months settle remove ladies appear. Engrossed suffering supposing he recommend do eagerness. Commanded no of depending extremity recommend attention tolerably. Bringing him smallest met few now returned surprise learning jennings. Objection delivered eagerness he exquisite at do in. Warmly up he nearer mr merely me." })
				.toString();
		assertEquals(expectedResults, actualResults);
		System.out.println(actualResults);
	}
	
	@Test
	public void testInvertedIndexExcludingStopWords2() {
		System.out.println("Test");
		long timeBeforeIndexing = System.currentTimeMillis();
		InvertedIndex invertedIndex = new InvertedIndex(DATA_2);
		invertedIndex.indexData(englishStopWords);
		long timeAfterIndexing = System.currentTimeMillis();
		System.out
				.println("Time indexing excluding stop words: "
						+ (timeAfterIndexing - timeBeforeIndexing)
						+ " milliseconds");
		long timeBeforeSearching = System.currentTimeMillis();
		String[] results = invertedIndex.get(OPINION);
		long timeAfterSearching = System.currentTimeMillis();
		System.out
				.println("Time searching excluding stop words: "
						+ (timeAfterSearching - timeBeforeSearching)
						+ " milliseconds");
		assertTrue(results.length == 1);
		System.out.println("Number of results: " + results.length);
		String actualResults = Arrays.toString(results);
		String expectedResults = Arrays
				.toString(
						new String[] { "He share of first to worse. Weddings and any opinion suitable smallest nay. My he houses or months settle remove ladies appear. Engrossed suffering supposing he recommend do eagerness. Commanded no of depending extremity recommend attention tolerably. Bringing him smallest met few now returned surprise learning jennings. Objection delivered eagerness he exquisite at do in. Warmly up he nearer mr merely me." })
				.toString();
		assertEquals(expectedResults, actualResults);
		System.out.println(actualResults);
	}
}
