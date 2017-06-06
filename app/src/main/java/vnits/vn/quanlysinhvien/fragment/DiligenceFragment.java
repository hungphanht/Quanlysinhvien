package vnits.vn.quanlysinhvien.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

import vnits.vn.quanlysinhvien.R;

public class DiligenceFragment extends Fragment {


    LinearLayout imgFrom, imgTo;
    static boolean flag = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diligence, container, false);

        imgFrom = (LinearLayout) view.findViewById(R.id.imgFrom);
        imgTo = (LinearLayout) view.findViewById(R.id.imgTo);

        evenClick();
        return view;
    }

    public void evenClick() {
        imgFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        imgTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });
    }

    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {

            TextView txtFrom = (TextView) getActivity().findViewById(R.id.txtFrom);
            TextView txtTo = (TextView) getActivity().findViewById(R.id.txtTo);
            if (flag == false){
                txtFrom.setText(day+"/"+month+"/"+year);
            }else if(flag == true){
                txtTo.setText(day+"/"+month+"/"+year);
            }

        }

    }

}
