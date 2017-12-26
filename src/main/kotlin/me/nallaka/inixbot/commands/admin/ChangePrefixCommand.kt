package me.nallaka.inixbot.commands.admin

import me.nallaka.inixbot.InixBot
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel

class ChangePrefixCommand : Command(PermissionLevel.ADMIN) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        if (args.isNotEmpty()) {
            InixBot.USER_COMMAND_PREFIX = args[0]
            println(InixBot.USER_COMMAND_PREFIX)
            embeddedMessageBuilder.addField("Command Header -", "Changed to ``" + args[0] + "``", true)
        } else if (!args.isNotEmpty()) {
            embeddedMessageBuilder.addField("ERROR :no_entry:", "Input a New Header", true)
        }
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }

    override fun runHelpCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        commandMessageHandler.sendHelpMessage(commandContainer.event, embeddedMessageBuilder, "Change Command Header :gear:", "Changes the Command Prefix", "changeheader <header>")
    }

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return false
    }
}