package me.nallaka.inixbot.commands.admin

import me.nallaka.inixbot.InixBot
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel

class ShutdownCommand : Command(PermissionLevel.ADMIN) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        if (commandContainer.author.id == "131068934907494400") {
            embeddedMessageBuilder.addField("Shutting Down :radio_button:", "Bye", true)
            commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
            InixBot.jda.shutdown()
            System.exit(0)
        } else {
            embeddedMessageBuilder.addField("ERROR :no_entry:", "Only <@131068934907494400> can use this command", true
            )
        }
    }

    override fun runHelpCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
    }

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return true
    }
}