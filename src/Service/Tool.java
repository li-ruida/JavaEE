

/**
 * @author lrd
 * @date 2022-10-03 下午1:02
 */
package Service;

import java.io.*;
import java.util.*;

public class Tool {

    public static String MapValueSettoString(Map map) {
        if (map != null) {
            String info = "";
            Collection ValueSet = map.values();
            String name = map.get("name").toString();
            if (name.contains("*")) {
                map.replace("gender", "女");
                map.replace("name", name.subSequence(0, name.length() - 1));//去除*
            } else if(map.get("gender")==null&&!name.contains("*")){
                map.replace("gender", "男");
            }
            info=""+map.get("id")+map.get("name")+map.get("gender")+map.get("class")+map.get("mobile")+map.get("email");
            return info;
        } else {
            return "";
        }
    }

        public static String ContactstoSQL(List<Map<String, Object>> contacts) throws IOException {
            String info = "";
            for (Map map:contacts) {
                if (map != null) {
                    Collection ValueSet = map.values();
                    String name = map.get("name").toString();
                    if (name.contains("*")) {
                        map.replace("gender", "女");
                        map.replace("name", name.subSequence(0, name.length() - 1));//去除*
                    } else if(map.get("gender")==null&&!name.contains("*")){
                        map.replace("gender", "男");
                    }
                    info+="(" +"\'"+
                            map.get("id")+"\'"+"," +"\'"+
                            map.get("name")+"\'"+"," +"\'"+
                            map.get("gender")+"\'"+"," +"\'"+
                            map.get("class")+"\'"+"," +"\'"+
                            map.get("mobile")+"\'"+"," +"\'"+
                            map.get("email")+"\'"+
                            "),";

                }
            }
            return info;

        }
    public static String ContactstoSQL1(List<Map<String, Object>> contacts) throws IOException {
        String info = "";
        for (Map map:contacts) {
            if (map != null) {
                Collection ValueSet = map.values();
                String name = map.get("name").toString();
                if (name.contains("*")) {
                    map.replace("gender", "女");
                    map.replace("name", name.subSequence(0, name.length() - 1));//去除*
                } else if(map.get("gender")==null&&!name.contains("*")){
                    map.replace("gender", "男");
                }
                info+="(" +"\'"+
                        map.get("id")+"\'"+"," +"\'"+ "123456"+"\'"+"," +"\'"+"\'"+
                        "),";

            }
        }
        return info;

    }

}
