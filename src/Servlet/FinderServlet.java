package Servlet;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


import Service.Tool;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import static Service.Tool.ContactstoSQL1;

public class FinderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // contact table
    private List<Map<String, Object>> contacts = new ArrayList<Map<String, Object>>();

    public void init() throws ServletException {
        try {
            String files = getInitParameter("contacts");
            files = files.trim();
            files = files.replace(',', ',');
            String[] file_name_array = files.split(",");

            for (int i = 0; i < file_name_array.length; i++) {
                String file_name = file_name_array[i];
                file_name = file_name.trim();
                if (file_name.length() == 0) {
                    continue;
                }

                File file = new File(getServletContext().getRealPath("/WEB-INF/contacts/" + file_name));

                FileInputStream fis = new FileInputStream(file);

                Workbook book = null;
                try {
                    book = new XSSFWorkbook(fis);
                } catch (Exception ex) {
                    book = new HSSFWorkbook(fis);
                }

                Sheet sheetAt = book.getSheetAt(0);

                for (Row row : sheetAt) {
                    if (row.getRowNum() == 0) {
                        continue;
                    }

                    if (row == null) {
                        break;
                    }

                    Cell cell = row.getCell(0);

                    if (cell == null) {
                        break;
                    }

                    double no = row.getCell(0).getNumericCellValue();
                    String id = row.getCell(1).getStringCellValue();
                    String name = row.getCell(2).getStringCellValue();
                    String strClass = "";
                    String mobile = "";
                    String email = "";

                    cell = row.getCell(3);
                    if (cell != null) {
                        strClass = cell.getStringCellValue();
                    }

                    cell = row.getCell(4);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        mobile = cell.getStringCellValue();
                    }

                    cell = row.getCell(5);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        email = cell.getStringCellValue();
                    }

                    Map<String, Object> record = new HashMap<String, Object>();
                    record.put("id", id);
                    record.put("name", name);
                    record.put("gender", null);
                    record.put("class", strClass);
                    record.put("mobile", mobile);
                    record.put("email", email);

                    contacts.add(record);

                }

                book.close();
                fis.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String info = request.getParameter("info");
        String pagestr=request.getParameter("page");
        int nowpage=pagestr==null?1: Integer.parseInt(pagestr);
        int pagelen= Integer.parseInt(request.getParameter("pagelen"));
        System.out.println(info);
        boolean isFind=false;

        String infoStr = "";//接取学生信息
        List<String> infostr=new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            infoStr = Tool.MapValueSettoString(contacts.get(i));
            if (null != infoStr && infoStr.indexOf(info) != -1) {
                Map map=contacts.get(i);
                String htmlstr="<tr>\n" +
                        "<td class=\"w20\">"+map.get("id")+"</td>" +
                        "<td class=\"w20\">"+map.get("name")+"</td>" +
                        "<td class=\"w20\">"+map.get("gender")+"</td>" +
                        "<td class=\"w20\">"+map.get("class")+"</td>" +
                        "<td class=\"w20\">"+map.get("mobile")+"</td>" +
                        "<td class=\"w20\">"+map.get("email")+"</td>" +
                        "</tr>\n";
                infostr.add(htmlstr);

                isFind=true;
            }
        }
        //sql generate
        //System.out.println(ContactstoSQL1(contacts));
        out.println("<head>\n" +
                "    <title>软院找人</title>\n" +
                "    <link rel=\"icon\" href=\"/image/xhxh.png\">\n" +
                "    <link rel=\"stylesheet\" href=\"/css/index.css\">\n" +
                "</head>");
        if (!isFind){
            String a = URLEncoder.encode("暂无数据", "UTF-8");
            out.println("<script language='javascript'>alert(decodeURIComponent('"+a+"') );</script>");

            request.setAttribute("info","1");
        }

        int infosize=infostr.size();

        int pagenum=infosize/pagelen+(infosize%pagelen==0?0:1);

        if(nowpage>pagenum){
            String a = URLEncoder.encode("无当前页", "UTF-8");
            out.println("<script language='javascript'>alert(decodeURIComponent('"+a+"') );</script>");

            request.setAttribute("page","1");
        }
        out.println("<div class=\"result\">" +
                "            <table id=\"tbl_stu\">\n" +
                "<tr>\n" +
                "<th class=\"w20\">学号</th>" +
                "<th class=\"w20\">姓名</th>" +
                "<th class=\"w20\">性别</th>" +
                "<th class=\"w20\">班级</th>" +
                "<th class=\"w20\">手机号</th>" +
                "<th class=\"w20\">邮箱</th>" +
                "</tr>\n"
        );
        for(int i=(nowpage-1)*pagelen;nowpage==1?i<infosize&&i<pagelen:i<infosize&&i<nowpage*pagelen;i++){
            out.println(infostr.get(i));
        }
        out.println(" </table>" +
                "</div>");

        out.println("<form action=\"find\" method=\"get\">\n" +
                "            <table>\n" +
                "                <tr>\n" +
                "                    <td id=\"lll\">\n" +
                "                        <input type=\"text\" name=\"info\" id=\"info\" value=\"" +info+"\" placeholder=\"查询信息\" class=\"l20\" style=\"display:none;\"/>\n" +
                "                        <input type=\"text\" name=\"page\" id=\"page\" value=\"1\" style=\"display:none;\">\n" +
                "                    </td>\n" +
                "                    <td>\n" +
                "                        <img src=\"/image/sid.png\" alt=\"页长\" width=\"30px\" id=\"topimg\" style=\"margin-left: 25px;\">\n" +
                "                    </td>\n" +
                "                    <td>\n" +
                "                        <input type=\"text\" name=\"pagelen\" value=\""+pagelen+"\" placeholder=\"页长\" class=\"l20\" />\n" +
                "                    </td>\n" +
                "                    <div style=\"text-align: right;\">\n" +
                "                        <th colspan=\"2\">\n" +
                "                            <input type=\"submit\" class=\"btn1\" value=\"更新页长\">\n" +
                "                            <input type=\"reset\" class=\"btn2\" value=\"重填\" />\n" +
                "                        </th>\n" +
                "                    </div>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </form>");
        out.println("<div class=\"pagelink\">\n");
        out.println("<p class=\"link\">"+"查到" +infosize +"条数据"+"</p>");
        out.println("<a class=\"link\" href=\"http://localhost:8080/"+"\">首页</a>");
        out.println("<a class=\"link\" href=\"http://localhost:8080/find?info="+info+"&page="+1+"&pagelen="+pagelen+"\">第一页</a>");
        out.println("<a class=\"link\" href=\"http://localhost:8080/find?info="+info+"&page="+(nowpage>1?nowpage-1:1)+"&pagelen="+pagelen+"\">上一页</a>");
        out.println("<a class=\"link\" href=\"http://localhost:8080/find?info="+info+"&page="+(nowpage<pagenum?nowpage+1:pagenum)+"&pagelen="+pagelen+"\">下一页</a>");
        out.println("<a class=\"link\" href=\"http://localhost:8080/find?info="+info+"&page="+pagenum+"&pagelen="+pagelen+"\">尾页(第"+pagenum+"页)</a>");
        out.println("</div>");




    }








}
