package me.nallaka.inixbot.commands.`fun`

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel

class HelloCommand : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.setTitle("Hello :wave:").setDescription(commandContainer.author.name)
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }

    override fun runHelpCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        commandMessageHandler.sendHelpMessage(commandContainer.event, embeddedMessageBuilder, "Hello", "Simply Says Hello", "hello")
    }

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return true
    }
}