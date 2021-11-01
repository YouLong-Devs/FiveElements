package me.kzheart.fiveelements.element.elementimpl

import me.kzheart.fiveelements.conf.ConfManager
import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.ElementType

/**
 *
 *@author kzheart
 *@Date 2021/10/29 12:30
 *@Descripiton
 *
 */
object WoodElement : Element {
    override val elementName: String by lazy { Element.woodLore }
    override val elementType = ElementType.WOOD

    override fun getHelpElementType() = listOf(ElementType.WATER, ElementType.FIRE)

    override fun getRestrainElementType() = listOf(ElementType.GOLDEN, ElementType.EARTH)
    override fun toString() = "wood"
}