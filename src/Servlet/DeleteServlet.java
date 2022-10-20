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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lrd
 * @date 2022-10-04 下午9:12
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Info info=null;
        Object obj=session.getAttribute("admin");
        if (obj==null)
            obj=" ";
        String adminflag= String.valueOf(obj);
        if(adminflag==null||!adminflag.equals("yes")){
            resp.sendRedirect("/login");
        }
        String id=req.getParameter("id");
        if(id==null){
            resp.sendRedirect("/adminview");
        }

        InfoDao infoDao=new InfoDaoImpl();
        info=infoDao.getInfoById(JDBCUtils.getConnection(),id);
        if(info==null){
            resp.sendRedirect("/adminview");
        }
        infoDao.deleteInfoById(JDBCUtils.getConnection(),id);
        UserDao userDao=new UserDaoImpl();
        userDao.deleteUserById(JDBCUtils.getConnection(),id);

    }
}
