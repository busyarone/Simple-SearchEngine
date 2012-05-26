package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HelloServlet() {
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		/*String value="solar";
		ServletContext context = getServletContext();
		synchronized(this){
			if(context.getAttribute("simplebean")==null)
			{
				SimpleBean simplebean= new SimpleBean(value);
				context.setAttribute("simplebean", simplebean);	
			}
		}*/
		
		ServletContext context = getServletContext();
		synchronized(this){
			if(context.getAttribute("lucenebean")==null)
			{
				LuceneBean lucenebean= new LuceneBean();
				
				StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
			    // 1. create the index
			    Directory index = new RAMDirectory();
			    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_34, analyzer);
			    IndexWriter w = new IndexWriter(index, config);
			   /* index is directory where you want to store index, config has the analyzer information you want to use when indexing the documents*/
			   
			    lucenebean.setAnalyzer(analyzer);
			    lucenebean.setDirectory(index);
			    lucenebean.setIndexWriterConfig(config);
			    
			    Document doc1 = new Document();
			    doc1.add(new Field("title", "Lucene in Action", Field.Store.YES, Field.Index.ANALYZED));
			    w.addDocument(doc1);
			    Document doc2 = new Document();
			    doc2.add(new Field("title", "Lucene for Dummies", Field.Store.YES, Field.Index.ANALYZED));
			    w.addDocument(doc2);
			    Document doc3 = new Document();
			    doc3.add(new Field("title", "Managing Gigabytes", Field.Store.YES, Field.Index.ANALYZED));
			    w.addDocument(doc3);
			    Document doc4 = new Document();
			    doc4.add(new Field("title", "The Art of Computer Science", Field.Store.YES, Field.Index.ANALYZED));
			    w.addDocument(doc4);
			   
			    w.close();
				
				
				
				
				context.setAttribute("lucenebean", lucenebean);	
			}
		}
		
		
		out.print("<html>");
		out.print("<head><title>");
		out.print("Hello");
		out.print("</title></head>");
		out.print("<body>");
		out.print("<div align ='center'>");
		out.print("<p>Hello World!</p>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");

        response.sendRedirect("/SearchEngine/home.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
