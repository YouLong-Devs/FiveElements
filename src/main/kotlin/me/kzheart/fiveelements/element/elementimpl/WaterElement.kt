package me.kzheart.fiveelements.element.elementimpl

import me.kzheart.fiveelements.conf.ConfManager
import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.ElementType

/**
 *
 *@author kzheart
 *@Date 2021/10/29 12:31
 *@Descripiton
 *
 */
object WaterElement : Element {
    override val elementName: String by lazy { Element.waterLore }
    override val elementType = ElementType.WATER

    override fun getHelpElementType() = listOf(ElementType.GOLDEN, ElementType.WOOD)

    override fun getRestrainElementType() = listOf(ElementType.EARTH, ElementType.FIRE)

    override fun toString() = "water"

}