package raptor.commands.util

import bsh.Interpreter
import bsh.InterpreterError
import raptor.handlers.CommandHandler
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.ICommand
import raptor.utils.permissionmeta.PermissionLevel

@ICommand(
        name = "Execute",
        emoji = ":computer:",
        description = "Executes a Java Expression",
        usage = "execute <expression>",
        aliases = ["execute", "getval"],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = true
)
class ExecuteCommand : Command() {
    private var interpreter: Interpreter = Interpreter()

    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
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

        sendMessage(commandContainer.event)

    }
}