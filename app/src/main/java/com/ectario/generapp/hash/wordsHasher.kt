package com.ectario.generapp.hash

class wordsHasher {
    lateinit var list : ArrayList<String?>
    var lengthPassword : Int = 0

    constructor(listOfWords: ArrayList<String?>, lenghtPasswordArg : Int = 0){
        var lengthPassword = lenghtPasswordArg
        var cpt = 0
        listOfWords.forEach {
            if (it != null) cpt += it.length
        }
        if (lengthPassword< cpt) lengthPassword = cpt
        this.lengthPassword = lengthPassword
        this.list = listOfWords
    }

}