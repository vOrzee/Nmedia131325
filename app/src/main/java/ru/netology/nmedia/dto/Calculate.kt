package ru.netology.nmedia.dto

    fun Calculate (x : Int):String {
        var y: String =""
        var z1: String
        var z2: String
        var xString: String

        xString = x.toString()
        if (x < 1000) { y=x.toString() }

        if ((x>=1000) && (x<1100))
        {
            z1 = xString.dropLast( 3)
            y= z1 +"K"
        }
        if ((x>=1100) && (x<20000))
        {
            z1 = xString.dropLast( 3)
            z2 = xString.dropLast( 2)
            z2 = z2.reversed()
            z2 = z2[0].toString()
            y= z1 + "."+ z2 +"K"
        }

        if ((x>=20000) && (x< 1000000))
        {
            z1 = xString.dropLast( 3)
            y= z1 + "K"
        }

        if (x>=1000000) {
            z1 = xString.dropLast(6)
            z2 = xString.dropLast(5)
            z2 = z2.reversed()
            z2 = z2[0].toString()
            y = z1 + "." + z2 + "M"
        }
        return y
    }

