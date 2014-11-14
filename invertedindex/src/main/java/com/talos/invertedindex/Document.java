package com.talos.invertedindex;

public class Document {
	
	private int m_id;
	private String m_content;
	
	public Document(int id, String content) {
		super();
		m_id = id;
		m_content = content;
	}

	public int getId() {
		return m_id;
	}
	
	public String getContent() {
		return m_content;
	}
}
