package Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author lrd
 * @date 2022-08-17 上午9:40
 */
public class JDBCUtils {
    //获取数据库连接
    public static Connection getConnection() throws IOException {
        Properties pro = new Properties();
        pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
        DataSource ds = null;
        try {
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    public static void closeResource(Connection conn, PreparedStatement ps){
        try{
            if (ps!=null)
                ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet resultSet){
        try{
            if (ps!=null)
                ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
