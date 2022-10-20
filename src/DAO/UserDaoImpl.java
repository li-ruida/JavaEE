package DAO;

import Bean.Info;
import Bean.User;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-10-13 下午5:52
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public List<User> getUser() {
        String sql="select * from userpassword";
        List beanList = BaseDao.getForList(User.class, sql);
        return beanList;
    }

    @Override
    public void saveUser(Connection conn, User user) {
        String sql="insert into userpassword VALUES (?,?,?)";
        BaseDao.update(conn,sql,user.getId(),user.getPassword(),user.getImg());
    }
/*
create table userpassword(
    id varchar(20),
    password varchar(20),
    img varchar(20),
    primary key (id)
);
 */
    @Override
    public void deleteUserById(Connection conn, String id) {
        String sql="delete from userpassword where id = ?";
        BaseDao.update(conn,sql,id);
    }

    @Override
    public User getUserById(Connection conn, String id) {
        String sql="select * from userpassword where id = ?";
        return BaseDao.getInstance(User.class,sql,id);
    }

    @Override
    public void updateUser(Connection conn, User user) {
        String sql="update userpassword set id=? ,password=? ,img=? where id=?";
        BaseDao.update(conn,sql,user.getId(),user.getPassword(),user.getImg(),user.getId());
    }
}
