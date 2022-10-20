package DAO;




import Bean.Info;

import java.sql.Connection;
import java.util.List;

public class InfoDaoImpl extends BaseDao<Info> implements InfoDao {

    @Override
    public List<Info> getInfo() {
        String sql="select * from info";
        List beanList = BaseDao.getForList(Info.class, sql);
        return beanList;
    }
/*
    id varchar(20),
    name varchar(10),
    gender varchar(10),
    iclass varchar(10),
    mobile varchar(20),
    email varchar(20),
 */
    @Override
    public void saveInfo(Connection conn,Info info) {
        String sql="insert into info VALUES (?,?,?,?,?,?)";
        BaseDao.update(conn,sql,info.getId(),info.getName(),info.getGender(),info.getIclass(),info.getMobile(),info.getEmail());
    }

    @Override
    public void deleteInfoById(Connection conn, String id) {
        String sql="delete from info where id = ?";
        BaseDao.update(conn,sql,id);
    }

    @Override
    public Info getInfoById(Connection conn, String id) {
        String sql="select * from info where id = ?";
        return BaseDao.getInstance(Info.class,sql,id);
    }
    @Override
    public void updateInfo(Connection conn, Info info) {
        String sql="update info set id=? ,name=? ,gender=? ,iclass=? ,mobile=?, email=? where id=?";
        BaseDao.update(conn,sql,info.getId(),info.getName(),info.getGender(),info.getIclass(),info.getMobile(),info.getEmail(),info.getId());
    }
}
