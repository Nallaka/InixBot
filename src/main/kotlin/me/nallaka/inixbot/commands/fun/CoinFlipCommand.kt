package me.nallaka.inixbot.commands.`fun`

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
@ICommand(
        name = "Coin Flip",
        emoji = "",
        description = "Flips a Coin",
        usage = "_coinflip",
        aliases = [""],
        isAdminOnly = false,
        isOwnerOnly = false
)
class CoinFlipCommand : Command(PermissionLevel.DEFAULT) {

    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        val random = (Math.random() * 1000).toInt()
        val flip: String
        flip = if (random % 2 == 0) {
            "Heads"
        } else {
            "Tails"
        }
        println("test works")
        embeddedMessageBuilder.addField("You Flipped - ", flip, true)
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return true
    }
}