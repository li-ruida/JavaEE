<%--
  Created by IntelliJ IDEA.
  User: da
  Date: 28/10/2022
  Time: 下午12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Good guess, but nope.  Try <b>${gethint}</b>.

You have made ${getnumguesse} guesses.<p>

    I'm thinking of a number between 1 and 100.<p>

<form method=get>
    What's your guess? <input type=text name=guess>
    <input type=submit value="Submit">
</form>
</body>
</html>
