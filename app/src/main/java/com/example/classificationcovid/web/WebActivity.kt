@file:Suppress("DEPRECATION")

package com.example.classificationcovid.web

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.classificationcovid.R
import com.example.classificationcovid.main.MainActivity
import com.google.android.material.internal.ContextUtils.getActivity


class WebActivity : AppCompatActivity() {
    private fun backBar() {
        val backbtn = supportActionBar
        backbtn!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun mainTitle(title: String) {
        supportActionBar?.title = title
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        setUpWebView()
        backBar()
        mainTitle(resources.getString(R.string.titleWeb))


    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavsScriptEnable")
    private fun setUpWebView() {
        var uploadMessage: ValueCallback<Array<Uri>>? = null
        var muploadMessage: ValueCallback<Uri>? = null
        val FILECHOOSER_RESULTCODE = 1
        val REQUEST_SELECT_FILE = 100

        val webView: WebView = findViewById(R.id.web_view)
        webView.apply {
            loadUrl("http://34.101.151.172/")
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.allowFileAccessFromFileURLs = true
            settings.allowUniversalAccessFromFileURLs = true
            settings.saveFormData = true

        }
        val progressBar : ProgressBar = findViewById(R.id.progressBar2)
        webView.webViewClient = object : WebViewClient(){
            override fun onPageStarted(webView: WebView?, url: String?, favicon: Bitmap?){
                progressBar.visibility = View.VISIBLE
                    super.onPageStarted(webView, url, favicon)
                }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }

        }
        webView.webChromeClient = object : WebChromeClient() {

             //for 3.0+ Devices (Start)
            fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String) {
                muploadMessage = uploadMsg
                val i = Intent(Intent.ACTION_GET_CONTENT)
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.type = "image/*";
                startActivityForResult(
                    Intent.createChooser(i, "File Browser"),
                    FILECHOOSER_RESULTCODE
                );
            }
            //For Lollipop 5.0+ Devices

            @SuppressLint("RestrictedApi")
            override fun onShowFileChooser(
                mWebView: WebView,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                if (uploadMessage != null) {
                    uploadMessage!!.onReceiveValue(null)
                    uploadMessage = null
                }
                uploadMessage = filePathCallback
                var intent: Intent? = null
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    intent = fileChooserParams.createIntent()
                }
                try {
                    startActivityForResult(intent, REQUEST_SELECT_FILE)
                } catch (e: ActivityNotFoundException) {
                    uploadMessage = null
                    Toast.makeText(getActivity(applicationContext), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false
                }
                return true
            }

            //For Android 4.1 Only
            fun openFileChooser(
                uploadMsg: ValueCallback<Uri>,
                acceptType: String,
                capture: String
            ) {
                muploadMessage = uploadMsg
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                startActivityForResult(
                    Intent.createChooser(intent, "File Browser"),
                    FILECHOOSER_RESULTCODE
                )
            }

            fun openFileChooser(uploadMsg: ValueCallback<Uri>) {
                muploadMessage = uploadMsg
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(
                    Intent.createChooser(intent, "File Browser"),
                    FILECHOOSER_RESULTCODE
                )
            }
            fun onActivityResult(Requestcode: Int, Resultcode: Int, intent: Intent) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (Requestcode == REQUEST_SELECT_FILE) {
                        if (uploadMessage == null)
                            return
                        uploadMessage!!.onReceiveValue(
                            WebChromeClient.FileChooserParams.parseResult(
                                Resultcode,
                                intent
                            )
                        );
                        uploadMessage = null;
                    }
                } else if (Requestcode == FILECHOOSER_RESULTCODE) {
                    if (muploadMessage == null)
                        return
                    val result =
                        if (intent == null || Resultcode !== RESULT_OK) null else intent.data
                    muploadMessage!!.onReceiveValue(result)
                    muploadMessage = null

                }
            }
        }


        fun onKeyDown(
            keyCode: Int,
            event: KeyEvent
        ): Boolean {

            if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
            return super.onKeyDown(keyCode, event)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === R.id.info) {
            startActivity(Intent(this, Information::class.java))
        } else {
            onBackPressed()
        }

        return true
    }


}

