package vnits.vn.quanlysinhvien.Task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import vnits.vn.quanlysinhvien.API.SettingApi;
import vnits.vn.quanlysinhvien.Database.MyDatabaseAccess;
import vnits.vn.quanlysinhvien.R;
import vnits.vn.quanlysinhvien.broadcast.ConnectivityReceiver;
import vnits.vn.quanlysinhvien.model.Score;
import vnits.vn.quanlysinhvien.model.Users;

/**
 * Created by Deleting on 6/17/2017.
 */

public class TaskDetailScore {
    TextView txtDk1, txtDk2, txtDk3, txtDk4, txtKt1, txtKt2, txtAvg, txtTrangThai,txtDk5,txtDk6,txtDk7,txtTenMonHoc;
    MyDatabaseAccess myDatabaseAccess;
    Activity activity;
    boolean checkconn;
    ConnectivityReceiver sconn;
    dowloadJson getJson;
    ProgressDialog Dialog;
    String UrlDetailSCore;
    Intent intent;
    Bundle bundle;
    int idMonhoc;
    public void CreatedScoreDetail(Activity getActivity) {
        activity = getActivity;
        myDatabaseAccess = new MyDatabaseAccess(activity);
        sconn = new ConnectivityReceiver();
        checkconn = sconn.isConnected(activity);
        getJson = new dowloadJson();
        txtTenMonHoc = (TextView) activity.findViewById(R.id.txtMonHoc) ;
        txtDk1 = (TextView) activity.findViewById(R.id.txtDk1);
        txtDk2 = (TextView) activity.findViewById(R.id.txtDk2);
        txtDk3 = (TextView) activity.findViewById(R.id.txtDk3);
        txtDk4 = (TextView) activity.findViewById(R.id.txtDk4);
        txtDk5 = (TextView) activity.findViewById(R.id.txtDk5);
        txtDk6 = (TextView) activity.findViewById(R.id.txtDk6);
        txtDk7 = (TextView) activity.findViewById(R.id.txtDk7);
        txtKt1 = (TextView) activity.findViewById(R.id.txtKt1);
        txtKt2 = (TextView) activity.findViewById(R.id.txtKt2);
        txtAvg = (TextView) activity.findViewById(R.id.txtAvg);
//        txtTrangThai = (TextView) activity.findViewById(R.id.txtTrangThai);
        intent = activity.getIntent();
        bundle = intent.getBundleExtra("data");
        if(bundle!=null){
            idMonhoc = bundle.getInt("id");

        }else {
            Toast.makeText(activity,"Đã xảy ra lỗi",Toast.LENGTH_SHORT).show();
        }
    }

    public void GetDetailScore() {
        Users users = myDatabaseAccess.GetToken();
        String mssv = users.getMssv();
        String tokens = users.getTokens();
        UrlDetailSCore = SettingApi.GetDetailScore(mssv, tokens,idMonhoc);
        if (checkconn == true) {
            activity.runOnUiThread((new Runnable() {
                @Override
                public void run() {
                    Dialog = new ProgressDialog(activity);
                    Dialog.setTitle("Đang tải giữ liệu");
                    Dialog.setMessage("Loading...");
                    Dialog.show();
                    // TODO Auto-generated method stub
                    new TaskDetailScore.AsytaskGet().execute(UrlDetailSCore);
                }
            }));
        }
    }
    public class AsytaskGet extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String res = null;
            try {
                res = getJson.downloadUrl(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("test", res);
            return res;
        }

        protected void onPostExecute(String res) {
            ExGetScore(res);
            Dialog.dismiss();
        }
    }

    public void ExGetScore(String input) {
        if (input.isEmpty()) {
            return;
        }
        try {
            JSONObject obj = new JSONObject(input);
            int code = obj.getInt("status");
            if (code == 200) {
                JSONArray array = obj.getJSONArray("data");
                for (int i = 0; i <array.length() ; i++) {
                    JSONObject myobj = array.getJSONObject(i);
                    String TenMonHoc = myobj.getString("name");
                    String Dinhky1 = myobj.getString("dk_1");
                    String Dinhky2 = myobj.getString("dk_2");
                    String Dinhky3 = myobj.getString("dk_3");
                    String Dinhky4 = myobj.getString("dk_4");
                    String Dinhky5 = myobj.getString("dk_5");
                    String Dinhky6 = myobj.getString("dk_6");
                    String Dinhky7 = myobj.getString("dk_7");
                    String KetThuc1 = myobj.getString("kt_1");
                    String KetThuc2 = myobj.getString("kt_2");
                    String TrungBinh = myobj.getString("avg");
//                    String TrangThai = myobj.getString("dk_1");
                    txtTenMonHoc.setText(TenMonHoc);
                    txtDk1.setText(Dinhky1.toString());
                    txtDk2.setText(Dinhky2.toString());
                    txtDk3.setText(Dinhky3.toString());
                    txtDk4.setText(Dinhky4.toString());
                    txtDk5.setText(Dinhky5.toString());
                    txtDk6.setText(Dinhky6.toString());
                    txtDk7.setText(Dinhky7.toString());
                    txtKt1.setText(KetThuc1.toString());
                    txtKt2.setText(KetThuc2.toString());
                    txtAvg.setText(TrungBinh.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
