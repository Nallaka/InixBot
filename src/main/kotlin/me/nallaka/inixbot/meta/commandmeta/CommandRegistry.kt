package me.nallaka.inixbot.meta.commandmeta

import me.nallaka.inixbot.commands.`fun`.CoinFlipCommand

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
        commandRegistry.put("flipcoin", CoinFlipCommand())

    }

}