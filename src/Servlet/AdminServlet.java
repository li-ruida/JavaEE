package Servlet;

import Bean.Info;
import DAO.BaseDao;
import Utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author lrd
 * @date 2022-10-04 下午6:26
 */
@WebServlet("/admin")
public class AdminServlet extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        HttpSession session=req.getSession();
        String id = req.getParameter("id");
        String password=req.getParameter("password");
        id=(id==null)?" ":id;
        password=(password==null)?" ":password;
        String adminid="admin";
        String adminpassword="password";
        PrintWriter out=resp.getWriter();
        if(id.equals(adminid)&&password.equals(adminpassword)){
            session.setAttribute("admin","yes");
            resp.sendRedirect("/adminview");
        }
        else {
            String a = URLEncoder.encode("账号或密码错误", "UTF-8");
            out.println("<script language='javascript'>alert(decodeURIComponent('"+a+"') );</script>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
