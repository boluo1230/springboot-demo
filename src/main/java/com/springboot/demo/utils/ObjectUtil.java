package com.springboot.demo.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public class ObjectUtil {

    public static HashMap<String, Object> beanToMapExcludeNull(Object object) {
        return beanToMap(object, true);
    }

    public static HashMap<String, Object> beanToMap(Object object) {
        return beanToMap(object, false);
    }

    private static HashMap<String, Object> beanToMap(Object object, boolean excludeNull) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (object != null) {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                char[] chars = name.toCharArray();
                chars[0] ^= 32;
                String methodName = String.format("get%s", String.valueOf(chars));
                Object value = null;
                try {
                    value = object.getClass().getDeclaredMethod(methodName).invoke(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!excludeNull || value != null) {
                    hashMap.put(name, value);
                }
            }
        }
        return hashMap;
    }

    public static long numberToTimeStamp(Integer time) {
        long result = 0L;
        if (time != null) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                Date date = simpleDateFormat.parse(time.toString());
                result = date.getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static Object getValue(Field field, Object object) {
        Object value = getValueFromGetter(field, object);
        return value == null ? getValueAccessible(field, object) : value;
    }

    private static Object getValueFromGetter(Field field, Object object) {
        String name = field.getName();
        char[] chars = name.toCharArray();
        chars[0] ^= 32;
        String methodName = String.format("get%s", String.valueOf(chars));
        Object value = null;
        try {
            value = object.getClass().getDeclaredMethod(methodName).invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private static Object getValueAccessible(Field field, Object object) {
        field.setAccessible(true);
        Object value = null;
        try {
            value = field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static List<Object> getArrayFromObject(Object object) {
        if (object.getClass().isArray() && Array.getLength(object) > 0) {
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < Array.getLength(object); i++) {
                list.add(Array.get(object, i));
            }
            return list;
        }
        return null;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
