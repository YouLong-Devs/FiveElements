package me.kzheart.fiveelements.database

import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.elementimpl.NoneElement
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.serverct.ersha.T
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.submit
import taboolib.module.database.ColumnBuilder
import taboolib.module.database.Host
import taboolib.module.database.Table
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * @author kzheart
 * @date 2021/10/31 13:06
 */
abstract class Database {
    val cache = ConcurrentHashMap<String, Element>()
    abstract fun pull(player: OfflinePlayer): Element
    abstract fun push(player: OfflinePlayer)
    fun modifyElement(offlinePlayer: OfflinePlayer, element: Element): Element {
        cache[offlinePlayer.name] = element
        return cache[offlinePlayer.name] ?: NoneElement
    }

    fun save() {
        cache.forEach { t, u ->
            push(Bukkit.getOfflinePlayer(t))
        }
    }

    abstract fun loadCache()
}