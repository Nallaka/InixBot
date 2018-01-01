package me.nallaka.inixbot.utils.commandmeta

import me.nallaka.inixbot.commands.`fun`.CoinFlipCommand
import me.nallaka.inixbot.commands.`fun`.EightBallCommand
import me.nallaka.inixbot.commands.`fun`.HelloCommand
import me.nallaka.inixbot.commands.`fun`.RngCommand
import me.nallaka.inixbot.commands.admin.ChangePrefixCommand
import me.nallaka.inixbot.commands.admin.ShutdownCommand
import me.nallaka.inixbot.commands.info.BotInfoCommand
import me.nallaka.inixbot.commands.info.GuildInfoCommand
import me.nallaka.inixbot.commands.info.UserInfoCommand
import me.nallaka.inixbot.commands.util.ExecuteCommand
import me.nallaka.inixbot.commands.util.HelpCommand
import me.nallaka.inixbot.commands.util.PingCommand
import me.nallaka.inixbot.commands.util.SearchCommand

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

        //Moderation Commands

        //Music Commands

        //Fun Commands
        registerCommand(CoinFlipCommand())
        registerCommand(EightBallCommand())
        registerCommand(HelloCommand())
        registerCommand(RngCommand())

        //Util Commands
        registerCommand(HelpCommand())
        registerCommand(PingCommand())
        registerCommand(SearchCommand())
        registerCommand(ExecuteCommand())

        //Info Commands
        registerCommand(BotInfoCommand())
        registerCommand(UserInfoCommand())
        registerCommand(GuildInfoCommand())
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
    fun getCommandRegistry() : MutableMap<String, Command> = commandRegistry

    fun getCommand(commandString: String ) : Command? = commandRegistry[commandString]
}