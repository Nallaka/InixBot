package me.nallaka.inixbot.utils

import com.esotericsoftware.yamlbeans.YamlReader
import com.esotericsoftware.yamlbeans.YamlWriter
import java.io.FileReader
import java.io.FileWriter

class BotProperties {
    companion object {
        //Default Command Prefix
        val DEFAULT_COMMAND_PREFIX = "_"

        //File Paths
        val BOT_CONFIG_FILE_PATH = "${System.getProperty("user.dir")}/botConfig.yml"
        val PERMISSIONS_FILE_PATH = "${System.getProperty("user.dir")}/permissions.yml"

        /*
        * botConfig
        */

        //botConfig YamlReader
        val botConfigYamlReader = YamlReader(FileReader(BOT_CONFIG_FILE_PATH))

        //BOT_CONFIG Map
        var BOT_CONFIG = botConfigYamlReader.read() as? HashMap<String, String>


        //Get BOT_CONFIG properties
        val BOT_NAME = BOT_CONFIG!!["botName"]
        val BOT_AVATAR = BOT_CONFIG!!["botAvatar"]
        val BOT_TOKEN = BOT_CONFIG!!["botToken"]
        var USER_COMMAND_PREFIX: String = BOT_CONFIG!!["userPrefix"] as String
        val DELETE_BOT_MESSAGES = BOT_CONFIG!!["deleteBotMessages"] == "true"
        val TIME_TO_DELETE_BOT_MESSAGES = BOT_CONFIG!!["timeToDeleteBotMessages"]!!.toLong()
    }
}