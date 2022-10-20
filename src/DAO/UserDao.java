package DAO;


import Bean.User;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-10-13 下午4:47
 */
public interface UserDao {
    List<User> getUser();

    void saveUser(Connection conn, User user);

    void deleteUserById(Connection conn, String id);//删除

    User getUserById(Connection conn,String id);//根据Id查

    void updateUser(Connection conn,User user);//更新信息
}
