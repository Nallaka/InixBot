package me.nallaka.inixbot.utils

import com.esotericsoftware.yamlbeans.YamlReader
import java.io.FileReader

class Consts {
    companion object {
        //Default Command Prefix
        val DEFAULT_COMMAND_PREFIX = "_"

        //User Command Prefix
        var USER_COMMAND_PREFIX = "_"

        //File Paths
        val BOT_CONFIG_FILE_PATH = "${System.getProperty("user.dir")}/botConfig.yml"
        val PERMISSIONS_FILE_PATH = "${System.getProperty("user.dir")}/permissions.yml"

        /*
        * botConfig
        */

        //botConfig YamlReader
        val botConfigYamlReader = YamlReader(FileReader(BOT_CONFIG_FILE_PATH))

        //BOT_CONFIG Map
        val BOT_CONFIG = botConfigYamlReader.read() as? Map<*,*>

        //Get BOT_CONFIG properties
        val BOT_NAME = BOT_CONFIG?.get("botName") as? String
        val BOT_AVATAR = BOT_CONFIG?.get("botAvatar") as? String
        val BOT_TOKEN = BOT_CONFIG?.get("botToken") as? String
    }
}