package edu.temple.basicbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var urlEditText: EditText
    private lateinit var goButton: ImageButton
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlEditText = findViewById(R.id.urlEditText)
        goButton = findViewById(R.id.goButton)
        webView = findViewById(R.id.webView)

        // Allow your browser to intercept hyperlink clicks
        webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
        goButton.setOnClickListener {
            val userInput = urlEditText.text.toString()
            val formatedInput = formatUrl(userInput)
            urlEditText.setText(formatedInput)
            webView.loadUrl(formatedInput)
        }
    }

    private fun formatUrl(url: String): String {

        var fixedUrl = url.trim()

        // add .com if user typed only a word
        if (!fixedUrl.contains(".")) {
            fixedUrl += ".com"
        }

        // add https if missing
        if (!fixedUrl.startsWith("http://") &&
            !fixedUrl.startsWith("https://")) {
            fixedUrl = "https://$fixedUrl"
        }

        return fixedUrl
    }
}