package Servlet;

import Bean.Info;
import DAO.InfoDao;
import DAO.InfoDaoImpl;
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
 * @date 2022-10-04 下午8:31
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
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
        String name=req.getParameter("name")==null?"":req.getParameter("name");
        String gender=req.getParameter("gender")==null?"":req.getParameter("gender");
        String iclass=req.getParameter("iclass")==null?"":req.getParameter("iclass");
        String mobile=req.getParameter("mobile")==null?"":req.getParameter("mobile");
        String email=req.getParameter("email")==null?"":req.getParameter("email");
        info=new Info(id,name,gender,iclass,mobile,email);
        infoDao.updateInfo(JDBCUtils.getConnection(),info);

    }
}
