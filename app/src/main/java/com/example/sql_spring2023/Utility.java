package com.example.sql_spring2023;

public class Utility {

    public  static boolean isNumeric(String inputString){
        boolean check = true;
        char[] chars = inputString.toCharArray();
        for(char c : chars){
            if(Character.isLetter(c)){
                check = false;
            }
        }
        return check;
    }

    public  static boolean isSpecial(char c){
        boolean check = true;
            if(!Character.isLetter(c)&&!Character.isDigit(c)&&!(c==' ')){
                check = false;
            }
        return check;
    }

    public static boolean validString(String string){
        boolean check = true;
        if(string == null|| string.isEmpty()||string.trim().isEmpty()){
            check = false;
        }
        if(string!=null){
            char[] chars = string.toCharArray();
            for(int i=0;i< chars.length;i++){
                if(isSpecial(chars[i])||Character.isDigit(chars[i])){
                    check = false;
                }
            }
        }
        return check;
    }

    public static boolean validCust(String string ){
        boolean check =true;
        if(string ==null||string.isEmpty()||string.trim().isEmpty()){
            check = false;
        }
        return check;
    }

public static boolean validCred (String string){
        boolean check = true;
        if (string == null || string.isEmpty()|| string.trim().isEmpty()){
            check = false;
        }
        return check;
}












}
