package vnits.vn.quanlysinhvien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import vnits.vn.quanlysinhvien.Task.TaskDetailScore;
import vnits.vn.quanlysinhvien.config.ConfigApplication;


/**
 * Created by Phong on 9/5/2017.
 */

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_score);
        LinearLayout btnBack = (LinearLayout) findViewById(R.id.btnback);
        TaskDetailScore taskDetailScore = new TaskDetailScore();
        taskDetailScore.CreatedScoreDetail(this);
        taskDetailScore.GetDetailScore();
        btnBack.setOnClickListener(new View.OnClickListener() {
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
        Intent intent = new Intent(DetailActivity.this, ViewPaperActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(ConfigApplication.DATA_DIRECT_TRANSFER, ConfigApplication.REQUEST_DIRECT_SCORE);
        intent.putExtra(ConfigApplication.BUNDLE_DATA_TRANSFER, bundle);
        startActivity(intent);
        finish();
    }
}
