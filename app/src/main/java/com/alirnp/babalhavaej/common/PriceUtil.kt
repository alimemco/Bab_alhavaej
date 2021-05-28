package com.alirnp.babalhavaej.common

import java.text.DecimalFormat

class PriceUtil {
    companion object{
        fun splitDigits(number: Int): String {
            return DecimalFormat("###,###,###").format(number)
        }
    }
}