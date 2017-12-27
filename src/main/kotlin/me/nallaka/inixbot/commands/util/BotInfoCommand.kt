package me.nallaka.inixbot.commands.util

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel

class BotInfoCommand : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
    }

    override fun runHelpCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        commandMessageHandler.sendHelpMessage(commandContainer.event, embeddedMessageBuilder, "Bot Info", "Provides Information About InixBot", "_botinfo")
    }

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return true
    }
}