package com.example.todolist


import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.todolist.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val savedTitleList = sharedPreferences.getString("titlelist", null)
        val savedDescriptionList = sharedPreferences.getString("descriptionlist", null)
        val savedCompletedList = sharedPreferences.getString("completedlist", null)
        if (savedTitleList != null) {
            val type: Type = object : TypeToken<MutableList<String>>() {}.type
            GlobalData.titleList = Gson().fromJson(savedTitleList, type)
            GlobalData.descriptionList = Gson().fromJson(savedDescriptionList, type)
            val type2: Type = object : TypeToken<MutableList<Boolean>>() {}.type
            GlobalData.completed = Gson().fromJson(savedCompletedList, type2)
        supportFragmentManager
            .beginTransaction()
            .detach(ListFragment())
            .attach(ListFragment())
            .commit()
        }

    }
    override fun onStop() {
        super.onStop()
        val editStorage = sharedPreferences.edit()
        val jsonTitleList = Gson().toJson(GlobalData.titleList)
        val jsonDescriptionList = Gson().toJson(GlobalData.descriptionList)
        val jsonCompletedList = Gson().toJson(GlobalData.completed)
        editStorage.putString("titlelist", jsonTitleList)
        editStorage.putString("descriptionlist", jsonDescriptionList)
        editStorage.putString("completedlist", jsonCompletedList)
        editStorage.apply()
    }



}