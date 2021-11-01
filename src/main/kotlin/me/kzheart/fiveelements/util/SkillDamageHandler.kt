package me.kzheart.fiveelements.util

import com.sucy.skill.api.skills.Skill
import me.kzheart.fiveelements.api.event.ChangeSource
import me.kzheart.fiveelements.conf.ConfManager
import me.kzheart.fiveelements.element.Relation
import me.kzheart.fiveelements.element.getMainElement
import org.bukkit.entity.LivingEntity

/**
 *
 *@author kzheart
 *@Date 2021/10/30 11:30
 *@Descripiton
 *
 */
object SkillDamageHandler {
    fun handle(damager: LivingEntity, livingEntity: LivingEntity, skill: Skill, damage: Double): Double {
        if (!ConfManager.skillsMapping.containsKey(skill.name)) return damage
        val skillElement = ConfManager.skillsMapping[skill.name]!!
        val mainElement = damager.getMainElement()
        if (mainElement.elementRelation(skillElement) == Relation.HELP) {
            val helpDamagePercent = mainElement.getHelpDamagePercent()
            return DamageValueHandler.handle(
                damager,
                livingEntity,
                damage,
                helpDamagePercent,
                Relation.HELP,
                ChangeSource.SKILLAPI
            )
        }
        if (mainElement.elementRelation(skillElement) == Relation.RESTRAIN) {
            val restrainDamagePercent = mainElement.getRestrainDamagePercent()
            return DamageValueHandler.handle(
                damager,
                livingEntity,
                damage,
                restrainDamagePercent,
                Relation.RESTRAIN,
                ChangeSource.SKILLAPI
            )
        }
        return damage
    }

}