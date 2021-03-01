package com.example.gymmanagement;

import android.text.TextUtils;
import android.util.Patterns;

public class Constants {

    public static final String TIMESTAMP = "timestamp";
    public static final String TEXT = "text";
    public static final String SENDER = "sender";
    public static final String PREFNAME = "pref";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_TYPE = "type";
    public static final String USER_NAME = "name";
    public static final String USER_MOBILE = "mobile";
    public static final String USER_LIST_TYPE = "userlisttype";
    public static final String USERS_LIST_BRANCH = "users";
    public static final String KEY_OTHER_PARTY_EMAIL = "other_user_email";
    public static final String USER_ID = "user_id";
    public  static  String ROOM_NAME ="roomname";
    public  static String ROOM_KEY = "room";

    public static String getChatHead(String uid1, String uid2)
    {

        if(uid1.compareTo(uid2) > 0){
            return uid1+uid2;
        }
        else{
            return uid2+uid1;
        }

    }

    public static String getNewChatHead(String usertype,String uid1, String uid2)
    {

        if(usertype.equals("user")){
            return "user"+uid1+"trainer"+uid2;
        }
        else{
            return "user"+uid2+"trainer"+uid1;
        }

    }




    public static boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
