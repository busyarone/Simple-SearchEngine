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


/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/SearchDocument")
public class SearchDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SearchDocument() {
        // TODO Auto-generated constructor stub
    }

	
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String titles[];
		int i,docid;
		
		try{
		LuceneBean searchbean=(LuceneBean)getServletContext().getAttribute("lucenebean");
		//LuceneBean sendbean = new LuceneBean();
		
		 StandardAnalyzer analyzer;
		 IndexWriterConfig config;
		 Directory directory;
		
		analyzer = searchbean.getAnalyzer();
		directory = searchbean.getDirectory();
	    config = searchbean.getIndexWriterConfig();
	    
	    String querystr=request.getParameter("search");
	    
	    
		Query q = new QueryParser(Version.LUCENE_34, "title", analyzer).parse(querystr);
	    int hitsPerPage = 10;
	    IndexSearcher searcher = new IndexSearcher(directory, true);
	    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
	    searcher.search(q, collector);
	    ScoreDoc[] results = collector.topDocs().scoreDocs;
	    
	    Document[] docs =new Document[results.length];
	    titles = new String[results.length];
	    for(i=0;i<results.length;i++)
	    {
	    	docid=results[i].doc;
	    	docs[i]=searcher.doc(docid);
	    	titles[i]=docs[i].get("title");
	    }
	    searcher.close();
	    
	    out.print("<html>");
		out.print("<head><title>");
		out.print("Search");
		out.print("</title></head>");
		out.print("<body>");
		out.print("<div align ='center'>");
		out.print("<img src='img.jpg'>");
		//out.print("<p>Hello World!</p>");
		out.print("</div>");
		if(titles.length==1)
		{
			out.print("<div align ='center'>");
			out.print("Found"+"&nbsp;&nbsp;"+titles.length+"&nbsp;&nbsp;"+"hit");
			out.print("</div>");
		}
		else
		{
			out.print("<div align ='center'>");
			out.print("Found"+"&nbsp;&nbsp;"+titles.length+"&nbsp;&nbsp;"+"hits");
			out.print("</div>");
		}
		out.print("<br />");
		out.print("<div align ='center'>");
		for(i=0;i<titles.length;i++)
		{
			out.print(i+1);
			out.print("&nbsp;");
			out.print(titles[i]);
			out.print("<br />");
		}
		
		out.print("<form method='post' action='/SearchEngine/home.jsp'>");
		out.print("<input type='submit' value='Home'>");
		out.print("</form>");
		
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		}


		
		
		
		
		
		
		catch(ParseException cnfe)
		{
			System.err.println(cnfe);
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
