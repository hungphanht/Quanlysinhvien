package vnits.vn.quanlysinhvien.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vnits.vn.quanlysinhvien.R;
import vnits.vn.quanlysinhvien.model.Diligence;

public class DiligenceAdapter extends RecyclerView.Adapter<DiligenceAdapter.RecyclerViewHolder> {

    private ArrayList<Diligence> listData = new ArrayList<>();

    public DiligenceAdapter(ArrayList<Diligence> listData) {
        this.listData = listData;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.item_diligence, parent, false);
        return new RecyclerViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.txtNgay.setText(listData.get(position).getNgay());
        holder.txtSang.setText(listData.get(position).getSang());
        holder.txtChieu.setText(listData.get(position).getChieu());
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtNgay,txtSang,txtChieu;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtNgay = (TextView) itemView.findViewById(R.id.txtNgay);
            txtSang = (TextView) itemView.findViewById(R.id.txtSang);
            txtChieu = (TextView) itemView.findViewById(R.id.txtChieu);
        }

    }
}
