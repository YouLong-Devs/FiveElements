package me.kzheart.fiveelements.util

import kotlin.math.max
import kotlin.math.min

/**
 *
 *@author kzheart
 *@Date 2021/10/30 12:07
 *@Descripiton
 *
 */
class RandomGenerator(private val helpMin: Double, private val helpMax: Double,private val restrainMin:Double,private val restrainMax: Double) {
    fun randomHelp(): Double {
        val data = taboolib.common.util.random(helpMin,helpMax)
        return data
    }
    fun randomRestrain():Double{
        return taboolib.common.util.random(restrainMin,restrainMax)
    }

    override fun toString(): String {
        return "RandomGenerator(helpMin=$helpMin, helpMax=$helpMax, restrainMin=$restrainMin, restrainMax=$restrainMax)"
    }

}

