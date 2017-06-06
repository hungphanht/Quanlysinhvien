package vnits.vn.quanlysinhvien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;

import vnits.vn.quanlysinhvien.Database.MyDatabaseAccess;

/**
 * Created by Phong on 9/5/2017.
 */

public class SplashScreenActivity extends Activity {
    private static String TAG = "WaitActivity";
    private static int SPLASH_TIME_OUT = 2000;
    MyDatabaseAccess myDatabaseAccess;
    int checklogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        myDatabaseAccess = new MyDatabaseAccess(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        checklogin =  myDatabaseAccess.getCountToken();
        Log.d(TAG,"check đăng nhập: " + checklogin);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(checklogin == 0) {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                }else if(checklogin == 1){
                    startActivity(new Intent(SplashScreenActivity.this, ViewPaperActivity.class));
                }else{
                    Log.d(TAG,"đã xảy ra lỗi");
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
        onStartAnimation();
    }

    private void onStartAnimation() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
