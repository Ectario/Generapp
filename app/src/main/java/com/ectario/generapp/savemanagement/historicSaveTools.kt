package com.ectario.generapp.savemanagement

import android.content.Context
import android.content.SharedPreferences
import com.ectario.generapp.hash.WordsHasher
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

fun saveHistoric(context: Context, historic : ArrayList<WordsHasher?>?) {
    val sharedPreferences: SharedPreferences? = context?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences?.edit()
    val gson = Gson()
    val json = gson.toJson(historic)
    editor?.putString("historic", json)
    editor?.apply()
}

fun loadHistoric(context: Context): ArrayList<WordsHasher?>? {
    var historic : ArrayList<WordsHasher?>? =  null
    val sharedPreferences: SharedPreferences? = context?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = sharedPreferences?.getString("historic", null)
    val type: Type = object : TypeToken<ArrayList<WordsHasher?>>() {}.type
    historic = if (json!=null) gson?.fromJson(json, type) else null
    if (historic == null) {
        historic = ArrayList()
    }
    return historic
}