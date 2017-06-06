package vnits.vn.quanlysinhvien.API;

import android.util.Log;

/**
 * Created by Deleting on 5/31/2017.
 */

public class SettingApi {
    private static final String API_LOGIN ="login.json?";
    public static String getBaseUrl(String api) {
        return "http://111.221.108.229/marks/" + api;
    }
    public static String getLogin(String username, String password) {

        String url = new StringBuilder(String.valueOf(getBaseUrl(API_LOGIN))).append("mssv=").append(username).append("&pass=").append(password).toString();
        Log.d("test", "url:" + url.toString());
        return url;
    }
}
