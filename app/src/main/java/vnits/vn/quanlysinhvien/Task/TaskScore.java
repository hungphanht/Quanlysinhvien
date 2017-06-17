package vnits.vn.quanlysinhvien.Task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import vnits.vn.quanlysinhvien.API.SettingApi;
import vnits.vn.quanlysinhvien.Database.MyDatabaseAccess;
import vnits.vn.quanlysinhvien.DetailActivity;
import vnits.vn.quanlysinhvien.R;
import vnits.vn.quanlysinhvien.adapter.RecycleAdapter;
import vnits.vn.quanlysinhvien.broadcast.ConnectivityReceiver;
import vnits.vn.quanlysinhvien.custom.RecyclerItemClickListener;
import vnits.vn.quanlysinhvien.model.Score;
import vnits.vn.quanlysinhvien.model.Users;

public class TaskScore {
    RecyclerView recyclerView;
    RecycleAdapter adapter;
    private ArrayList<Score> arrdata = new ArrayList<Score>();
    MyDatabaseAccess myDatabaseAccess;
    Activity activity;
    boolean checkconn;
    ConnectivityReceiver sconn;
    dowloadJson getJson;
    ProgressDialog Dialog;
    String UrlSCore;

    public void CreatedScore(Activity getActivity) {
        activity = getActivity;
        myDatabaseAccess = new MyDatabaseAccess(activity);
        sconn = new ConnectivityReceiver();
        checkconn = sconn.isConnected(activity);
        getJson = new dowloadJson();
        recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerViewList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleAdapter(arrdata);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(activity, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Bundle bundle = new Bundle();
                        bundle.putInt("id",arrdata.get(position).getId());
                        Intent intent = new Intent(activity, DetailActivity.class);
                        intent.putExtra("data",bundle);
                        activity.startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                      Toast.makeText(activity,"Bạn không thể chọn nhiều items",Toast.LENGTH_LONG).show();
                    }

                })
        );
    }

    public void getScore() {
        Users users = myDatabaseAccess.GetToken();
        String mssv = users.getMssv();
        String tokens = users.getTokens();
        UrlSCore = SettingApi.GetScore(mssv, tokens);
        if (checkconn == true) {
            activity.runOnUiThread((new Runnable() {
                @Override
                public void run() {
                    Dialog = new ProgressDialog(activity);
                    Dialog.setTitle("Đang tải giữ liệu");
                    Dialog.setMessage("Loading...");
                    Dialog.show();
                    // TODO Auto-generated method stub
                    new TaskScore.AsytaskGet().execute(UrlSCore);
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
                JSONArray arr = obj.getJSONArray("data");
                for (int i = 0; i < arr.length(); i++) {
                    Score tmp = new Score();
                    JSONObject myobj = arr.getJSONObject(i);
                    int id = myobj.getInt("subject_id");
                    String tenmonhoc = myobj.getString("name");
                    double diemtrungbinh = myobj.getDouble("avg");
                    String trangthai = myobj.getString("state");
                    tmp.setId(id);
                    tmp.setMonhoc(tenmonhoc);
                    tmp.setDtb(diemtrungbinh);
                    tmp.setTrangthai(trangthai);
                    arrdata.add(tmp);
                }

                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
