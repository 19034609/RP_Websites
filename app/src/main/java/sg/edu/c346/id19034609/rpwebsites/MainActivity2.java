package sg.edu.c346.id19034609.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity2 extends AppCompatActivity {

    WebView wvPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        wvPage = findViewById(R.id.webViewPage);

        wvPage.setWebViewClient(new WebViewClient());
        WebSettings setting = wvPage.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setAllowFileAccess(false);
        setting.setBuiltInZoomControls(true);

        Intent intentReceived = getIntent();
        String url = intentReceived.getStringExtra("url");
        wvPage.loadUrl(url);

    }
}