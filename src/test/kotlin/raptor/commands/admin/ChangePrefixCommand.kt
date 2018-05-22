package raptor.commands.admin

import com.esotericsoftware.yamlbeans.YamlWriter
import raptor.handlers.CommandHandler
import raptor.utils.BotProperties
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.ICommand
import raptor.utils.permissionmeta.PermissionLevel
import java.io.FileWriter

@ICommand(
        name = "Change Prefix",
        emoji = ":gear:",
        description = "Changes the command prefix",
        usage = "changeprefix <prefix>",
        aliases = ["changeprefix", "changeheader"],
        commandPermissionLevel = PermissionLevel.ADMIN,
        isOwnerOnly = false
)
class ChangePrefixCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        /*val cmdProperties = getCmdProperties()
        if (args.isNotEmpty()) {
            BotProperties.USER_COMMAND_PREFIX = args[0]
            val yamlWriter = YamlWriter(FileWriter(BotProperties.BOT_CONFIG_FILE_PATH))
            BotProperties.BOT_CONFIG?.put("userPrefix", args[0])
            yamlWriter.write(BotProperties.BOT_CONFIG)
            yamlWriter.close()
            embeddedMessageBuilder.addField("${cmdProperties?.name}", "Changed to ``" + args[0] + "``", true)
        } else {
            sendUsageMessage(commandContainer.event)
            return
        }
        sendMessage(commandContainer.event)*/
    }
}
