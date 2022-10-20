package Servlet;

import Bean.Info;
import Bean.User;
import DAO.InfoDao;
import DAO.InfoDaoImpl;
import DAO.UserDao;
import DAO.UserDaoImpl;
import Utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author lrd
 * @date 2022-10-13 下午6:34
 */
@WebServlet("/userview")
public class UserviewServlet extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        HttpSession session=req.getSession();
        PrintWriter out=resp.getWriter();
        Object obj=session.getAttribute("login");
        if (obj==null)
            obj=" ";
        String loginflag= String.valueOf(obj);
        if(loginflag==null||!loginflag.equals("yes")){
            resp.sendRedirect("/login");
        }
        String id = String.valueOf(session.getAttribute("id"));
        id=(id==null)?" ":id;
        UserDao userDao=new UserDaoImpl();
        InfoDao infoDao=new InfoDaoImpl();
        System.out.println("id"+id);
        Info u=infoDao.getInfoById(JDBCUtils.getConnection(),id);
        System.out.println(u);
        session.setAttribute("name","你好,"+u.getName());
        List<User> userList=userDao.getUser();
        for (User user:userList){
            if (user.getId().equals(id)&&user.getImg().equals(id+".png")){
                String file=File.separator +"upload"+ File.separator +id+".png";
                System.out.println(file);
                session.setAttribute("id",id);
            }
        }

        super.processTemplate("userview",req,resp);
    }
}
