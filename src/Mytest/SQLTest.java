package Mytest;

import Bean.Info;
import DAO.InfoDao;
import DAO.InfoDaoImpl;
import Service.Tool;
import org.junit.Test;

import java.util.List;

/**
 * @author lrd
 * @date 2022-10-04 上午9:04
 */
public class SQLTest {
    @Test
    public void test(){
        InfoDao infoDao=new InfoDaoImpl();
        List<Info> testinfo=infoDao.getInfo();
        for (Info i:testinfo) {
            System.out.println(i);
        }
    }
}
