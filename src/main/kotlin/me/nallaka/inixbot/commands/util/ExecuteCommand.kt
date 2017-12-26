package me.nallaka.inixbot.commands.util

import bsh.Interpreter
import bsh.InterpreterError
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel

class ExecuteCommand : Command(PermissionLevel.DEFAULT) {
    private var interpreter: Interpreter = Interpreter()

    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        if (commandContainer.author.id == "131068934907494400") {
            interpreter.set("event", commandContainer.event)
            interpreter.set("args", args)

            if (args.isNotEmpty()) {
                embeddedMessageBuilder.setTitle("Execute :computer:")
                try {
                    interpreter.eval("value = ${commandContainer.content.substring(8)}")
                    embeddedMessageBuilder.addField("Returned", "${interpreter.get("value")}", true)
                } catch (e: InterpreterError) {
                    embeddedMessageBuilder.addField("ERROR :no_entry:", "Can Not Evaluate", true)
                }
            } else {
                embeddedMessageBuilder.addField("ERROR :no_entry:", "Please Input an Expression", true)
            }
        } else {
            embeddedMessageBuilder.addField("ERROR :no_entry:", "Execute is only usable by <@131068934907494400>", true)
        }
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)

    }

    override fun runHelpCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
    }

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return true
    }
}