package com.example.webviewflutterintegration.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.webviewflutterintegration.R
import com.example.webviewflutterintegration.databinding.FragmentDashboardBinding

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