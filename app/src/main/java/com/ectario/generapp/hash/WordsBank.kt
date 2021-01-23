package com.ectario.generapp.hash

open class WordsBank {
    protected var list : ArrayList<String?>?
    protected var lengthPassword : Int = 0

    constructor(listOfWords: ArrayList<String?>? = null, lenghtPasswordArg : Int = 0){
        var lengthPassword = lenghtPasswordArg
        var cpt = 0
        if (listOfWords != null) {
            listOfWords.forEach {
                if (it != null) cpt += it.length
            }
        }
        if (lengthPassword< cpt) lengthPassword = cpt
        this.lengthPassword = lengthPassword
        this.list = listOfWords
    }

    fun getLength() : Int {
        return this.lengthPassword
    }
}