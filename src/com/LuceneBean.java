package com;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class LuceneBean {
	
	private StandardAnalyzer analyzer;
	private IndexWriterConfig config;
	private Directory directory;
	
	public LuceneBean()
	{}
	

	public void setAnalyzer(StandardAnalyzer analyzer)
	{
		this.analyzer=analyzer;
	}
	

	public void setIndexWriterConfig(IndexWriterConfig config)
	{
		this.config=config;
	}
	
	public void setDirectory(Directory directory)
	{
		this.directory=directory;
	}
	
	public StandardAnalyzer getAnalyzer()
	{
		return analyzer;
	}
	
	public IndexWriterConfig getIndexWriterConfig()
	{
		return config;
	}
	
	public Directory getDirectory()
	{
		return directory;
	}
	
}
