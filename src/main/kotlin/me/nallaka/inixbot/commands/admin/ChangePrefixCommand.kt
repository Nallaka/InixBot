package me.nallaka.inixbot.commands.admin

import com.esotericsoftware.yamlbeans.YamlWriter
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.BotProperties
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
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
        val cmdProperties = getCmdProperties()
        if (args.isNotEmpty()) {
            BotProperties.USER_COMMAND_PREFIX = args[0]
            val yamlWriter = YamlWriter(FileWriter(BotProperties.BOT_CONFIG_FILE_PATH))
            BotProperties.BOT_CONFIG?.put("userPrefix", args[0])
            yamlWriter.write(BotProperties.BOT_CONFIG)
            yamlWriter.close()
            embeddedMessageBuilder.addField("${cmdProperties?.name}", "Changed to ``" + args[0] + "``", true)
        } else if (!args.isNotEmpty()) {
            embeddedMessageBuilder.addField("ERROR :no_entry:", "Input a New Header", true)
        }
        sendMessage(commandContainer.event, embeddedMessageBuilder)
    }
}