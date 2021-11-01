package me.kzheart.fiveelements.database

import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.elementimpl.NoneElement
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import taboolib.common.platform.function.getDataFolder
import taboolib.module.database.ColumnOptionSQLite
import taboolib.module.database.ColumnTypeSQLite
import taboolib.module.database.Table
import taboolib.module.database.getHost
import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * @author kzheart
 * @date 2021/10/31 0:13
 */
class DatabaseLocal : Database() {
    val host = File(getDataFolder(), "data.db").getHost()
    val table = Table("player_element", host) {
        add {
            name("user")
            type(ColumnTypeSQLite.TEXT, 36) {
                options(ColumnOptionSQLite.PRIMARY_KEY)
            }
        }
        add {
            name("element")
            type(ColumnTypeSQLite.TEXT)
        }
    }
    val dataSource = host.createDataSource()

    init {
        table.workspace(dataSource) { createTable(true) }.run()
    }

    override fun pull(player: OfflinePlayer): Element {
        return cache.computeIfAbsent(player.name) {
            table.workspace(dataSource) {
                select { where { "user" eq player.name } }
            }.firstOrNull {
                Element.getElementByName(getString("element"))
            } ?: NoneElement
        }
    }

    override fun push(player: OfflinePlayer) {
        val element = cache[player.name] ?: return
        if (table.workspace(dataSource) { select { where { "user" eq player.name } } }.find()) {
            table.workspace(dataSource) {
                update {
                    set("element", element.toString())
                    where {
                        "user" eq player.name
                    }
                }
            }.run()
        } else {
            table.workspace(dataSource) {
                insert("user", "element") {
                    value(player.name, element.toString())
                }
            }.run()
        }
    }

    override fun loadCache() {
        table.workspace(dataSource) {
            select { }
        }.forEach {
            cache[this.getString("user")] = Element.getElementByName(this.getString("element"))
        }
    }


}