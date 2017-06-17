package vnits.vn.quanlysinhvien.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vnits.vn.quanlysinhvien.R;
import vnits.vn.quanlysinhvien.model.Score;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecyclerViewHolder> {

    private ArrayList<Score> listData = new ArrayList<>();

    public RecycleAdapter(ArrayList<Score> listData) {
        this.listData = listData;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.item_score, parent, false);
        return new RecyclerViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.txtMonHoc.setText(listData.get(position).getMonhoc());
        holder.txtTrangThai.setText(listData.get(position).getTrangthai());
        holder.txtDiem.setText(listData.get(position).getDtb().toString());
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtMonHoc,txtTrangThai,txtDiem;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtMonHoc = (TextView) itemView.findViewById(R.id.txtMonHoc);
            txtTrangThai = (TextView) itemView.findViewById(R.id.txtTrangThai);
            txtDiem = (TextView) itemView.findViewById(R.id.txtDiem);
        }

    }
}