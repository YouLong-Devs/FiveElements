package me.kzheart.fiveelements

import com.sucy.skill.SkillAPI
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper
import me.kzheart.fiveelements.conf.ConfManager
import me.kzheart.fiveelements.database.Database
import me.kzheart.fiveelements.database.DatabaseLocal
import me.kzheart.fiveelements.database.DatabaseSQL
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.common.platform.function.submit
import taboolib.common.platform.service.PlatformExecutor

/**
 *
 *@author kzheart
 *@Date 2021/10/29 9:15
 *@Descripiton
 *
 */
object FiveElements : Plugin() {
    lateinit var database: Database
    val skillApi by lazy { SkillAPI() }
    val mythicApi by lazy { BukkitAPIHelper() }
    lateinit var saveTask: PlatformExecutor.PlatformTask
    override fun onEnable() {
        database = if (ConfManager.config.getString("options.database.type").lowercase() == "sqlite")
            DatabaseLocal() else DatabaseSQL()
        database.loadCache()
        saveTask = submit(async = true, period = 1200) {
            database.save()
        }
        info("FiveElements enabled")
    }

    override fun onDisable() {
        database.save()
        saveTask.cancel()
    }
}