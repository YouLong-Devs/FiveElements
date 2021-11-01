package me.kzheart.fiveelements.listener

import com.sucy.skill.api.event.SkillDamageEvent
import me.kzheart.fiveelements.element.elementimpl.NoneElement
import me.kzheart.fiveelements.element.getMainElement
import me.kzheart.fiveelements.util.AttackDamageHandler
import me.kzheart.fiveelements.util.SkillDamageHandler
import taboolib.common.platform.event.SubscribeEvent

/**
 * @author kzheart
 * @date 2021/10/31 1:18
 */
object SkillListener {

    @SubscribeEvent(ignoreCancelled = true)
    fun onSkillDamage(e: SkillDamageEvent) {
        val damager = e.damager ?: return
        if (damager.getMainElement() == NoneElement) return
        e.damage = SkillDamageHandler.handle(damager, e.target, e.skill, e.damage)
    }
}