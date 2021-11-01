package me.kzheart.fiveelements.element

import io.lumine.xikage.mythicmobs.mobs.MythicMob
import me.kzheart.fiveelements.FiveElements
import me.kzheart.fiveelements.conf.ConfManager
import me.kzheart.fiveelements.element.elementimpl.*
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

/**
 *
 *@author kzheart
 *@Date 2021/10/29 9:43
 *@Descripiton
 *
 */
interface Element {
    companion object {
        val goldenLore by lazy { ConfManager.config.getString("golden.lore") }
        val woodLore by lazy { ConfManager.config.getString("wood.lore") }
        val waterLore by lazy { ConfManager.config.getString("water.lore") }
        val fireLore by lazy { ConfManager.config.getString("fire.lore") }
        val earthLore by lazy { ConfManager.config.getString("earth.lore") }

        fun getElementByOptionLore(name: String): Element {
            return when (name) {
                goldenLore -> GoldenElement
                woodLore -> WoodElement
                waterLore -> WaterElement
                fireLore -> FireElement
                earthLore -> EarthElement
                else -> {
                    NoneElement
                }
            }
        }

        fun getElementByName(name: String): Element {
            return when (name) {
                "golden" -> GoldenElement
                "wood" -> WoodElement
                "water" -> WaterElement
                "fire" -> FireElement
                "earth" -> EarthElement
                else -> NoneElement
            }
        }
    }

    abstract val elementName: String
    abstract val elementType: ElementType
    fun elementRelation(element: Element): Relation {
        return when (element.elementType) {
            getHelpElementType() -> Relation.HELP
            getRestrainElementType() -> Relation.RESTRAIN
            else -> Relation.NORMAL
        }
    }

    fun elementRestrain(element: Element): Boolean {
        return elementRelation(element) == Relation.RESTRAIN
    }

    fun elementHelp(element: Element): Boolean {
        return elementRelation(element) == Relation.HELP
    }

    fun elementNormal(element: Element): Boolean {
        return elementRelation(element) == Relation.NORMAL
    }

    fun getHelpElementType(): ElementType

    fun getRestrainElementType(): ElementType

    fun getHelpDamagePercent(): Double {
        return ConfManager.configMapping[this]!!.randomHelp()
    }

    fun getRestrainDamagePercent(): Double {
        return ConfManager.configMapping[this]!!.randomRestrain()
    }
}

fun LivingEntity.getMainElement(): Element {
    if (this is Player)
        return FiveElements.database.pull(Bukkit.getPlayer(name))
    if (FiveElements.mythicApi.isMythicMob(this)) {
        val type = FiveElements.mythicApi.getMythicMobInstance(this).mobType
        return ConfManager.mobsMapping[type] ?: NoneElement
    }
    return NoneElement
}