package com.example.docbaovoirss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.Layout;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvTieuDe;
    ArrayList<String> arrTieude,arrLink;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTieuDe=(ListView)findViewById(R.id.lvTieuDe);
        arrTieude=new ArrayList<>();
        arrLink=new ArrayList<>();
        adapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,arrTieude);
        lvTieuDe.setAdapter(adapter);

        new ReadRSS().execute("https://dantri.com.vn/suc-khoe/tu-van.rss");

        lvTieuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,DetailNews.class);
                intent.putExtra("linkTinTuc",arrLink.get(i));
                startActivity(intent);
            }
        });
    }
    private class ReadRSS extends AsyncTask<String,Void,String>
    {
        StringBuilder content=new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url=new URL(strings[0]);
                InputStreamReader streamReader=new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader=new BufferedReader(streamReader);
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser xmldomParser=new XMLDOMParser();
            Document document = xmldomParser.getDocument(s);
            NodeList nodeList =document.getElementsByTagName("item");//item là tên cặp thẻ đọc được từ link, nội dung đọc từ link có nhiều cặp thẻ item
            String tieuDe="";
            for(int i=0;i<nodeList.getLength();i++)
            {
                Element element= (Element) nodeList.item(i);
                tieuDe=xmldomParser.getValue(element,"title");//title là cặp thẻ trong cặp thẻ item
                arrTieude.add(tieuDe);
                arrLink.add(xmldomParser.getValue(element,"description"));//mỗi phần tử trong arrLink tương ứng với mỗi phần tử trong arrTieuDe
            }
            adapter.notifyDataSetChanged();
        }
    }
}