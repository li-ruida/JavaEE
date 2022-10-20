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
import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-10-04 下午6:52
 */
@WebServlet("/adminview")
public class AdminViewServlet extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        HttpSession session=req.getSession();
        Object obj=session.getAttribute("admin");
        if (obj==null)
            obj=" ";
        String adminflag= String.valueOf(obj);
        if(adminflag==null||!adminflag.equals("yes")){
            resp.sendRedirect("/adminlogin");
        }










        super.processTemplate("adminview",req,resp);
    }
}
