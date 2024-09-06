package io.flutter.plugins.webviewflutter;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;


public class FlutterWebViewClient extends WebViewClient {
    @Nullable
    public static WebViewHttpDnsHandler httpDnsHandler;


    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        if (httpDnsHandler != null) {
            WebResourceResponse dnsHandle = httpDnsHandler.shouldInterceptRequest(view, request);
            if (dnsHandle != null) {
                return dnsHandle;
            }
        }
        return super.shouldInterceptRequest(view, request);
    }

}
