package me.kzheart.fiveelements.api

import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.getMainElement
import me.kzheart.fiveelements.listener.AttackListener.getElement
import me.kzheart.fiveelements.listener.AttackListener.getElements
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack

/**
 *
 *@author kzheart
 *@Date 2021/10/30 11:42
 *@Descripiton
 *
 */
object FiveElementAPI {
    @JvmStatic
    fun getLivingEntityElements(livingEntity: LivingEntity): List<Element> {
        return livingEntity.getElements();
    }

    @JvmStatic
    fun getItemStackElement(itemStack: ItemStack): Element {
        return itemStack.getElement()
    }

    @JvmStatic
    fun getLivingEntityMainElement(livingEntity: LivingEntity): Element {
        return livingEntity.getMainElement();
    }
}