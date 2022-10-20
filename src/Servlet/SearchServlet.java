package Servlet;

import Bean.Info;
import DAO.BaseDao;
import DAO.InfoDao;
import DAO.InfoDaoImpl;
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
 * @date 2022-10-04 下午2:25
 */
@WebServlet("/search")
public class SearchServlet extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        HttpSession session=req.getSession();
        String info = req.getParameter("info");
        String pagestr=req.getParameter("page");
        int nowpage=pagestr==null?1: Integer.parseInt(pagestr);
        int pagelen= Integer.parseInt(req.getParameter("pagelen"));
        System.out.println(info);
        boolean isFind=false;
        PrintWriter out=resp.getWriter();

        //数据库查询
        String sql="select count(*) from info where id=\'"+info +"\'or name=\'"+info +"\' or gender= \'"+info +"\' or iclass= \'"+info +"\' or " +
                "mobile= \'"+info +"\' or email= \'"+info +"\';";
        Integer tempint = Integer.valueOf(BaseDao.getValue(JDBCUtils.getConnection(), sql).toString());
        int count=(int)tempint;
        int infosize=count;
        int pagenum=infosize/pagelen+(infosize%pagelen==0?0:1);
        if(nowpage>pagenum)
        {
            String a = URLEncoder.encode("无当前页", "UTF-8");

            out.println("<script language='javascript'>alert(decodeURIComponent('"+a+"') );</script>");
            nowpage=1;
            req.setAttribute("page","1");
        }

        sql="select * from info where id=\'"+info +"\'or name=\'"+info +"\' or gender= \'"+info +"\' or iclass= \'"+info +"\' or " +
                "mobile= \'"+info +"\' or email= \'"+info +"\'"+" limit "+(nowpage-1)+","+pagelen+";";
        System.out.println(sql);
        List<Info> forList = BaseDao.getForList(Info.class, sql);
        session.setAttribute("infos",forList);
        session.setAttribute("info",info);
        session.setAttribute("page",nowpage);
        session.setAttribute("pagelen",pagelen);
        session.setAttribute("count","查到"+count+"条数据");
        session.setAttribute("sy","http://localhost:8080/");
        session.setAttribute("dyy","http://localhost:8080/search?info="+info+"&page="+1+"&pagelen="+pagelen);
        session.setAttribute("syy","http://localhost:8080/search?info="+info+"&page="+(nowpage>1?nowpage-1:1)+"&pagelen="+pagelen);
        session.setAttribute("xyy","http://localhost:8080/search?info="+info+"&page="+(nowpage<pagenum?nowpage+1:pagenum)+"&pagelen="+pagelen);
        session.setAttribute("wy","http://localhost:8080/search?info="+info+"&page="+pagenum+"&pagelen="+pagelen);
        session.setAttribute("wyh","尾页(第\""+pagenum+"\"页)");
        session.setAttribute("sforminfo",info);


        super.processTemplate("search",req,resp);
    }


}
