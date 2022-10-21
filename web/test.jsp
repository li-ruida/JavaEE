<%@ page import="Bean.NumberGuessBean" %>

<jsp:useBean id="numguess" class="Bean.NumberGuessBean" scope="session"/>
<jsp:setProperty name="numguess" property="*"/>
<%@ taglib prefix="ex" uri="WEB-INF/reset.tld" %>
<%@ taglib prefix="dx" uri="WEB-INF/getnumguesses.tld" %>
<%@ taglib prefix="exx" uri="WEB-INF/gethint.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Number Guess</title></head>
<body bgcolor="white">
<font size=4>

    <c:set var="getSuccess" scope="session" value="${numguess.getSuccess()}"/>
    <c:if test="${getSuccess==true}">
    Congratulations! You got it.
    And after just <dx:getnumguessestag/> tries.<p>

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

        Good guess, but nope. Try <b><exx:gethinttag/>
    </b>.

        You have made <dx:getnumguessestag/> guesses.<p>

        I'm thinking of a number between 1 and 100.<p>

        <form method=get>
            What's your guess? <input type=text name=guess>
            <input type=submit value="Submit">
        </form>

    </c:if>

</font>
</body>
</html>
