package com.example.googleapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlaylistYoutubeActivity extends AppCompatActivity {
    public static String API_KEY="AIzaSyDmAUKP3mAKI5g8uP7WPGoWShiEKjXgn1I";
    String id_listvieo="PLhq3xhjyWujolS59IKY1SjklLzVdYvG6O";//https://www.youtube.com/watch?v=25zaPKEnXKs&list=PLhq3xhjyWujolS59IKY1SjklLzVdYvG6O
    String urlGetJsonListVideo="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+id_listvieo+"&key="+API_KEY+"&maxResults=50";

   ListView listViewVideo;
   ArrayList<VideoYoutube> arrVideo;
   VideoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_youtube);

        listViewVideo=(ListView)findViewById(R.id.listviewVideo);
        arrVideo=new ArrayList<>();
        adapter=new VideoAdapter(PlaylistYoutubeActivity.this,R.layout.row_video,arrVideo);
        listViewVideo.setAdapter(adapter);

        GetJsonYoutube(urlGetJsonListVideo);

        listViewVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(PlaylistYoutubeActivity.this,PlayVideoActivity.class);
                intent.putExtra("idVideoYoutube",arrVideo.get(i).getIdVideo());
                startActivity(intent);
            }
        });
    }
    private void GetJsonYoutube(String url)
    {
        RequestQueue requestQueue= Volley.newRequestQueue(PlaylistYoutubeActivity.this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //coppy đoạn json lấy được và 1 trang json formatter nào đó để phân tích cho dễ
                        try {
                            JSONArray jsonItems=response.getJSONArray("items");
                            String title,urlThumbnailMedium,idVideo;
                            for(int i=0;i<jsonItems.length();i++)
                            {
                                JSONObject jsonItem= jsonItems.getJSONObject(i);
                                JSONObject jsonSnippet=jsonItem.getJSONObject("snippet");
                                title=jsonSnippet.getString("title");
                                JSONObject jsonThumbnail=jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonThumbnailMedium=jsonThumbnail.getJSONObject("medium");
                                urlThumbnailMedium=jsonThumbnailMedium.getString("url");
                                JSONObject jsonResourceId=jsonSnippet.getJSONObject("resourceId");
                                idVideo=jsonResourceId.getString("videoId");
                                arrVideo.add(new VideoYoutube(title,urlThumbnailMedium,idVideo));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}

