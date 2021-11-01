package me.kzheart.fiveelements.util

import me.kzheart.fiveelements.api.event.ChangeCause
import me.kzheart.fiveelements.api.event.ChangeSource
import me.kzheart.fiveelements.api.event.DamageChangeEvent
import me.kzheart.fiveelements.element.Relation
import org.bukkit.entity.LivingEntity

/**
 * @author kzheart
 * @date 2021/11/1 7:47
 */
object DamageValueHandler {
    fun handle(
        damager: LivingEntity,
        livingEntity: LivingEntity,
        baseDamage: Double,
        percent: Double,
        relation: Relation,
        source: ChangeSource
    ): Double {
        if (relation == Relation.HELP) {
            val addedDamage = baseDamage * (1.0 + percent)
            DamageChangeEvent(damager,livingEntity,baseDamage, addedDamage, percent, ChangeCause.ATTRIBUTE_HELP, source).call()
            return addedDamage
        }
        if (relation == Relation.RESTRAIN) {
            val reducedDamage = baseDamage * (1.0 - percent)
            DamageChangeEvent(damager,livingEntity,baseDamage, reducedDamage, percent, ChangeCause.ATTRIBUTE_RESTRAIN, source).call()
            return reducedDamage
        }
        return baseDamage
    }
}