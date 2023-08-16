package com.anam_soft_so.boxer_shorts_cotton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anam_soft_so.boxer_shorts_cotton.R;
import com.squareup.picasso.Picasso;

public class AboutP extends AppCompatActivity {

    TextView name,price,title;
    CardView cardBtn;
    ImageView img;

    public static String NAME ="" ;
    public static String IMG ="" ;
    public static String PURL ="" ;
    public static String TITLE ="" ;
    public static String PRICE ="" ;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_p);

        name=findViewById(R.id.name);
        price=findViewById(R.id.price);
        title=findViewById(R.id.title);
        cardBtn=findViewById(R.id.cardBtn);
        img=findViewById(R.id.img);



        name.setText(NAME);
        price.setText(PRICE);
        title.setText(TITLE);
        Picasso.get().load(IMG).into(img);



        cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(AboutP.this,WebView.class);
                startActivity(intent);
                 WebView.url=PURL ;



            }
        });







    }
}