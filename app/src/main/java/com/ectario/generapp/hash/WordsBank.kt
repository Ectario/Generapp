package com.ectario.generapp.hash

open class WordsBank {
    protected var list : ArrayList<String?>?
    protected var lengthWordsBank : Int = 0

    constructor(listOfWords: ArrayList<String?>? = null, lenghtPasswordArg : Int = 0){
        var lengthPassword = lenghtPasswordArg
        var cpt = 0
        listOfWords?.forEach {
            if (it != null) cpt += it.length
        }
        if (lengthPassword< cpt) lengthPassword = cpt
        this.lengthWordsBank = lengthPassword
        this.list = listOfWords
    }

    fun getLength() : Int {
        return lengthWordsBank
    }

    fun getListOfWords() : ArrayList<String?>? {
        return list
    }
}