package com.example.dynamicjsonparsing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.dynamicjsonparsing.model.DemoJson
import com.example.dynamicjsonparsing.model.Feeds
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            //First Method
            val gson = Gson()
            val data = loadJSONFromAsset()
            val type = object : TypeToken<DemoJson>() {}.type
            val demoJson = gson.fromJson<DemoJson>(data, type)
            val feeds: kotlin.collections.Map<String, Feeds>
            feeds = demoJson.data.feeds
            printMap(demoJson.data.feeds)
            //Second Method
            for (key in feeds.keys) {
                println("Key = " + key!!)
            }
            // Iterating over values only
            for (value in feeds.values) {
                println("Value = " + value.address + "-" + value.name)
            }

            val mapper = ObjectMapper()
            val map = mapper.readValue(data, Map::class.java)
            println(map)

            for (key in map.keys) {
                println("Key = " + key!!)
            }
            // Iterating over values only
            for (value in map.values) {
                println("Value = $value")
            }
        }
    }

    fun printMap(mp: kotlin.collections.Map<String, Feeds>) {
        val it = mp.entries.iterator()
        while (it.hasNext()) {
            val pair = it.next()
            Log.d("fav", pair.key)
            Log.d("fav", pair.value.name)
        }
    }

    private fun loadJSONFromAsset(): String? {
        var s: String? = ""
        try {
            val fileName = "test.json"
            s = application.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            s = null
        }
        return s
    }
}
