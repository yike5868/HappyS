package com.zlin.happys.utils;

public class StringUtils {
    public static boolean isNotEmpty(String str){
      if(str == null || "".equals(str.trim())){
          return false;
      }else{
          return true;
      }
    }
}
