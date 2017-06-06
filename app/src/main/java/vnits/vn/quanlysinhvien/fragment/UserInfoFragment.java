package vnits.vn.quanlysinhvien.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import vnits.vn.quanlysinhvien.ChangeInfoActivity;
import vnits.vn.quanlysinhvien.ChangePassActivity;
import vnits.vn.quanlysinhvien.Database.MyDatabaseAccess;
import vnits.vn.quanlysinhvien.LoginActivity;
import vnits.vn.quanlysinhvien.R;


public class UserInfoFragment extends Fragment{

    LinearLayout btnChangeInfo,btnChangePass,btnLogout,btnCancel;
    ImageView btnSetting;
    Animation rotateOpen,rotateClose;
    MyDatabaseAccess myDatabaseAccess;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo,container,false);
        myDatabaseAccess = new MyDatabaseAccess(getActivity());
        rotateOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_plus_close_rotate);
        rotateClose = AnimationUtils.loadAnimation(getActivity(),R.anim.fab_plus_open_rotate);
        btnSetting = (ImageView) view.findViewById(R.id.btn_setting);

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSetting.startAnimation(rotateOpen);
                eventDialogSetting();
            }
        });
        return view;
    }

    public void eventDialogSetting(){
        final Dialog dialogSetting = new Dialog(getActivity());
        dialogSetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSetting.setContentView(R.layout.dialog_userinfo);
        dialogSetting.setCancelable(false);
        dialogSetting.setCanceledOnTouchOutside(false);
        dialogSetting.getWindow().setBackgroundDrawableResource(R.color.colorTran);
        dialogSetting.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialogSetting.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogSetting.show();

        btnChangeInfo = (LinearLayout) dialogSetting.findViewById(R.id.btn_editinfo);
        btnChangePass = (LinearLayout) dialogSetting.findViewById(R.id.btn_editpass);
        btnLogout = (LinearLayout) dialogSetting.findViewById(R.id.btn_logout);
        btnCancel = (LinearLayout) dialogSetting.findViewById(R.id.btn_cancel);

        btnChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSetting.startAnimation(rotateClose);
                rotateClose.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(getActivity(),ChangeInfoActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                dialogSetting.dismiss();
            }
        });

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSetting.startAnimation(rotateClose);
                rotateClose.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(getActivity(),ChangePassActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                dialogSetting.dismiss();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outApp();
                dialogSetting.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSetting.startAnimation(rotateClose);
                dialogSetting.dismiss();
            }
        });

    }

    public void outApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.MyDialogTheme);
        builder.setTitle( Html.fromHtml("<font color='#FF7F27'>Thoát</font>"));
        builder.setMessage( Html.fromHtml("<font color='#FF7F27'>Bạn có muốn đăng xuất ?</font>"));
        builder.setCancelable(false);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                myDatabaseAccess.DeleteTokens();
                dialog.dismiss();
                Intent intent = new Intent(getView().getContext(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnSetting.startAnimation(rotateClose);
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
