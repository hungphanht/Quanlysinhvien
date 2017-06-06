package vnits.vn.quanlysinhvien;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import vnits.vn.quanlysinhvien.Task.TaskLogin;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_login);
        TaskLogin taskLogin = new TaskLogin();
        taskLogin.Login(LoginActivity.this);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
