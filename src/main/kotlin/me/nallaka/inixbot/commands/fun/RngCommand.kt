package me.nallaka.inixbot.commands.`fun`

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
@ICommand(
        name = "Random Number Generator",
        emoji = ":game_die:",
        description = "Generates a Number",
        usage = "_rng <max_value>",
        aliases = [""],
        isOwnerOnly = false
)
class RngCommand : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        try {
            val diceArg = Integer.parseInt(args[0])
            if (diceArg < 1000) {
                embeddedMessageBuilder.addField("RNG :game_die:", Integer.toString((Math.random() * Integer.parseInt(args[0])).toInt()), true)
            } else {
                embeddedMessageBuilder.addField("ERROR :game_die:", "Value too high. Please try again", true)
            }
            commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
            commandMessageHandler.clearEmbeddedBuilder(embeddedMessageBuilder)
        } catch (e: ArrayIndexOutOfBoundsException) {
            embeddedMessageBuilder.addField("ERROR :game_die:", "Input a RNG value", true)
            commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
        }

    }

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return true
    }
}