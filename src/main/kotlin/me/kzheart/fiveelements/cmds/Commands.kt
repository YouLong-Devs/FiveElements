package me.kzheart.fiveelements.cmds

import me.kzheart.fiveelements.FiveElements
import me.kzheart.fiveelements.conf.ConfManager
import me.kzheart.fiveelements.element.Element
import me.kzheart.fiveelements.element.elementimpl.NoneElement
import me.kzheart.fiveelements.element.getMainElement
import me.kzheart.fiveelements.listener.AttackListener.getElement
import me.kzheart.fiveelements.listener.AttackListener.getElements
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import taboolib.common.platform.command.*
import taboolib.common.platform.function.onlinePlayers
import taboolib.expansion.createHelper
import taboolib.platform.type.BukkitPlayer
import taboolib.platform.util.sendLang

/**
 *
 *@author kzheart
 *@Date 2021/10/29 21:14
 *@Descripiton
 *
 */
@CommandHeader("fiveelements", ["fve"], permission = "fiveelements.admin", permissionDefault = PermissionDefault.OP)
object Commands {
    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody
    val set = subCommand {
        dynamic {
            suggestion<CommandSender> { _, _ ->
                Bukkit.getOfflinePlayers().map { it.name!! }
            }
            dynamic {
                suggestion<CommandSender> { _, _ ->
                    listOf("golden", "wood", "water", "fire", "earth", "none")
                }
                execute<CommandSender> { sender, context, argument ->
                    val player = Bukkit.getOfflinePlayer(context.argument(-1)) ?: return@execute sender.sendLang(
                        "player-not-exist",
                        context.argument(-1)
                    )
                    val modifiedElement =
                        FiveElements.database.modifyElement(player, Element.getElementByName(argument))
                    sender.sendLang("player-set-element", player.name, modifiedElement.elementName)
                }
            }
        }
    }

    @CommandBody
    val check = subCommand {
        dynamic {
            suggestion<CommandSender> { _, _ ->
                Bukkit.getOfflinePlayers().map { it.name!! }
            }
            execute<CommandSender> { sender, context, argument ->
                val player = Bukkit.getOfflinePlayer(argument) ?: return@execute sender.sendLang(
                    "player-not-exist",
                    argument
                )
                val element = FiveElements.database.pull(player)
                if (element == NoneElement) return@execute sender.sendLang("select-player-not-element", argument)
                sender.sendLang("select-player-element", argument, element.elementName)
            }
        }
    }

    @CommandBody
    val reload = subCommand {
        execute<CommandSender> { sender, context, argument ->
            ConfManager.reload()
            sender.sendLang("config-reload")
        }

    }

/*    @CommandBody
    val cache = subCommand {
        execute<CommandSender> { sender, context, argument ->
            sender.sendMessage(FiveElements.database.cache.toString())
        }
    }

    @CommandBody
    val save = subCommand {
        execute<CommandSender> { sender, context, argument ->
            FiveElements.database.save()
        }
    }

    @CommandBody
    val skills = subCommand {
        execute<CommandSender> { sender, context, argument ->
            sender.sendMessage(ConfManager.skillsMapping.toString())
        }
    }

    @CommandBody
    val mobs = subCommand {
        execute<CommandSender> { sender, context, argument ->
            sender.sendMessage(ConfManager.mobsMapping.toString())
        }
    }

    @CommandBody
    val elements = subCommand {
        execute<Player> { sender, context, argument ->
            sender.getNearbyEntities(5.0, 5.0, 5.0).filter { it is LivingEntity }.map { it as LivingEntity }
                .filter { it.getMainElement() != NoneElement }.forEach {
                    it.getElements().forEach {

                    }
                }
        }
    }

    @CommandBody
    val hand = subCommand {
        execute<Player> { sender, context, argument ->
            sender.sendMessage(sender.itemInHand.getElement().elementName)
        }
    }*/
}
