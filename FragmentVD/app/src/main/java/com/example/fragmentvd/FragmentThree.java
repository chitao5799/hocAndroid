package com.example.fragmentvd;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;

public class FragmentThree extends Fragment {
    TextView txtNoiDung;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_three,container,false);
        txtNoiDung=(TextView)view.findViewById(R.id.txtFragmentThree);

        Bundle bundle=getArguments();
        if(bundle!=null)
            txtNoiDung.setText(bundle.getString("DuLieu"));
        return view;
    }
}
