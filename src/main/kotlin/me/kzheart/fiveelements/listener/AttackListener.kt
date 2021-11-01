package me.kzheart.fiveelements.listener

import com.sucy.skill.api.event.SkillDamageEvent
import me.kzheart.fiveelements.FiveElements
import me.kzheart.fiveelements.conf.ConfManager
import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.ElementAttribute
import me.kzheart.fiveelements.element.ElementType
import me.kzheart.fiveelements.element.elementimpl.NoneElement
import me.kzheart.fiveelements.element.getMainElement
import me.kzheart.fiveelements.util.AttackDamageHandler
import me.kzheart.fiveelements.util.SkillDamageHandler
import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.serverct.ersha.api.event.AttrEntityAttackEvent
import org.serverct.ersha.listener.AttrAttackListener
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.info
import taboolib.common.platform.function.warning
import taboolib.common5.LoreMap

/**
 *
 *@author kzheart
 *@Date 2021/10/29 18:07
 *@Descripiton
 *
 */
object AttackListener {
    private val matcher by lazy {
        LoreMap<ElementAttribute>(true, true, true).also {
            it.put(ConfManager.config.getString("options.lore", "元素属性:") + ":", ElementAttribute())
        }
    }

    @SubscribeEvent(ignoreCancelled = true)
    fun e(e: AttrEntityAttackEvent) {
        val damager = e.attackerOrKiller
        if (damager.getMainElement() == NoneElement) return
        e.attributeHandle.updateDamage(
            damager,
            AttackDamageHandler.handle(
                damager,
                e.entity,
                e.attributeHandle.getDamage(damager)
            ) - e.attributeHandle.getDamage(
                damager
            )
        )
    }


    fun ItemStack?.getElement(): Element {

        val lore = this?.itemMeta?.lore ?: return NoneElement
        lore.forEach {
            val matchResult = matcher.getMatchResult(it) ?: return@forEach
            Element.getElementByOptionLore(matchResult.remain).elementType
            if (matchResult.remain == null || Element.getElementByOptionLore(matchResult.remain).elementType == ElementType.NONE)
                return@forEach;

            return Element.getElementByOptionLore(matchResult.remain)
        }
        return NoneElement
    }

    fun LivingEntity.getElements(): List<Element> {
        val list = arrayListOf<Element>()
        equipment.also { it ->
            it.armorContents.forEach {
                list.add(it.getElement())
            }
            list.add(it.itemInMainHand.getElement())
            list.add(it.itemInOffHand.getElement())
        }
        return list.filter { it.elementType != ElementType.NONE }
    }
}