package com.example.pujarani.githubviewerapplication.View

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pujarani.githubviewerapplication.Model.GitHubDatModel
import com.example.pujarani.githubviewerapplication.R
import kotlinx.android.synthetic.main.git_hub_details.view.*

/**
 * Created by Puja.Rani on 15-04-2020.
 */
class GitHubDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.git_hub_details)
        val intent: GitHubDatModel = getIntent().getSerializableExtra("DATA") as GitHubDatModel
        val webview: WebView = findViewById(R.id.htmlView)

        webview.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(intent.html_url)
                return true
            }
        }
        webview.loadUrl(intent.html_url)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}