package me.nallaka.inixbot.commands.`fun`

import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class RollDiceCommand : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(event: MessageReceivedEvent, args: Array<String>) {
        try {
            val diceArg = Integer.parseInt(args[1])
            if (diceArg < 1000) {
                embeddedMessageBuilder.addField("You Rolled :game_die:", Integer.toString((Math.random() * Integer.parseInt(args[1])).toInt()), true)
            } else {
                embeddedMessageBuilder.addField("ERROR :game_die:", "Dice value too high. Please try again", true)
            }
            messageHandler.sendMessage(event, embeddedMessageBuilder)
            messageHandler.clearEmbeddedBuilder(embeddedMessageBuilder)
        } catch (e: ArrayIndexOutOfBoundsException) {
            embeddedMessageBuilder.addField("ERROR :game_die:", "Input a dice value", true)
            messageHandler.sendMessage(event, embeddedMessageBuilder)
        }

    }

    override fun runHelpCommand(event: MessageReceivedEvent, args: Array<String>) {
        messageHandler.sendHelpMessage(event, embeddedMessageBuilder, "Roll Dice :game_die:", "Rolls a Die", "rolldice <number>")
    }

    override fun executed(event: MessageReceivedEvent, args: Array<String>): Boolean {
        commandLogger.logCommand(event, args)
        return true
    }
}