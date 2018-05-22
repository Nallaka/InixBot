package raptor.utils

import com.esotericsoftware.yamlbeans.YamlReader
import java.io.FileReader

class BotProperties {
    companion object {
        //Default Command Prefix
        val DEFAULT_COMMAND_PREFIX = "_"

        //File Paths
        val BOT_CONFIG_FILE_PATH = "${System.getProperty("user.dir")}/src/botConfig.yml"
        val PERMISSIONS_FILE_PATH = "${System.getProperty("user.dir")}/src/permissions.yml"


        // botConfig


        //botConfig YamlReader
        val botConfigYamlReader = YamlReader(FileReader(BOT_CONFIG_FILE_PATH))

        //BOT_CONFIG Map
        var BOT_CONFIG = botConfigYamlReader.read() as? HashMap<String, String>


        //Get BOT_CONFIG properties
        //val BOT_NAME = BOT_CONFIG!!["botName"]
        //val BOT_AVATAR = BOT_CONFIG!!["botAvatar"]
        //val BOT_TOKEN = BOT_CONFIG!!["botToken"]
        var USER_COMMAND_PREFIX: String = "_"
    }
}