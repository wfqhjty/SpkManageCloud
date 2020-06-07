package cn.spk.common.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonUtil {

    /**
     * 对字符串，数组，List，Map，Set进行非空判断
     *
     * @param object
     * @return
     */
    public static boolean isNullOrEmpty(Object object) {

        if (object instanceof Object[]) {
            Object[] objects = (Object[]) object;
            if (objects == null || objects.length == 0) {
                return true;
            }
            return false;
        } else if (object instanceof Map) {
            Map map = (Map) object;
            if (map == null || map.size() == 0) {
                return true;
            } else return false;
        } else if (object instanceof List) {
            List list = (List) object;
            if (list == null || list.size() == 0) {
                return true;
            }
            return false;
        } else if (object instanceof Set) {
            Set set = (Set) object;
            if (set == null || set.size() == 0) {
                return true;
            }
            return false;
        } else if (object instanceof String) {
            String str = (String) object;
            if (str == null || str.length() == 0) {
                return true;
            }
            return false;
        }
        return false;
    }

}
