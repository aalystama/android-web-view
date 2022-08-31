package com.example.webviewflutterintegration.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.webviewflutterintegration.R

class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        val webView = view.findViewById<WebView>(R.id.webView);
        webView.webViewClient = CustomWebViewClient();
        webView.settings.javaScriptEnabled = true;
        webView.loadUrl("https://aalystama.github.io/bloc-counter-build/")
        webView.addJavascriptInterface(WebInterface(webView.context), "androidApp")

        val button = view.findViewById<Button>(R.id.button);
        button.setOnClickListener {
            val randomString = java.util.UUID.randomUUID().toString();
            webView.evaluateJavascript("obtainRandomString('$randomString')", null);
        }

        return view;
    }
}

private class CustomWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return true;
    }
}

class WebInterface(private val context: Context) {

    @JavascriptInterface
    fun makeToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}