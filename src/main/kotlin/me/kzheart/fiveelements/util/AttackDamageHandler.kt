package me.kzheart.fiveelements.util

import me.kzheart.fiveelements.api.event.ChangeCause
import me.kzheart.fiveelements.api.event.ChangeSource
import me.kzheart.fiveelements.api.event.DamageChangeEvent
import me.kzheart.fiveelements.element.Relation
import me.kzheart.fiveelements.element.getMainElement
import me.kzheart.fiveelements.listener.AttackListener.getElements
import org.bukkit.entity.LivingEntity

/**
 *
 *@author kzheart
 *@Date 2021/10/30 11:38
 *@Descripiton
 *
 */
object AttackDamageHandler {
    fun handle(damager: LivingEntity, livingEntity: LivingEntity,damage: Double): Double {
        val mainElement = damager.getMainElement()
        damager.getElements().filter { mainElement.elementRelation(it) != Relation.NORMAL }.forEach {
            val relation = mainElement.elementRelation(it)
            if (relation == Relation.HELP) {
                val helpDamagePercent = mainElement.getHelpDamagePercent()
                return DamageValueHandler.handle(damager,livingEntity,damage,helpDamagePercent,Relation.HELP,ChangeSource.COMMON_ATTACK)
            }
            if (relation == Relation.RESTRAIN) {
                val restrainDamagePercent = mainElement.getRestrainDamagePercent()
                return DamageValueHandler.handle(damager,livingEntity,damage,restrainDamagePercent,Relation.RESTRAIN,ChangeSource.COMMON_ATTACK)
            }
        }
        return damage
    }
}