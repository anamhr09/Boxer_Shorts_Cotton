package com.anam_soft_so.boxer_shorts_cotton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Man_Page extends AppCompatActivity {

   GridView gridView;
   ProgressBar progress;
   ImageView back;

    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_page);
        gridView=findViewById(R.id.gridView);
        progress=findViewById(R.id.progress);
         back=findViewById(R.id.back);


        String url ="https://anamhr.xyz/eapp/man.json";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progress.setVisibility(View.GONE);

                        try {
                            for (int x=0; x<response.length();x++){
                                JSONObject jsonObject=response.getJSONObject(x);
                                String pName =jsonObject.getString("p_name") ;
                                String pUrl=jsonObject.getString("p_url");
                                String pImg=jsonObject.getString("img_url");
                                String pTitle=jsonObject.getString("title");
                                String pPrice=jsonObject.getString("price");

                                hashMap=new HashMap<>();
                                hashMap.put("pname",pName);
                                hashMap.put("purl",pUrl);
                                hashMap.put("pimg",pImg);
                                hashMap.put("ptitle",pTitle);
                                hashMap.put("pprice",pPrice);
                                arrayList.add(hashMap);



                            }

                            MyAdapter myAdapter=new MyAdapter();
                            gridView.setAdapter(myAdapter);



                        } catch (JSONException e) {

                            throw new RuntimeException(e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.setVisibility(View.GONE);



            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(Man_Page.this);
        requestQueue.add(jsonArrayRequest);



back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(Man_Page.this,MainActivity.class);
        startActivity(intent);
    }
});




    }

    private class MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater=getLayoutInflater();
            View myView =layoutInflater.inflate(R.layout.all_item,null);
            ImageView img;
            CardView card;
            TextView name , title , price ;
            img=myView.findViewById(R.id.img);
            name=myView.findViewById(R.id.name);
            title=myView.findViewById(R.id.title);
            price=myView.findViewById(R.id.price);
            card=myView.findViewById(R.id.card);

            HashMap<String,String>hashMap=arrayList.get(position);
            String Name = hashMap.get("pname") ;
            String Purl = hashMap.get("purl") ;
            String Img = hashMap.get("pimg") ;
            String Title = hashMap.get("ptitle") ;
            String Price = hashMap.get("pprice") ;

            name.setText(Name);
            title.setText(Title);
            price.setText(Price);
            Picasso.get().load(Img).into(img);


            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =new Intent(Man_Page.this,AboutP.class);
                    startActivity(intent);
                    AboutP.NAME=Name ;
                    AboutP.IMG=Img;
                    AboutP.PURL=Purl;
                    AboutP.TITLE=Title;
                    AboutP.PRICE=Price;


                }
            });



            return myView;
        }
    }







}