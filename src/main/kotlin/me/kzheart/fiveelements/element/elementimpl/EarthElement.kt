package me.kzheart.fiveelements.element.elementimpl

import me.kzheart.fiveelements.conf.ConfManager
import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.ElementType
import me.kzheart.fiveelements.util.RandomGenerator


/**
 *
 *@author kzheart
 *@Date 2021/10/29 12:32
 *@Descripiton
 *
 */
object EarthElement : Element {
    override val elementName: String by lazy { Element.earthLore }
    override val elementType = ElementType.EARTH

    override fun getHelpElementType() = listOf(ElementType.FIRE, ElementType.GOLDEN)

    override fun getRestrainElementType() = listOf(ElementType.WOOD, ElementType.WATER)

    override fun toString() = "earth"
}