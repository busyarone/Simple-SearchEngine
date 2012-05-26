package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;



/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/DeleteDocument")
public class DeleteDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DeleteDocument() {
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int[] docid;
		//String[] titles;
		int i;
		
		try{
        String delete=request.getParameter("delete");
	    
		LuceneBean deletebean=(LuceneBean)getServletContext().getAttribute("lucenebean");
		
		 StandardAnalyzer analyzer;
		 IndexWriterConfig config;
		 Directory directory;
		
		analyzer = deletebean.getAnalyzer();
		directory = deletebean.getDirectory();
	    //config = insertbean.getIndexWriterConfig();
		 config = new IndexWriterConfig(Version.LUCENE_34, analyzer);
	    
	     IndexWriter writer = new IndexWriter(deletebean.getDirectory(),config);
	     writer.deleteDocuments(new Term("title",delete));
	     writer.commit();
	     writer.optimize();
	     writer.close();
	   
	    /*Query q = new QueryParser(Version.LUCENE_34, "title", analyzer).parse(delete);
	    
	    int hitsPerPage = 10;
	    IndexSearcher searcher = new IndexSearcher(directory, true);
	    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
	    searcher.search(q, collector);
	    ScoreDoc[] results = collector.topDocs().scoreDocs;
	    
	    //Document[] docs =new Document[results.length];
	    //titles = new String[results.length];
	    docid=new int[results.length];
	    
	    for(i=0;i<results.length;i++)
	    {
	    	docid[i]=results[i].doc;
	    	//docs[i]=searcher.doc(docid[i]);
	    	//titles[i]=docs[i].get("title");
	    }
	    searcher.close();*/
	    
	    //IndexReader reader = IndexReader.open(directory);
	    
	   /* for(i=0;i<results.length;i++)
	    {
	    	out.print(docid[i]);
	    	//reader.deleteDocument(docid[i]);
	    }*/
	    
	    //reader.close();
	   // Term term= new Term(delete);
	    //writer.deleteDocuments(q);
	    
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head><title>");
		out.print("Delete");
		out.print("</title></head>");
		out.print("<body>");
		out.print("<div align ='center'>");
		out.print("<img src='img.jpg'>");
		out.print("</div>");
		//out.print("<p>Hello World!</p>");
		out.print("<div align ='center'>");
		out.print(delete);
		out.print(" index removed");
		/*for(i=0;i<results.length;i++)
	    {
	    	out.print(docid[i]);
	    	//reader.deleteDocument(docid[i]);
	    }*/
		out.print("<br />");
		out.print("<form method='post' action='/SearchEngine/home.jsp'>");
		out.print("<input type='submit' value='Home'>");
		out.print("</form>");
		
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");

		}
		
		catch(IOException e)
		{
			System.err.println(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
