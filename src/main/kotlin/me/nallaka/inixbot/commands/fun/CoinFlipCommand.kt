package me.nallaka.inixbot.commands.`fun`

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel

class CoinFlipCommand(commandPermissionLevel: PermissionLevel) : Command(commandPermissionLevel) {

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

    override fun runHelpCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.setTitle("Coin Flip")
                .setDescription("Flips a Coin")
                .addField("Usage", "``" + defaultCommandPrefix + "coinflip``", true)
        commandMessageHandler.sendHelpMessage(commandContainer.event, embeddedMessageBuilder, "Coin Flip", "Flips a Coin", "coinflip")
    }

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return true
    }
}