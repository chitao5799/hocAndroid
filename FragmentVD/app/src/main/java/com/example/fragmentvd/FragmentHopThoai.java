package com.example.fragmentvd;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class FragmentHopThoai extends DialogFragment {
    DeleteData deleteData;

    //fragment này ko cần layout resource file nên ko cần hàm onCreateView

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        deleteData= (DeleteData) getActivity();

        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
        dialog.setTitle("Xác nhận");
        dialog.setMessage("Bạn có muốn xóa sản phẩm này không");
        dialog.setPositiveButton("có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              //  Toast.makeText(getActivity(),"đã chọn có",Toast.LENGTH_SHORT).show();
                deleteData.GiaTriXoa(true);
            }
        });
        dialog.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteData.GiaTriXoa(false);
            }
        });

        Dialog hopThoai=dialog.create();
        return hopThoai;
    }
}
