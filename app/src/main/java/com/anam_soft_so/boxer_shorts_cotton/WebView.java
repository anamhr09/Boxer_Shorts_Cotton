package com.anam_soft_so.boxer_shorts_cotton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class WebView extends AppCompatActivity {



    android.webkit.WebView webView;
    public static String url ="";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
         webView=findViewById(R.id.webView);



        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null&& networkInfo.isConnected()){




        }else {

            new AlertDialog.Builder(WebView.this)
                    .setTitle("No Internet")
                    .setMessage("Please Connect Your Internet")
                    .show();


        }




        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        webView.setInitialScale(1);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);






    }


    @Override
    public void onBackPressed() {

        if (webView.canGoBack())
            webView.goBack();

        else
            finish();



    }
}