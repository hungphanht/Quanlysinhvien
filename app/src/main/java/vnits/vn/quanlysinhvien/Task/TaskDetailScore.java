package vnits.vn.quanlysinhvien.Task;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import vnits.vn.quanlysinhvien.API.SettingApi;
import vnits.vn.quanlysinhvien.Database.MyDatabaseAccess;
import vnits.vn.quanlysinhvien.R;
import vnits.vn.quanlysinhvien.broadcast.ConnectivityReceiver;
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
    String UrlDetailSCore;
    Intent intent;
    Bundle bundle;
    int idMonhoc;
    ACProgressFlower progressDialog;

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
                    progressDialog = new ACProgressFlower.Builder(activity)
                            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                            .themeColor(Color.WHITE)
                            .text("Loading")
                            .fadeColor(Color.DKGRAY).build();
                    progressDialog.show();
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
            progressDialog.dismiss();
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
                    String TenMonHoc,Dinhky1,Dinhky2,Dinhky3,Dinhky4,Dinhky5,Dinhky6,Dinhky7,KetThuc1,KetThuc2,TrungBinh;

                    TenMonHoc = myobj.getString("name");
                    txtTenMonHoc.setText(TenMonHoc);

                    if(myobj.isNull("dk_1")) {
                        Dinhky1 = null;
                        txtDk1.setText("Chưa Có Điểm");
                    } else {
                        Dinhky1 = myobj.getString("dk_1");
                        txtDk1.setText(Dinhky1.toString());
                    }

                    if(myobj.isNull("dk_2")) {
                        Dinhky2 = null;
                        txtDk2.setText("Chưa Có Điểm");
                    } else {
                        Dinhky2 = myobj.getString("dk_2");
                        txtDk2.setText(Dinhky2.toString());
                    }

                    if(myobj.isNull("dk_3")) {
                        Dinhky3 = null;
                        txtDk3.setText("Chưa Có Điểm");
                    } else {
                        Dinhky3 = myobj.getString("dk_3");
                        txtDk3.setText(Dinhky3.toString());
                    }

                    if(myobj.isNull("dk_4")) {
                        Dinhky4 = null;
                        txtDk4.setText("Chưa Có Điểm");
                    } else {
                        Dinhky4 = myobj.getString("dk_4");
                        txtDk4.setText(Dinhky4.toString());
                    }

                    if(myobj.isNull("dk_5")) {
                        Dinhky5 = null;
                        txtDk5.setText("Chưa Có Điểm");
                    } else {
                        Dinhky5 = myobj.getString("dk_5");
                        txtDk5.setText(Dinhky5.toString());
                    }

                    if(myobj.isNull("dk_6")) {
                        Dinhky6 = null;
                        txtDk6.setText("Chưa Có Điểm");
                    } else {
                        Dinhky6 = myobj.getString("dk_6");
                        txtDk6.setText(Dinhky6.toString());
                    }

                    if(myobj.isNull("dk_7")) {
                        Dinhky7 = null;
                        txtDk7.setText("Chưa Có Điểm");
                    } else {
                        Dinhky7 = myobj.getString("dk_7");
                        txtDk7.setText(Dinhky7.toString());
                    }

                    if(myobj.isNull("kt_1")) {
                        KetThuc1 = null;
                        txtKt1.setText("Chưa Có Điểm");
                    } else {
                        KetThuc1 = myobj.getString("kt_1");
                        txtKt1.setText(KetThuc1.toString());
                    }

                    if(myobj.isNull("kt_2")) {
                        KetThuc2 = null;
                        txtKt2.setText("Chưa Có Điểm");
                    } else {
                        KetThuc2 = myobj.getString("kt_2");
                        txtKt2.setText(KetThuc2.toString());
                    }

                    if(myobj.isNull("avg")) {
                        TrungBinh = null;
                        txtAvg.setText("Chưa Có Điểm");
                    } else {
                        TrungBinh = myobj.getString("avg");
                        txtAvg.setText(TrungBinh.toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
