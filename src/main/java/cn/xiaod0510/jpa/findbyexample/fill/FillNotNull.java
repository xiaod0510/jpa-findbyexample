package cn.xiaod0510.jpa.findbyexample.fill;

import cn.xiaod0510.jpa.findbyexample.BaseExample;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaod0510@gmail.com on 16-11-30 下午12:37.
 */
public class FillNotNull implements FillCondition {
    private static final Pattern pattern = Pattern.compile("(.+)([A-Z][a-z]+)");
    private static Map<String, BaseExample.PredicateType> predicateTypeMap = new HashMap<String, BaseExample.PredicateType>();

    static {
        for (BaseExample.PredicateType type : BaseExample.PredicateType.values()) {
            char[] nameChars = type.name().toCharArray();
            nameChars[0] = Character.toUpperCase(nameChars[0]);
            String name = new String(nameChars);
            predicateTypeMap.put(name, type);
        }
    }

    public void fill(BaseExample condiction, Object pojo) {
        if (pojo == null) return;

        Field[] fields = pojo.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                //skip static field
                if(Modifier.isStatic(field.getModifiers())){
                    continue;
                }
                Object value = field.get(pojo);
                if (value == null) {
                    continue;
                }
                if (value instanceof String && ((String) value).length() == 0) {
                    continue;
                }
                String fieldName = field.getName();
                String typeName = "Eq";
                Matcher matcher = pattern.matcher(fieldName);
                if (matcher.find()) {
                    fieldName = matcher.group(1);
                    typeName = matcher.group(2);
                }
                //type首字母大写,若为空则默认为equals
                BaseExample.PredicateType predicateType = predicateTypeMap.get(typeName);
                if (predicateType == null) {
                    predicateType = BaseExample.PredicateType.eq;
                    fieldName = field.getName();
                }

                condiction.add(fieldName, predicateType, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
