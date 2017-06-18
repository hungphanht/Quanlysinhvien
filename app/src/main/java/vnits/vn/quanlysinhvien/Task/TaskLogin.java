package vnits.vn.quanlysinhvien.Task;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import vnits.vn.quanlysinhvien.API.SettingApi;
import vnits.vn.quanlysinhvien.Database.MyDatabaseAccess;
import vnits.vn.quanlysinhvien.R;
import vnits.vn.quanlysinhvien.ViewPaperActivity;
import vnits.vn.quanlysinhvien.broadcast.ConnectivityReceiver;
import vnits.vn.quanlysinhvien.model.Users;

/**
 * Created by Deleting on 5/31/2017.
 */

public class TaskLogin {
    Activity activity;
    AppCompatEditText txtmssv, txtpass;
    Button btnLogin;
    String UrlLogin, tokens, mess, Username, Password;
    boolean checkconn;
    AppCompatCheckBox rembox;
    MyDatabaseAccess myDatabaseAccess;
    ConnectivityReceiver sconn;
    ACProgressFlower progressDialog;

    public void Login(Activity getActivity) {
        activity = getActivity;
        btnLogin = (Button) activity.findViewById(R.id.btn_login);
        txtmssv = (AppCompatEditText) activity.findViewById(R.id.edtUser);
        txtpass = (AppCompatEditText) activity.findViewById(R.id.etPassword);
        myDatabaseAccess = new MyDatabaseAccess(activity);
        rembox = (AppCompatCheckBox) activity.findViewById(R.id.cbSave);
        sconn = new ConnectivityReceiver();
        int slUser = myDatabaseAccess.getCountUser();
        Log.d("test", "so luong " + slUser);
        if (slUser > 0) {
            Users getUser = myDatabaseAccess.getUser();
            Log.d("test","luc get: user" + getUser.getMssv() + "pass: " + getUser.getPassword() + "trangthai" + getUser.getTrangthai());
            String trangthai = getUser.getTrangthai();
            Log.d("test","trangthai :" + trangthai);
            if (trangthai .equals("true")) {
                Username = getUser.getMssv();
                Password = getUser.getPassword();
                rembox.setChecked(true);
            }else {
                Username ="";
                Password="";
            }
            txtmssv.setText(Username);
            txtpass.setText(Password);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = new Users();
                checkconn = sconn.isConnected(activity);
                Username = txtmssv.getText().toString();
                Password = txtpass.getText().toString();
                UrlLogin = SettingApi.getLogin(Username, Password);
                if (Username.length() == 0 | Password.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialogTheme);
                    builder.setTitle(Html.fromHtml("<font color='#FF7F27'>Lỗi</font>"));
                    builder.setMessage(Html.fromHtml("<font color='#FF7F27'>Bạn chưa nhập mã số sinh viên hoặc mật khẩu ?</font>"));
                    builder.setCancelable(false);
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    if (checkconn == true) {
                        dowloadJson getJson = new dowloadJson();
                        try {
                            String input = getJson.downloadUrl(UrlLogin);
                            Log.d("test",input);
                            boolean check = ExcLogin(input);
                            if (check == true) {
                                users.setMssv(Username);
                                users.setPassword(Password);
                                if (rembox.isChecked()) {
                                    Log.d("test","co check");
                                    users.setTrangthai("true");
                                } else {
                                    users.setTrangthai("false");
                                    Log.d("test","ko check");
                                }
                                boolean add = myDatabaseAccess.addUser(users);
                                if (add == true) {
                                    Log.d("test", "Adđ acount success");
                                } else {
                                    Log.d("test", "Adđ acount fail");
                                }
                                boolean addtoken = myDatabaseAccess.addToken(Username,tokens);
                                if (addtoken == true) {
                                    Log.d("test", "Add token success");
                                } else {
                                    Log.d("test", "Add token fail");
                                }
                                progressDialog = new ACProgressFlower.Builder(activity)
                                        .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                                        .themeColor(Color.WHITE)
                                        .text("Loading")
                                        .fadeColor(Color.DKGRAY).build();
                                progressDialog.show();
                                Intent intent = new Intent(activity, ViewPaperActivity.class);
                                activity.startActivity(intent);
                                activity.finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialogTheme);
                                builder.setTitle(Html.fromHtml("<font color='#FF7F27'>Lỗi</font>"));
                                builder.setMessage(Html.fromHtml("<font color='#FF7F27'>Mã Số sinh viên hoặc mật khẩu không đúng ?</font>"));
                                builder.setCancelable(false);
                                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialogTheme);
                        builder.setTitle(Html.fromHtml("<font color='#FF7F27'>Lỗi</font>"));
                        builder.setMessage(Html.fromHtml("<font color='#FF7F27'>Không có kết nối mạng ?</font>"));
                        builder.setCancelable(false);
                        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
            }
        });
    }

    public boolean ExcLogin(String input) {

        boolean status = false;
        if (input.isEmpty()) {
            status = false;
        }
        try {
            JSONObject obj = new JSONObject(input);
            int code = obj.getInt("status");
            Log.d("test", "trang thai: " + code);
            if (code == 200) {
                status = true;
                tokens = obj.getString("data");
            } else if(code == 500){
                mess = obj.getString("error");
            }else {
                mess = obj.getString("message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

}
