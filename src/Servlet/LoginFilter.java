package Servlet;

import Bean.User;
import DAO.UserDao;
import DAO.UserDaoImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author lrd
 * @date 2022-10-13 下午6:30
 */
@WebFilter(urlPatterns = {"/user"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String id = req.getParameter("id");
        String password=req.getParameter("password");
        id=(id==null)?" ":id;
        password=(password==null)?" ":password;
        PrintWriter out=resp.getWriter();
        if(!id.equals(" ")&&!password.equals(" ")){
            chain.doFilter(req, resp);
        }
        else {
            String a = URLEncoder.encode("请先登录", "UTF-8");
            out.println("<script language='javascript'>alert(decodeURIComponent('"+a+"') );</script>");
        }
    }

    @Override
    public void destroy() {

    }
}
