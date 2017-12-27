package me.nallaka.inixbot.utils.commandmeta

import me.nallaka.inixbot.commands.`fun`.CoinFlipCommand
import me.nallaka.inixbot.commands.`fun`.EightBallCommand
import me.nallaka.inixbot.commands.`fun`.HelloCommand
import me.nallaka.inixbot.commands.`fun`.RngCommand
import me.nallaka.inixbot.commands.admin.ChangePrefixCommand
import me.nallaka.inixbot.commands.util.ExecuteCommand
import me.nallaka.inixbot.commands.util.HelpCommand
import me.nallaka.inixbot.commands.util.PingCommand
import me.nallaka.inixbot.commands.util.SearchCommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel

class CommandRegistry {
    companion object {
        //Initialize CommandRegistry
        var commandRegistry: HashMap<String, Command> = hashMapOf()
    }
    //Adds new command to registry with name "commandString" and Command "command"
    fun setCommand(commandString: String, command: Command) = commandRegistry.put(commandString, command)

    //Gets command with Key "commandString"
    fun getCommand(commandString: String ) : Command? = commandRegistry[commandString]

    //Checks if commandRegistry contains Key "key"
    fun registryContainsKey(key: String) : Boolean = commandRegistry.containsKey(key)

    //Returns complete commandRegistry
    fun getCommandRegistry() : MutableMap<String, Command> = commandRegistry

    //Sets commandRegistry with original Commands
    fun setCommandRegistry() {
        //Admin Commands
        commandRegistry.put("changeprefix", ChangePrefixCommand())
        //Moderation Commands
        //Music Commands
        //Fun Commands
        commandRegistry.put("coinflip", CoinFlipCommand(PermissionLevel.ADMIN))
        commandRegistry.put("8ball", EightBallCommand())
        commandRegistry.put("hello", HelloCommand())
        commandRegistry.put("rolldice", RngCommand())
        //Util Commands
        commandRegistry.put("help", HelpCommand())
        commandRegistry.put("ping", PingCommand())
        commandRegistry.put("search", SearchCommand())
        commandRegistry.put("execute", ExecuteCommand())
    }

}