package me.kzheart.fiveelements.database

import me.kzheart.fiveelements.FiveElements
import me.kzheart.fiveelements.conf.ConfManager
import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.elementimpl.NoneElement
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import taboolib.common.platform.function.getDataFolder
import taboolib.library.configuration.Configuration
import taboolib.library.configuration.YamlConfiguration
import taboolib.module.database.*
import java.io.File

/**
 * @author kzheart
 * @date 2021/10/31 13:35
 */
class DatabaseSQL : Database() {
    val host = HostSQL(ConfManager.config.getConfigurationSection("options.database"))
    val table = Table("player_element", host) {
        add {
            name("user")
            type(ColumnTypeSQL.VARCHAR,255) {
                options(ColumnOptionSQL.PRIMARY_KEY)
            }
        }
        add {
            name("element")
            type(ColumnTypeSQL.VARCHAR,255)
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

/*    fun pull(player: Player): Element {
        return cache.computeIfAbsent(player.uniqueId.toString()) {
            table.workspace(dataSource) {
                select { where { "user" eq player.uniqueId.toString() } }
            }.firstOrNull {
                Element.getElementByName(getString("element"))
            } ?: NoneElement
        }
    }

    fun push(player: Player) {
        val element = cache[player.uniqueId.toString()] ?: return
        if (table.workspace(dataSource) { select { where { "user" eq player.uniqueId.toString() } } }.find()) {
            table.workspace(dataSource) {
                update {
                    set("element", element.toString())
                    where {
                        "user" eq player.uniqueId.toString()
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
    }*/
}