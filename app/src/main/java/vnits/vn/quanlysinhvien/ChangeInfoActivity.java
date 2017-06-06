package vnits.vn.quanlysinhvien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import vnits.vn.quanlysinhvien.config.ConfigApplication;


/**
 * Created by Phong on 9/5/2017.
 */

public class ChangeInfoActivity extends Activity {

    Button btnYes, btnNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        eventClick();
    }

    public void eventClick() {
        btnYes = (Button) findViewById(R.id.btnYesInfo);
        btnNo = (Button) findViewById(R.id.btnNoInfo);

        LinearLayout btnBack = (LinearLayout) findViewById(R.id.btnback);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveActivity();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveActivity();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveActivity();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        moveActivity();
        super.onBackPressed();
    }

    public void moveActivity() {
        Intent intent = new Intent(ChangeInfoActivity.this, ViewPaperActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(ConfigApplication.DATA_DIRECT_TRANSFER, ConfigApplication.REQUEST_DIRECT_DEFAULT);
        intent.putExtra(ConfigApplication.BUNDLE_DATA_TRANSFER, bundle);
        startActivity(intent);
        finish();
    }
}
