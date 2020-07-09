package mtha.fithou.guidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListContactActivity extends AppCompatActivity {

    ListView lvContact;
    String contact;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        getViews();
        //2. tao adapter chua data source vaf itemview
        adapter = new ArrayAdapter<String>(ListContactActivity.this,
                android.R.layout.simple_list_item_1,contacts);
        //3. set adapter cho listview
        lvContact.setAdapter(adapter);

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long l) {
                String contact =(String) adapterView.getItemAtPosition(position);
                Toast.makeText(ListContactActivity.this,
                        "Contact: " + contact,
                        Toast.LENGTH_LONG).show();

            }
        });

        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,View view, int position, long l)
            {
                //lay ra item can xoa
               contact = adapterView.getItemAtPosition(position).toString();
                //xay dung dialog hien thi thong bao co xoa hay k
                AlertDialog.Builder builder = new AlertDialog.Builder(ListContactActivity.this);
                //thiet lap cac tham so cho alertDialog
                builder.setMessage("ban co muon xoa contact: " + contact +" khong? ");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //thuc hien xoa item khoi listview
                        adapter.remove(contact);
                        //update lai su thay doi trong adapter
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ListContactActivity.this,
                                contact +" da xoa", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //khong thuc hien xoa item da chon
                        dialogInterface.dismiss();//an dialog nay di
                    }
                });

                //hien alertDialog len
                builder.show();
                return true;
            }
        });


    }

//1. chuan bi data source
    final String [] contacts ={"ha 0987643", "tuan 98765432", "minh 3456789", "Linh 98765432"};
    private void getViews(){
        lvContact = (ListView) findViewById(R.id.lvContact);
    }
}