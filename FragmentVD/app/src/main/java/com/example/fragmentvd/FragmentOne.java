package com.example.fragmentvd;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class FragmentOne extends Fragment {
    TextView txtOne;
    EditText edtOne;
    Button btnOne;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frament_one,container,false);
        txtOne=(TextView)view.findViewById(R.id.txtFragOne);
        edtOne=(EditText)view.findViewById(R.id.edtFragOne);
        btnOne=(Button)view.findViewById(R.id.btnFragOne);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(getActivity(),edtOne.getText().toString(),Toast.LENGTH_SHORT).show();
                TextView txtMain=(TextView)getActivity().findViewById(R.id.txtMain);
                txtMain.setText(edtOne.getText().toString());
            }
        });
        return  view;
    }
    public void GanNoiDung(String s)
    {
        txtOne.setText(s);
    }
}
