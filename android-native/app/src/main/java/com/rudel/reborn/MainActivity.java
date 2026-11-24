package com.rudel.reborn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Build;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceError;
import android.Manifest;
import android.content.pm.PackageManager;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;

/** No fake network backend. Offline RUDEL assets are packaged locally. */
public final class MainActivity extends Activity {
    private static final int PICK_FILE = 1277;
    private static final String ENTRY = "file:///android_asset/index.html";
    private WebView web;
    private ValueCallback<Uri[]> pendingFile;
    private PermissionRequest pendingAudio;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (com.google.firebase.auth.FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, AccountActivity.class));
            finish();
            return;
        }

        getWindow().setStatusBarColor(Color.rgb(9,17,29));
        getWindow().setNavigationBarColor(Color.rgb(9,17,29));
        try {
            web = new WebView(this);
            web.setBackgroundColor(Color.rgb(9,17,29));
            web.setOverScrollMode(View.OVER_SCROLL_NEVER);
            WebSettings settings = web.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setAllowFileAccess(true);
            settings.setAllowContentAccess(true);
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
            settings.setSupportZoom(false);
            settings.setBuiltInZoomControls(false);
            settings.setDisplayZoomControls(false);
            web.setWebViewClient(new WebViewClient() {
                @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    Uri uri=request.getUrl();
                    if ("file".equals(uri.getScheme()) && uri.getPath()!=null && uri.getPath().startsWith("/android_asset/")) return false;
                    if ("https".equals(uri.getScheme()) || "http".equals(uri.getScheme())) {
                        try { startActivity(new Intent(Intent.ACTION_VIEW,uri)); } catch(Exception ex) { Log.w("RUDEL","Link not opened",ex); }
                    }
                    return true;
                }
                @Override public void onReceivedError(WebView v, WebResourceRequest request, WebResourceError error) {
                    if(request.isForMainFrame()) showNativeError("Content could not load. Retry RUDEL.");
                }
            });
            web.setWebChromeClient(new WebChromeClient() {
                @Override public void onPermissionRequest(PermissionRequest request) {
                    // Only microphone capture, only after the Web page explicitly asks for it.
                    if (request == null) return;
                    boolean needsAudio=false;
                    for (String res : request.getResources()) if (PermissionRequest.RESOURCE_AUDIO_CAPTURE.equals(res)) needsAudio=true;
                    if (!needsAudio || request.getResources().length != 1) {request.deny();return;}
                    pendingAudio=request;
                    if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                        request.grant(new String[]{PermissionRequest.RESOURCE_AUDIO_CAPTURE});pendingAudio=null;
                    } else requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 3341);
                }
                @Override public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> callback, FileChooserParams params) {
                    if (pendingFile != null) pendingFile.onReceiveValue(null);
                    pendingFile=callback;
                    try {
                        Intent intent=params.createIntent();
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        startActivityForResult(intent,PICK_FILE);
                        return true;
                    } catch(Exception ex) {
                        Log.w("RUDEL","No suitable file picker",ex);
                        pendingFile.onReceiveValue(null);
                        pendingFile=null;
                        return false;
                    }
                }
            });
            LinearLayout shell = new LinearLayout(this);
            shell.setOrientation(LinearLayout.VERTICAL);
            Button account = new Button(this);
            account.setText("RUDEL · Online account");
            account.setOnClickListener(v -> startActivity(new Intent(this, AccountActivity.class)));
            
    // RUDEL43_HEADER
    shell.setBackgroundColor(Color.rgb(7,12,24));
    account.setText("RUDEL  |  My Account");
    account.setAllCaps(false);
    account.setTextSize(16);
    account.setTextColor(Color.WHITE);
    android.graphics.drawable.GradientDrawable bg =
        new android.graphics.drawable.GradientDrawable(
        android.graphics.drawable.GradientDrawable.Orientation.LEFT_RIGHT,
        new int[]{0xff172b49,0xff44378a});
    bg.setCornerRadius(28f);
    account.setBackground(bg);
    LinearLayout.LayoutParams header =
        new LinearLayout.LayoutParams(-1, -2);
    header.setMargins(16,12,16,12);
    shell.addView(account,header);

            shell.addView(web, new LinearLayout.LayoutParams(-1, 0, 1));
            setContentView(shell);
            web.loadUrl(ENTRY);
        } catch(Throwable ex) {
            Log.e("RUDEL","WebView startup failure",ex);
            showNativeError("RUDEL could not start. Check Android System WebView and retry.");
        }
    }

    private void showNativeError(String message) {
        LinearLayout box=new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(40,65,40,20);
        box.setBackgroundColor(Color.rgb(9,17,29));
        TextView text=new TextView(this);
        text.setText("RUDEL\n\n"+message);
        text.setTextColor(Color.WHITE);
        text.setTextSize(18);
        box.addView(text);
        Button retry=new Button(this);
        retry.setText("Retry");
        retry.setOnClickListener(v->{ if(web!=null) {setContentView(web);web.loadUrl(ENTRY);} else recreate(); });
        box.addView(retry);
        setContentView(box);
    }

    @Override public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        super.onRequestPermissionsResult(code, permissions, results);
        if (code == 3341 && pendingAudio != null) {
            PermissionRequest request = pendingAudio; pendingAudio = null;
            if (results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) request.grant(new String[]{PermissionRequest.RESOURCE_AUDIO_CAPTURE});
            else request.deny();
        }
    }

    @Override protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==PICK_FILE && pendingFile!=null) {
            try { pendingFile.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode,data)); }
            catch(Exception e) { pendingFile.onReceiveValue(null); }
            pendingFile=null;
        }
    }

    @Override public void onBackPressed() {
        if(web!=null && web.canGoBack()) web.goBack();
        else super.onBackPressed();
    }


    @Override protected void onResume() {
        super.onResume();
        if (com.google.firebase.auth.FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, AccountActivity.class));
            finish();
        }
    }

    @Override protected void onDestroy() {
        if(pendingFile!=null) {pendingFile.onReceiveValue(null);pendingFile=null;}
        if(pendingAudio!=null) {pendingAudio.deny();pendingAudio=null;}
        if(web!=null) {web.stopLoading();web.destroy();web=null;}
        super.onDestroy();
    }
}
