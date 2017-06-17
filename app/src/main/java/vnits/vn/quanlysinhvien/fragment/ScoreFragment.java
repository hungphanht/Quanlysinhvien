package vnits.vn.quanlysinhvien.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vnits.vn.quanlysinhvien.DetailActivity;
import vnits.vn.quanlysinhvien.R;
import vnits.vn.quanlysinhvien.Task.TaskScore;
import vnits.vn.quanlysinhvien.adapter.RecycleAdapter;
import vnits.vn.quanlysinhvien.custom.RecyclerItemClickListener;
import vnits.vn.quanlysinhvien.model.Score;

public class ScoreFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);
        return rootView;
    }

    public void onViewCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        TaskScore taskScore = new TaskScore();
        taskScore.CreatedScore(getActivity());
        taskScore.getScore();
    }

}
