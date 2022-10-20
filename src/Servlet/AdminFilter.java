package Servlet;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * @author lrd
 * @date 2022-10-04 下午7:07
 */
@WebFilter(urlPatterns = {"/admin"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        String id = req.getParameter("id");
        String password=req.getParameter("password");
        id=(id==null)?" ":id;
        password=(password==null)?" ":password;
        String adminid="admin";
        String adminpassword="password";
        PrintWriter out=resp.getWriter();
        if(id.equals(adminid)&&password.equals(adminpassword)){
            chain.doFilter(req, resp);
        }
        else {
            String a = URLEncoder.encode("请登录后访问!", "UTF-8");
            out.println("<script language='javascript'>alert(decodeURIComponent('"+a+"') );</script>");
        }
    }

    @Override
    public void destroy() {

    }
}
