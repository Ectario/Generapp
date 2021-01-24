package com.ectario.generapp.hash

import com.ectario.generapp.HomePageActivity
import com.ectario.generapp.tools.makeToast
import kotlin.random.Random

class WordsHasher(listOfWords: ArrayList<String?>? = null, lenghtPasswordArg: Int = 0) : WordsBank(listOfWords, lenghtPasswordArg) {
    private val mapingLetter = mapOf('a' to '@', 'e' to '&')
    private var password : String = ""



    init {
        if (listOfWords != null) {
            listOfWords.forEach {
                if (it != null) {
                    it.forEach {
                        password += if(mapingLetter.containsKey(it)){
                            mapingLetter.get(it)
                        } else it
                    }
                } else HomePageActivity.applicationContext.makeToast("Empty word found")
            }
        } else {
            password = if (lenghtPasswordArg != 0) generateRandomPassword(lenghtPasswordArg) else generateRandomPassword()
        }
    }

    private fun generateRandomPassword(length: Int = Random.Range(6,15)): String {
        val leftLimit = 33 // char '!' in UTF-8 base 10
        val rightLimit = 122 // char 'z' in UTF-8 base 10
        var generatedPwd = ""
        for (i in 0 until length) {
            val randomLimitedInt = Random.Range(leftLimit, rightLimit)
            generatedPwd += randomLimitedInt.toChar()
        }
        return generatedPwd
    }

    fun getPasswordHashed(): String {
        return this.password
    }

    private fun Random.Range(min : Int, max : Int): Int {
        return min + (Random.nextFloat() * (max - min + 1)).toInt()
    }
}