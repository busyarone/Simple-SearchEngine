<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lucene!</title>
</head>
<body>
<div align="center">
<img src="img.jpg">
<!--  <form method="post" action="/SearchEngine/HelloServlet">
<input type="submit" value="Hello">
</form> -->

<!-- <table>
<tr>
<td>
<a href="/SearchEngine/search.jsp"><input type="button" value="Search"></a>
</td>
<td>
<a href="/SearchEngine/insert.jsp"><input type="button" value="Insert"></a>
</td>
<td>
<a href="/SearchEngine/delete.jsp"><input type="button" value="Delete"></a>
</td>
</tr>

</table>-->


<table>
<tr>
<td><form method="post" action="/SearchEngine/search.jsp">
<input type="submit" value="Search">
</form>
</td>
<td><form method="post" action="/SearchEngine/insert.jsp">
<input type="submit" value="Insert">
</form>
</td>
<td><form method="post" action="/SearchEngine/delete.jsp">
<input type="submit" value="Delete">
</form>
</td>
</tr>

</table>




</div>
</body>
</html>