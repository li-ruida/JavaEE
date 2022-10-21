<%@ page import="Bean.NumberGuessBean" %>

<jsp:useBean id="numguess" class="Bean.NumberGuessBean" scope="session"/>
<jsp:setProperty name="numguess" property="*"/>
<%@ taglib prefix="ex" uri="WEB-INF/reset.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Number Guess</title></head>
<body bgcolor="white">
<font size=4>

    <c:set var="getSuccess" scope="session" value="${numguess.getSuccess()}"/>
    <c:if test="${getSuccess==true}">
    Congratulations! You got it.
    And after just <%= numguess.getNumGuesses() %> tries.<p>

        <ex:resettag/>

    Care to <a href="numguess.jsp">try again</a>?
    </c:if>
    <c:set var="getNumGuesses" scope="session" value="${numguess.getNumGuesses()}"/>
    <c:if test="${getNumGuesses== 0}">


    Welcome to the Number Guess game.
    <p>

        I'm thinking of a number between 1 and 100.
    <p>

    <form method=get>
        What's your guess? <input type=text name=guess>
        <input type=submit value="Submit">
    </form>
    </c:if>
    <c:if test="${getNumGuesses!= 0 &&getSuccess==false}">

        Good guess, but nope. Try <b><%= numguess.getHint() %>
    </b>.

        You have made <%= numguess.getNumGuesses() %> guesses.<p>

        I'm thinking of a number between 1 and 100.<p>

        <form method=get>
            What's your guess? <input type=text name=guess>
            <input type=submit value="Submit">
        </form>

    </c:if>

</font>
</body>
</html>
