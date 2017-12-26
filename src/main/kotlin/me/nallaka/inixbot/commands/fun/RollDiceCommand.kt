package me.nallaka.inixbot.commands.`fun`

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel

class RollDiceCommand : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        try {
            val diceArg = Integer.parseInt(args[0])
            if (diceArg < 1000) {
                embeddedMessageBuilder.addField("You Rolled :game_die:", Integer.toString((Math.random() * Integer.parseInt(args[0])).toInt()), true)
            } else {
                embeddedMessageBuilder.addField("ERROR :game_die:", "Dice value too high. Please try again", true)
            }
            commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
            commandMessageHandler.clearEmbeddedBuilder(embeddedMessageBuilder)
        } catch (e: ArrayIndexOutOfBoundsException) {
            embeddedMessageBuilder.addField("ERROR :game_die:", "Input a dice value", true)
            commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
        }

    }

    override fun runHelpCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        commandMessageHandler.sendHelpMessage(commandContainer.event, embeddedMessageBuilder, "Roll Dice :game_die:", "Rolls a Die", "rolldice <number>")
    }

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return true
    }
}