package DAO;



import Bean.Info;

import java.sql.Connection;
import java.util.List;

public interface InfoDao {
    List<Info> getInfo();

    void saveInfo( Connection conn,Info info);

    void deleteInfoById(Connection conn, String id);//删除

    Info getInfoById(Connection conn,String id);//根据Id查

    void updateInfo(Connection conn,Info info);//更新信息
}
