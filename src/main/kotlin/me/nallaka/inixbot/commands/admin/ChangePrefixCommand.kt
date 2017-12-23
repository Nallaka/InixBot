package me.nallaka.inixbot.commands.admin

import me.nallaka.inixbot.InixBot
import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class ChangePrefixCommand : Command(PermissionLevel.ADMIN) {
    override fun runCommand(event: MessageReceivedEvent, args: Array<String>) {
        if (args.size > 1) {
            InixBot.USER_COMMAND_PREFIX = args[1]
            embeddedMessageBuilder.addField("Command Header -", "Changed to ``" + args[1] + "``", true)
        } else if (args.size == 1) {
            embeddedMessageBuilder.addField("ERROR :no_entry:", "Input a New Header", true)
        }
        messageHandler.sendMessage(event, embeddedMessageBuilder)
    }

    override fun runHelpCommand(event: MessageReceivedEvent, args: Array<String>) {
        messageHandler.sendHelpMessage(event, embeddedMessageBuilder, "Change Command Header :gear:", "Changes the Command Prefix", "changeheader <header>")
    }

    override fun executed(event: MessageReceivedEvent, args: Array<String>): Boolean {
        commandLogger.logCommand(event, args)
        return false
    }
}