package com.talos.invertedindex;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.tartarus.martin.Stemmer;

public final class InvertedIndex {

	private static final String ALPHANUM_REGEX = "[^a-zA-Z0-9\\s]";
	private static final String ESCAPED_SPACE = "\\s";
	private static final String SIMPLE_SPACE = "";
	private Document[] documents;
	private Map<String, List<Document>> internalIndex = new Hashtable<>();

	public InvertedIndex(String[] data) {
		assert data != null : "No data found!";

		documents = new Document[data.length];
		for (int i = 0; i < data.length; i++) {
			documents[i] = new Document(i, data[i]);
		}
	}

	/**
	 * Indexes the previously loaded data. 
	 * @param stem Boolean parameter to stem.
	 * @param stopWords Stop words to rule out of the indexing. Can be null.
	 */
	public void indexData(boolean stem, List<String> stopWords)
	{
		if (stem)
		{
			indexDataWithStemming(stopWords);
		}
		else
		{
			indexDataWithoutStemming(stopWords);
		}
	}
	
	private void indexDataWithoutStemming(List<String> stopWords) {
		assert documents != null : "No documents loaded!";
		for (Document nextDocument : documents) {
			String[] tokensInDocument = nextDocument.getContent().split(
					ESCAPED_SPACE);
			for (String nextTokenInDocument : tokensInDocument) {
				indexToken(nextTokenInDocument, nextDocument, stopWords);
			}
		}
	}

	/**
	 * Applies stemming to the tokens. Uses the 
	 * @param stopWords
	 */
	private void indexDataWithStemming(List<String> stopWords) {
		assert documents != null : "No documents loaded!";
		for (Document nextDocument : documents) {
			String[] tokensInDocument = nextDocument.getContent().split(
					ESCAPED_SPACE);
			for (String nextTokenInDocument : tokensInDocument) {
				char[] tokenChars = nextTokenInDocument.toCharArray();
				Stemmer stemmer = new Stemmer();
				for (char nextChar : tokenChars) {
					stemmer.add(nextChar);
				}
				stemmer.stem();
				String stemmedToken = new String(stemmer.getResultBuffer());
				indexToken(stemmedToken, nextDocument, stopWords);
			}
		}
	}

	/**
	 * Indexes the tokenToIndex regarding the passed document. Does not index the token if it is a stop word.
	 * @param tokenToIndex
	 * @param document
	 * @param stopWords Can be null.
	 */
	private void indexToken(String tokenToIndex, Document document, List<String> stopWords) {
		assert tokenToIndex != null : "No token found!";
		assert document != null : "No document found";

		tokenToIndex = getNormalizedToken(tokenToIndex);
		if (stopWords != null && stopWords.contains(tokenToIndex))
		{
			//Don't index stop words.
			return;
		}

		List<Document> documents;
		if (internalIndex.get(tokenToIndex) == null) {
			documents = new ArrayList<Document>();
			documents.add(document);
			internalIndex.put(tokenToIndex, documents);
		} else {
			documents = internalIndex.get(tokenToIndex);
			if (!documents.contains(tokenToIndex)) {
				documents.add(document);
			}
		}
	}

	/**
	 * Transforms to lower case and removes non alpha-numeric characters.
	 * @param token
	 * @return Normalized token.
	 */
	private String getNormalizedToken(String token) {
		assert token != null : "Null token!";
		token = token.toLowerCase();
		return token.replaceAll(ALPHANUM_REGEX, SIMPLE_SPACE);
	}

	/**
	 * @param searchedText
	 * @return The text of the documents where the searched text has occurrences.
	 */
	public String[] get(String searchedText) {
		assert searchedText != null : "Nothing to search";
		List<Document> documents = internalIndex.get(searchedText);
		String[] results = new String[documents.size()];
		for (int i = 0; i < results.length; i++) {
			results[i] = documents.get(i).getContent();
		}
		return results;
	}
}
