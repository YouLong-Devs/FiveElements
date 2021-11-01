package me.kzheart.fiveelements.element.elementimpl

import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.ElementType
import me.kzheart.fiveelements.element.Relation

object NoneElement : Element {
    override val elementName: String = "null"
    override val elementType = ElementType.NONE

    override fun elementRelation(element: Element) = Relation.NORMAL

    override fun getHelpElementType() = emptyList<ElementType>()

    override fun getRestrainElementType() = emptyList<ElementType>()

    override fun getHelpDamagePercent(): Double {
        return 0.0
    }

    override fun getRestrainDamagePercent(): Double {
        return 0.0
    }

    override fun toString() = "none"
}