package com.kurisu.skill.util;

public class NumberInsert {
    public static String insert(int var, int point) {
        return insert(Integer.toString(var), point);
    }
    public static String insert(long var, int point) {
        return insert(Long.toString(var), point);
    }
    private static String insert(String str, int point) {
        return str.length() == 3
                ? new StringBuilder(str).insert(0, "0").insert(1, ".").toString()
                : new StringBuilder(str).insert(str.length()-point, ".").toString();
    }
}
