package me.nallaka.inixbot.commands.`fun`

import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class CoinFlipCommand : Command(PermissionLevel.ADMIN) {
    override fun runCommand(event: MessageReceivedEvent, args: Array<String>) {
        val random = (Math.random() * 1000).toInt()
        val flip: String
        flip = if (random % 2 == 0) {
            "Heads"
        } else {
            "Tails"
        }
        println("test works")
        embeddedMessageBuilder.addField("You Flipped - ", flip, true)
        messageHandler.sendMessage(event, embeddedMessageBuilder)
    }

    override fun runHelpCommand(event: MessageReceivedEvent, args: Array<String>) {
        embeddedMessageBuilder.setTitle("Coin Flip")
                .setDescription("Flips a Coin")
                .addField("Usage", "``" + defaultCommandPrefix + "coinflip``", true)
        messageHandler.sendHelpMessage(event, embeddedMessageBuilder, "Coin Flip", "Flips a Coin", "coinflip")
    }

    override fun executed(event: MessageReceivedEvent, args: Array<String>): Boolean {
        commandLogger.logCommand(event, args)
        return true
    }
}