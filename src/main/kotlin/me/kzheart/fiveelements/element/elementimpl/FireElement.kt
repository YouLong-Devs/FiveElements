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
object FireElement : Element {
    override val elementName: String by lazy { Element.fireLore }
    override val elementType = ElementType.FIRE

    override fun getHelpElementType() = ElementType.WOOD

    override fun getRestrainElementType() = ElementType.WATER
    override fun toString() = "fire"

}