package com.example.fragmenthandlegraphic;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentStudentList extends ListFragment {
    ArrayList<Student> listSinhVien;
    StudentAdapter adapter;
    TruyenSinhVien truyenSV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        truyenSV= (TruyenSinhVien) getActivity();
        listSinhVien=new ArrayList<>();
        AddStudent();
        adapter=new StudentAdapter(getActivity(),R.layout.row_student,listSinhVien);
        setListAdapter(adapter);


        return inflater.inflate(R.layout.fragment_student_list,container,false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        truyenSV.DataStudent(listSinhVien.get(position));
    }

    private void AddStudent()
    {
        listSinhVien.add(new Student("Nguyễn Văn Minh",2000,"Hà Nội","skfs@gmail.com"));
        listSinhVien.add(new Student("Nguyễn thị linh",1999,"hà nam","sk9823fs@gmail.com"));
        listSinhVien.add(new Student("phạm văn trung",1998,"Hà tây","i9282384@gmail.com"));
        listSinhVien.add(new Student("đào thị mai",1889,"bắc giang","oie9w8239@gmail.com"));
        listSinhVien.add(new Student("võ nhật linh",1997,"quảng ninh","q2320@gmail.com"));
        listSinhVien.add(new Student("ưng hoàng vương",1992,"ninh bình","ksdf0930329@gmail.com"));
        listSinhVien.add(new Student("phạm thị nhung",1996,"thái nguyên","xckvj0w0329@gmail.com"));
    }
}
