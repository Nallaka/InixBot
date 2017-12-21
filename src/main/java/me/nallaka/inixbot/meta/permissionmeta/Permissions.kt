package me.nallaka.inixbot.meta.permissionmeta

import com.esotericsoftware.yamlbeans.YamlReader
import com.esotericsoftware.yamlbeans.YamlWriter
import net.dv8tion.jda.core.entities.User
import java.io.FileReader
import java.io.FileWriter

class Permissions {
    //Filepath
    private var filePath = "src/resources/permissions.yml"

    //Create userPermissionMap
    private var userPermissionMap: Map<String, PermissionLevel> = mapOf()

    //Create YamlReader and YamlWriter
    private var yamlReader = YamlReader(FileReader(filePath))
    private var yamlWriter = YamlWriter(FileWriter(filePath))

    //TODO: setGuildUsersDefaultPermissionLevels. Set default permission levels for all Guild Users.
    //TODO: loadGuildUsersPermissionLevels. Load levels from config to working map
    //TODO: setUserPermissionLevel. Set a user's permission level.
    //TODO: userHasPermissionLevel. Check if User has a permission level


    //Update the permissions.yml file with all changes
    fun updateYaml() {
        yamlWriter.run {
            write(userPermissionMap)
            close()
        }
    }
}