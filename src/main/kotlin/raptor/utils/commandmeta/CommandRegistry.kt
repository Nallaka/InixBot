package raptor.utils.commandmeta

import raptor.commands.`fun`.CoinFlipCommand
import raptor.commands.`fun`.EightBallCommand
import raptor.commands.`fun`.HelloCommand
import raptor.commands.`fun`.RngCommand
import raptor.commands.admin.ChangePrefixCommand
import raptor.commands.admin.ShutdownCommand
import raptor.commands.info.BotInfoCommand
import raptor.commands.info.DevInfoCommand
import raptor.commands.info.GuildInfoCommand
import raptor.commands.info.UserInfoCommand
import raptor.commands.util.ExecuteCommand
import raptor.commands.util.HelpCommand
import raptor.commands.util.PingCommand
import raptor.commands.util.SearchCommand

class CommandRegistry {
    companion object {
        //Initialize CommandRegistry
        var commandRegistry: HashMap<String, Command> = hashMapOf()
    }

    //Sets commandRegistry with original Commands
    fun setCommandRegistry() {
        //Admin Commands
        registerCommand(ChangePrefixCommand())
        registerCommand(ShutdownCommand())

        //Fun Commands
        registerCommand(CoinFlipCommand())
        registerCommand(EightBallCommand())
        registerCommand(HelloCommand())
        registerCommand(RngCommand())

        //Info Commands
        registerCommand(BotInfoCommand())
        registerCommand(DevInfoCommand())
        registerCommand(GuildInfoCommand())
        registerCommand(UserInfoCommand())

        //Moderation Commands

        //Music Commands

        //Util Commands
        registerCommand(ExecuteCommand())
        registerCommand(HelpCommand())
        registerCommand(PingCommand())
        registerCommand(SearchCommand())
    }

    //registerCommand: Registers a new command with all given aliases
    fun registerCommand(command: Command) {
        if (!command.javaClass.isAnnotationPresent(ICommand::class.java)) {
            println("[ERROR] No aliases given in command ${command.javaClass.name}")
        } else {
            for (alias: String in command.getCmdProperties()!!.aliases) {
                commandRegistry.put(alias.toLowerCase(), command)
            }
        }
    }

    //getCommandRegistry: Returns complete commandRegistry
    fun getCommandRegistry(): MutableMap<String, Command> = commandRegistry

    fun getCommand(commandString: String): Command? = commandRegistry[commandString]
}