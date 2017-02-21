package sanguneo.com.sgpokeease;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebStorage;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView web; //웹뷰
    private WebSettings webSet; //웹뷰세팅
    private final long FINSH_INTERVAL_TIME = 2000;
    private long	backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = (WebView) findViewById(R.id.webview);
        web.setHorizontalScrollBarEnabled(false); //가로 스크롤
        web.setVerticalScrollBarEnabled(false);   //세로 스크롤
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        WebSettings webSet = web.getSettings();
        webSet.setAllowFileAccess(true);
        webSet.setDomStorageEnabled(true);
        webSet.setJavaScriptEnabled(true);
        webSet.setUseWideViewPort(true);
        webSet.setLoadWithOverviewMode(true);
        webSet.setBuiltInZoomControls(false);

        webSet.setTextZoom(100);
        webSet.setAllowUniversalAccessFromFileURLs(true);
        webSet.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        web.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if ( 0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime ) {
            super.onBackPressed();
            finish();
        }
        else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(),"Please touch BACK again to exit.",Toast.LENGTH_SHORT).show();
        }
    }
}
