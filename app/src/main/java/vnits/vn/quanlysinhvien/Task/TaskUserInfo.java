package vnits.vn.quanlysinhvien.Task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;

import vnits.vn.quanlysinhvien.API.SettingApi;
import vnits.vn.quanlysinhvien.Database.MyDatabaseAccess;
import vnits.vn.quanlysinhvien.R;
import vnits.vn.quanlysinhvien.broadcast.ConnectivityReceiver;
import vnits.vn.quanlysinhvien.model.Users;

/**
 * Created by Deleting on 6/6/2017.
 */


public class TaskUserInfo {
    Activity activity;
    TextView txtifHoTen,txtifMssv,txtifphone,txtifBir,txtifPlaceOfBirth,txtifGender,txtifNation,txtifAddress,txtifClass;
    String UrlStudentInfo;
    MyDatabaseAccess myDatabaseAccess;
    boolean checkconn;
    ConnectivityReceiver sconn;
    dowloadJson getJson;
    ProgressDialog Dialog;
    public void CreatedStudenInfo(Activity getActivity) {
        activity = getActivity;
        txtifHoTen = (TextView) activity.findViewById(R.id.txtifHoTen);
        txtifMssv = (TextView) activity.findViewById(R.id.txtifMssv);
        txtifphone = (TextView) activity.findViewById(R.id.txtifphone);
        txtifBir = (TextView) activity.findViewById(R.id.txtifBir);
        txtifPlaceOfBirth = (TextView) activity.findViewById(R.id.txtifPlaceOfBirth);
        txtifGender = (TextView) activity.findViewById(R.id.txtifGender);
        txtifNation = (TextView) activity.findViewById(R.id.txtifNation);
        txtifAddress = (TextView) activity.findViewById(R.id.txtifAddress);
        txtifClass = (TextView) activity.findViewById(R.id.txtifClass);
        myDatabaseAccess = new MyDatabaseAccess(activity);
        sconn =  new ConnectivityReceiver();
        checkconn = sconn.isConnected(activity);
         getJson = new dowloadJson();
    }
    public void GetStudentInfo(){
        Users users = myDatabaseAccess.GetToken();
        String mssv = users.getMssv();
        String tokens  = users.getTokens();

        UrlStudentInfo = SettingApi.GetStudentInfo(mssv, tokens);
        Log.d("test","url get info: " + UrlStudentInfo);
        if(checkconn == true){
            activity.runOnUiThread((new Runnable() {
                @Override
                public void run() {
                    Dialog = new ProgressDialog(activity);
                    Dialog.setTitle("Đang tải giữ liệu");
                    Dialog.setMessage("Loading...");
                    Dialog.show();
                    // TODO Auto-generated method stub
                    new AsytaskGet().execute(UrlStudentInfo);

                }
            }));
        }
    }
    public  class AsytaskGet extends AsyncTask<String, Integer, String> {

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
            ExGetInfo(res);
            Dialog.dismiss();
        }
    }
    public void ExGetInfo(String  input){
        if (input.isEmpty()) {
           return;
        }
        try {
            JSONObject obj = new JSONObject(input);
            int code = obj.getInt("status");
            if(code ==200){
                JSONObject myobj = obj.getJSONObject("data");
                String HoTen = myobj.getString("ho_dem") + " " + myobj.getString("ten");
                String MSSV = myobj.getString("student_id");
                String SDT = myobj.getString("dien_thoai");
                String NgaySinh = myobj.getString("ngay_sinh");
                String QueQuan = myobj.getString("dc_dia_chi");
                String DanToc = myobj.getString("dan_toc");

                txtifHoTen.setText(HoTen);
                txtifMssv.setText(MSSV);
                txtifphone.setText(SDT);
                txtifBir.setText(NgaySinh);
                txtifPlaceOfBirth.setText(QueQuan);
                txtifNation.setText(DanToc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
