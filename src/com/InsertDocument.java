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
@WebServlet("/InsertDocument")
public class InsertDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InsertDocument() {
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String insert=request.getParameter("insert");
	    
		LuceneBean insertbean=(LuceneBean)getServletContext().getAttribute("lucenebean");
		
		 StandardAnalyzer analyzer;
		 IndexWriterConfig config;
		 Directory directory;
		
		analyzer = insertbean.getAnalyzer();
		directory = insertbean.getDirectory();
	    //config = insertbean.getIndexWriterConfig();
		 config = new IndexWriterConfig(Version.LUCENE_34, analyzer);
	    
	    IndexWriter writer = new IndexWriter(insertbean.getDirectory(),config);
	    
	    Document doc = new Document();
	    doc.add(new Field("title", insert, Field.Store.YES, Field.Index.ANALYZED));
	    writer.addDocument(doc);
	    writer.close();
		
		out.print("<html>");
		out.print("<head><title>");
		out.print("Insert");
		out.print("</title></head>");
		out.print("<body>");
		out.print("<div align ='center'>");
		out.print("<img src='img.jpg'>");
		//out.print("<p>Hello World!</p>");
		out.print("<p>");
		out.print(insert);
		out.print(" inserted to the index");
		out.print("</p>");
		out.print("<form method='post' action='/SearchEngine/home.jsp'>");
		out.print("<input type='submit' value='Home'>");
		out.print("</form>");
		
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");


		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
