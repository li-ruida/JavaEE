package Numberguess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lrd
 * @date 2022-10-05 下午7:46
 */
@WebServlet("/guess")
public class NumGuessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        PrintWriter out=resp.getWriter();
        HttpSession session= req.getSession();
        Object first = session.getAttribute("first");//是否第一次猜
        Object lastobj=session.getAttribute("last");//上一个猜测数字
        String number=req.getParameter("guess");//猜测的数字
        NumberGuessBean thenum=(NumberGuessBean)session.getAttribute("num");
        //todo:dd
        if(thenum!=null)
            System.out.println(thenum.getAnswer());
        if (first==null&&((thenum!=null&&thenum.getNumGuesses()==0)||thenum==null)){
            out.println("<head><title>Number Guess</title></head>");
            if (number!=null&&number!=""){
                NumberGuessBean numberGuessBean=new NumberGuessBean();
                session.setAttribute("first","1");
                session.setAttribute("last",number);
                numberGuessBean.setGuess(number);
                session.setAttribute("num",numberGuessBean);//设置被猜数
                resp.sendRedirect("guess?guess="+number);
            }
            else {
                out.println("<body bgcolor=\"white\">\n" +
                        "<font size=\"4\">\n" +
                        "  Welcome to the Number Guess game.<p>\n" +
                        "\n" +
                        "  I'm thinking of a number between 1 and 100.</p><p>\n" +
                        "\n" +
                        "  </p><form action=\"guess\" method=\"get\">\n" +
                        "  What's your guess? <input type=\"text\" name=\"guess\" value=\"\">\n" +
                        "  <input type=\"submit\" value=\"Submit\">\n" +
                        "  </form>\n" +
                        "</body>");
            }
        }
        else {
            out.println("<head><title>Number Guess</title></head>");
            NumberGuessBean truenum=(NumberGuessBean)session.getAttribute("num");
            String status=truenum.getHint();
            String times= String.valueOf(truenum.getNumGuesses());
            if(((String)first).equals("1")){
                truenum.setNumGuesses(0);
                session.setAttribute("first","2");
            }
            if(number==null||number==""){
                //todo:
                System.out.println(number+" 111");
                number=(String) lastobj;
                out.println("<body bgcolor=\"white\">\n" +
                        "<font size=\"4\">\n" +
                        "  Good guess, but nope.  Try <b>"+status+"</b>.\n" +
                        "  You have made "+times+" guesses.<p>\n" +
                        "  I'm thinking of a number between 1 and 100.</p><p>\n" +
                        "  </p><form method=\"get\">\n" +
                        "  What's your guess? <input type=\"text\" name=\"guess\">\n" +
                        "  <input type=\"submit\" value=\"Submit\">\n" +
                        "  </form>\n" +
                        "    </div></body>");
                number=null;
            }
            else {
                session.setAttribute("last",number);
                truenum.setGuess(number);
                status=truenum.getHint();
                if(truenum.getSuccess()==true){
                    out.println("<body bgcolor=\"white\">\n" +
                            "<font size=\"4\">\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "  Congratulations!  You got it.\n" +
                            "  And after just "+truenum.getNumGuesses()+" tries.</font><p><font size=\"4\">\n" +
                            "\n" +
                            "  \n" +
                            "\n" +
                            "  Care to <a href=http://localhost:8080/guess>try again</a>?\n" +
                            "    </div></body>");
                    session.setAttribute("first",null);
                    session.setAttribute("num",null);
                    session.setAttribute("last",null);

                }
                else {
                    session.setAttribute("num",truenum);
                    out.println("<body bgcolor=\"white\">\n" +
                            "<font size=\"4\">\n" +
                            "  Good guess, but nope.  Try <b>"+status+"</b>.\n" +
                            "  You have made "+truenum.getNumGuesses()+" guesses.<p>\n" +
                            "  I'm thinking of a number between 1 and 100.</p><p>\n" +
                            "  </p><form method=\"get\">\n" +
                            "  What's your guess? <input type=\"text\" name=\"guess\">\n" +
                            "  <input type=\"submit\" value=\"Submit\">\n" +
                            "  </form>\n" +
                            "    </div></body>");
                }
            }


        }



    }
}
