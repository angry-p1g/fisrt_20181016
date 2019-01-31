package com.gq.common.util;

public class StringUtils {

    public static boolean isBlank(String arg){
        if(arg == null || "".equals(arg.trim()) || "null".equals(arg)){
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String arg){
        if(arg == null || "".equals(arg.trim()) || "null".equals(arg)){
            return false;
        }
        return true;
    }
}
