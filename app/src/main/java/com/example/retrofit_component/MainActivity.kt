package com.example.retrofit_component

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val quotesAPI = RetrofitHelper.getInstance().create(QuotesAPI::class.java)
        GlobalScope.launch {
            val result = quotesAPI.getQuotes(1)
            if (result != null){
                val quotesList = result.body()
                if (quotesList != null){
                    quotesList.results.forEach {
                        Log.d("RETRO", it.content)
                    }
                }
            }
        }

    }
}