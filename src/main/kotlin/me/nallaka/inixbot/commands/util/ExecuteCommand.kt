package me.nallaka.inixbot.commands.util

import bsh.Interpreter
import bsh.InterpreterError
import me.nallaka.inixbot.InixBot
import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class ExecuteCommand : Command(PermissionLevel.DEFAULT) {
    private var interpreter: Interpreter = Interpreter()

    override fun runCommand(event: MessageReceivedEvent, args: Array<String>) {
        interpreter.set("event", event)
        interpreter.set("args", args)

        if (args.size > 1) {
            embeddedMessageBuilder.setTitle("Execute :computer:")
            try {
                interpreter.eval("value = ${event.message.content.substring(8)}")
                embeddedMessageBuilder.addField("Returned", "${interpreter.get("value")}", true)
            } catch (e: InterpreterError) {
                embeddedMessageBuilder.addField("ERROR :no_entry:", "Can Not Evaluate", true)
            }
        } else {
            embeddedMessageBuilder.addField("ERROR :no_entry:", "Please Input an Expression", true)
        }
        messageHandler.sendMessage(event, embeddedMessageBuilder)

    }

    override fun runHelpCommand(event: MessageReceivedEvent, args: Array<String>) {
    }

    override fun executed(event: MessageReceivedEvent, args: Array<String>): Boolean {
        commandLogger.logCommand(event, args)
        return true
    }
}