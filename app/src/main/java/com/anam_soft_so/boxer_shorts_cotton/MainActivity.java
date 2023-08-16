package com.anam_soft_so.boxer_shorts_cotton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ImageSlider imageSlider;

   GridView  gridView ;
    ProgressBar progress;
    ImageView about;
    CardView cardMan,cardGirl,card3,card4;
    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSlider =findViewById(R.id.image_slider);
         gridView=findViewById(R.id.gridView);
          progress=findViewById(R.id.progress);
          cardMan=findViewById(R.id.cardMan);
          cardGirl=findViewById(R.id.cardGirl);
               card3=findViewById(R.id.card3);
               card4=findViewById(R.id.card4);
               about=findViewById(R.id.about);


        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel("https://m.media-amazon.com/images/I/81OOfAs1BEL._AC_UX679_.jpg", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://m.media-amazon.com/images/I/811zyOkaPHL._AC_UX679_.jpg", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://m.media-amazon.com/images/I/51gqeGXHmeL._AC_UY741_.jpg", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://m.media-amazon.com/images/I/81qYD-LY5xL._AC_UX679_.jpg", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://m.media-amazon.com/images/I/61InJyHWv7L._AC_UX679_.jpg", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://m.media-amazon.com/images/I/81OOfAs1BEL._AC_UX679_.jpg", ScaleTypes.FIT));


        imageSlider.setImageList(imageList);




        String URL="https://anamhr.xyz/eapp/all.json" ;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            progress.setVisibility(View.GONE);

                try {

                    for (int x=0 ;x<response.length();x++){
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


                ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
                if (networkInfo!=null&& networkInfo.isConnected()){




                }else {

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("No Internet")
                            .setMessage("Please Connect Your Internet")
                            .show();


                }



            }
        });


        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);



cardMan.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(MainActivity.this, Man_Page.class);
        startActivity(intent);

    }
});

        cardGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, Girls_Page.class);
                startActivity(intent);

            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"Coming Soon" ,Toast.LENGTH_LONG).show();

            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"Coming Soon" ,Toast.LENGTH_LONG).show();

            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Purpose Of This App")
                        .setMessage("Our App Aims To connect Users Directly to Products Of Their Choice")
                        .show();


            }
        });







    }
    // adapter ***************************************************//

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

                    Intent intent =new Intent(MainActivity.this,AboutP.class);
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