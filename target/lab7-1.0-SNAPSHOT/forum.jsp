<%@ page import="com.example.lab7.ForumController" %>
<%@ page import="com.example.lab7.Domain.Message" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Mukhametkaly
  Date: 09.04.2021
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        ForumController forumController = new ForumController();

        ArrayList<Message> messages = forumController.getForum_messages().getMessagesInForum(1, forumController.getUsersCollection().getConn());

        for (Message message : messages) {
            response.getWriter().println("<h1>" + message.getAuthor() + "</h1>");
            response.getWriter().println("<p>" + message.getMessage() + "</p>");
        }
    %>
    <form action="/forum" method="post">
        <input type="textarea" name="message" placeholder="message">
        <br>
        <input type="submit" value="Submit">
    </form>


</body>
</html>
