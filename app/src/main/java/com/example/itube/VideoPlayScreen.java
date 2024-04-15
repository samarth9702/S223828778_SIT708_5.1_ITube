package com.example.itube;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoPlayScreen extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_play_screen);

        webView = findViewById(R.id.webview);
        String url = getIntent().getStringExtra("url");
        String videoId;

        if(url.length() < 45){
            videoId = url.substring(32);
        } else{
            String id = url.substring(17);
            videoId = id.substring(0,11);
        }

        String javascript = "<iframe id=\"player\" type=\"text/html\" width=\"100%\" height=\"100%\"\n" +
                "  src= \"http://www.youtube.com/embed/" + videoId + "?enablejsapi=1&origin=http://example.com\"\n" +
                "  frameborder=\"0\"></iframe>";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(javascript,"text/html", "utf-8");
        webView.setWebViewClient(new WebViewClient());
    }
}