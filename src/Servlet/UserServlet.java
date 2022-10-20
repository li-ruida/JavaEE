package Servlet;

import Bean.User;
import DAO.UserDao;
import DAO.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author lrd
 * @date 2022-10-13 下午6:57
 */
@WebServlet("/user")
public class UserServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        HttpSession session=req.getSession();
        PrintWriter out=resp.getWriter();

        String id = req.getParameter("id");
        String password=req.getParameter("password");
        id=(id==null)?" ":id;
        session.setAttribute("id",id);
        password=(password==null)?" ":password;
        UserDao userDao=new UserDaoImpl();
        List<User> userList=userDao.getUser();
        boolean isFind=false;
        for (User u:userList){

            if (u.getId().equals(id)&&u.getPassword().equals(password)){
                session.setAttribute("login","yes");
                resp.sendRedirect("/userview");
            }
        }
        if(!isFind) {
            String a = URLEncoder.encode("账号或密码错误", "UTF-8");
            out.println("<script language='javascript'>alert(decodeURIComponent('"+a+"') );</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
