package me.nallaka.inixbot.commands.util

import bsh.Interpreter
import bsh.InterpreterError
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
@ICommand(
        name = "Execute",
        emoji = ":computer:",
        description = "Executes a Java Expression",
        usage = "_execute <expression>",
        aliases = [""],
        isAdminOnly = false,
        isOwnerOnly = true
)
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

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return true
    }
}