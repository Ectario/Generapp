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

fun deleteOldHistoric(context: Context, deleteLimit : Int = 100, numberToDeleteArg : Int = 1): ArrayList<WordsHasher?>? { // AFTER the "deleteLimit" indice the function delete "numberToDelete" Object
    var historic = loadHistoric(context)         // If the numberToDelete = -1 then delete all after the deleteLimit

    historic?.reverse()

    var size = historic?.size!!
    var numberToDelete = numberToDeleteArg

    if (numberToDelete == 0 || deleteLimit == -1){
        historic?.reverse()
        return historic
    }

    if (size-1 < deleteLimit){
        historic?.reverse()
        return historic
    }

    if (numberToDelete == -1) {
        numberToDelete = size-deleteLimit
    }

    for(i in 1..numberToDelete){
        size = historic?.size!!
        println(deleteLimit)
        println(size)
        if (size-1 < deleteLimit){ //The size downgrade each removeAt so if the deleteLimit is too big that cut the delete and save/return
            println("STOPPED")
            historic?.reverse()
            saveHistoric(context, historic)
            return historic
        }
        historic.removeAt(deleteLimit)
    }
    historic?.reverse()
    saveHistoric(context, historic)
    return historic
}