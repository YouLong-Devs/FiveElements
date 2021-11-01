package me.kzheart.fiveelements.conf

import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.elementimpl.*
import me.kzheart.fiveelements.util.RandomGenerator
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.module.configuration.Config
import taboolib.module.configuration.SecuredFile

/**
 *
 *@author kzheart
 *@Date 2021/10/30 11:51
 *@Descripiton
 *
 */
object ConfManager {
    @Config("config.yml")
    lateinit var config: SecuredFile

    @Config("skills.yml")
    lateinit var skillConf: SecuredFile

    @Config("mobs.yml")
    lateinit var mobConf: SecuredFile

    val skillsMapping = hashMapOf<String, Element>()
    val mobsMapping = hashMapOf<String, Element>()
    val configMapping = hashMapOf<Element, RandomGenerator>()


    @Awake(LifeCycle.ACTIVE)
    private fun init() {
        skillsMapping.apply {
            skillConf.getStringList("golden-skills").forEach { this[it] = GoldenElement }
            skillConf.getStringList("wood-skills").forEach { this[it] = WoodElement }
            skillConf.getStringList("water-skills").forEach { this[it] = WaterElement }
            skillConf.getStringList("fire-skills").forEach { this[it] = FireElement }
            skillConf.getStringList("earth-skills").forEach { this[it] = EarthElement }
        }

        mobsMapping.apply {
            mobConf.getStringList("golden-mobs").forEach { this[it] = GoldenElement }
            mobConf.getStringList("wood-mobs").forEach { this[it] = WoodElement }
            mobConf.getStringList("water-mobs").forEach { this[it] = WaterElement }
            mobConf.getStringList("fire-mobs").forEach { this[it] = FireElement }
            mobConf.getStringList("earth-mobs").forEach { this[it] = EarthElement }
        }
        loadConfiguration("golden")
        loadConfiguration("wood")
        loadConfiguration("water")
        loadConfiguration("fire")
        loadConfiguration("earth")
    }

    private fun loadConfiguration(elementOptionName: String) {
        val helpMin: Double
        val helpMax: Double
        val restrainMin: Double
        val restrainMax: Double
        val element = Element.getElementByName(elementOptionName)
        config.getString("${elementOptionName}.help").split("~").also {
            helpMin = it[0].toDoubleOrNull()
                ?: throw RuntimeException("Failed to read ${element.elementType} configuration please check")
            helpMax = it[1].toDoubleOrNull()
                ?: throw RuntimeException("Failed to read ${element.elementType} configuration please check")
        }

        config.getString("${elementOptionName}.restrain").split("~").also {
            restrainMin = it[0].toDoubleOrNull()
                ?: throw RuntimeException("Failed to read ${element.elementType} configuration please check")
            restrainMax = it[1].toDoubleOrNull()
                ?: throw RuntimeException("Failed to read ${element.elementType} configuration please check")
        }
        configMapping[element] = RandomGenerator(helpMin, helpMax, restrainMin, restrainMax)
    }

    fun reload() {
        config.reload()
        skillConf.reload()
        mobConf.reload()
        init()
    }


}