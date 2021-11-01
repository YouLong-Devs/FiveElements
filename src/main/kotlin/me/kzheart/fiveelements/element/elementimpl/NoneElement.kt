package me.kzheart.fiveelements.element.elementimpl

import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.ElementType
import me.kzheart.fiveelements.element.Relation

object NoneElement : Element {
    override val elementName: String = "null"
    override val elementType = ElementType.NONE

    override fun elementRelation(element: Element): Relation {
        return Relation.NORMAL
    }

    override fun getHelpElementType(): ElementType {
        return ElementType.NONE;
    }

    override fun getRestrainElementType(): ElementType {
        return ElementType.NONE;
    }

    override fun getHelpDamagePercent(): Double {
        return 0.0
    }

    override fun getRestrainDamagePercent(): Double {
        return 0.0
    }

    override fun toString() = "none"
}