package me.kzheart.fiveelements.api.event

import org.bukkit.entity.LivingEntity
import taboolib.common.platform.event.ProxyEvent

/**
 * @author kzheart
 * @date 2021/10/31 22:54
 */
class DamageChangeEvent(
    val damager: LivingEntity,
    val livingEntity: LivingEntity,
    val baseDamage: Double,
    val changedDamage: Double,
    val percent: Double,
    val cause: ChangeCause,
    val source: ChangeSource
) : ProxyEvent() {

}