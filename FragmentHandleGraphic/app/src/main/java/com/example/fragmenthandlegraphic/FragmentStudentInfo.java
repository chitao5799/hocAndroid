package com.example.fragmenthandlegraphic;

import android.app.Fragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class FragmentStudentInfo extends Fragment {
    View view;
    TextView txtHoten,txtNamSinh,txtDiaChi,txtEmail;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       view =inflater.inflate(R.layout.fragment_student_info,container,false);

       AnhXa();

        return view;
    }
    public void SetInfo(Student sv)
    {
        txtHoten.setText("Họ tên: "+sv.getHoTen());
        txtNamSinh.setText("Năm sinh: "+sv.getNamSinh()+"");
        txtDiaChi.setText("Địa chỉ: "+sv.getDiaChi());
        txtEmail.setText("Email: "+sv.getEmail());
    }
    private void AnhXa() {
        txtHoten=(TextView)view.findViewById(R.id.txtInfoHoTen);
        txtNamSinh=(TextView)view.findViewById(R.id.txtInfoNamSinh);
        txtDiaChi=(TextView)view.findViewById(R.id.txtInfoDiaChi);
        txtEmail=(TextView)view.findViewById(R.id.txtInfoEmail);
    }
}

