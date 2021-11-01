package me.kzheart.fiveelements.element.elementimpl

import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.ElementType

/**
 *
 *@author kzheart
 *@Date 2021/10/29 9:47
 *@Descripiton
 *
 */
object GoldenElement : Element {
    override val elementName: String by lazy { Element.goldenLore }
    override val elementType = ElementType.GOLDEN
    override fun getHelpElementType() = listOf(ElementType.EARTH, ElementType.WATER)
    override fun getRestrainElementType() = listOf(ElementType.FIRE, ElementType.WOOD)
    override fun toString() = "golden"

}