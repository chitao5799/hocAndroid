package com.example.fragmentvd;

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

public class FragmentTwo extends Fragment {
    TextView txtTwo;
    EditText edtTwo;
    Button btnTwo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frament_two,container,false);
        txtTwo=(TextView)view.findViewById(R.id.txtFragTwo);
        edtTwo=(EditText)view.findViewById(R.id.edtFragTwo);
        btnTwo=(Button)view.findViewById(R.id.btnFragTwo);

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               TextView txtOne=(TextView)getActivity().findViewById(R.id.txtFragOne);
               txtOne.setText(edtTwo.getText().toString());
            }
        });
        return  view; 
    }
}
