package com.ectario.generapp.hash

import com.ectario.generapp.HomePageActivity
import com.ectario.generapp.tools.makeToast

class WordsHasher(listOfWords: ArrayList<String?>? = null, lenghtPasswordArg : Int = 0) : WordsBank(listOfWords, lenghtPasswordArg) {
    private val mapingLetter = mapOf('a' to '@', 'e' to '&')
    private var password : String = ""
    private var noNullPwd = false

    init {
        if (listOfWords != null) {
            listOfWords.forEach {
                if (it != null) {
                    it.forEach {
                        password += if(mapingLetter.containsKey(it)){
                            mapingLetter.get(it)
                        } else it
                    }
                    noNullPwd = true
                } else HomePageActivity.applicationContext.makeToast("Empty word")
            }
        } else HomePageActivity.applicationContext.makeToast("Empty list of words")
    }

    fun getPasswordHashed(): String {
        return if(noNullPwd) this.password else ""
    }
}