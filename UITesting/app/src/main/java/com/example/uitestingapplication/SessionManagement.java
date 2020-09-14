package com.example.uitestingapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.uitestingapplication.db.entity.Registration;

public class SessionManagement {
    ImageConverter imageConverter = new ImageConverter();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String SHARED_PREF_NAME = "session";
    private String session_user_id = "session_user_id";
    private String session_user_name = "session_user_name";
    private String session_user_image = "session_user_image";
    private String session_user_email= "session_user_email";
    Registration registration = new Registration();

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Log.i("i","test");
    }


    public void saveSession(Registration user){
        //save session of user whenever user is logged in
        int id = user.getId().intValue();
      ;

        editor.putInt(session_user_id,id).commit();
        editor.putString(session_user_name,  user.getName()).commit();
        editor.putString(session_user_email,  user.getEmail()).commit();

    }

    public int getUserIdBySession(){
        //return user id whose session is saved
        return sharedPreferences.getInt(session_user_id, -1);
    }

    public String getUserNameBySession()
    {
        return sharedPreferences.getString(session_user_name,"-1");
    }

    public String getUserEmailBySession()
    {
        return sharedPreferences.getString(session_user_email,"-1");
    }


    public void removeSession(){
        editor.putInt(session_user_id,-1).commit();
    }

}
