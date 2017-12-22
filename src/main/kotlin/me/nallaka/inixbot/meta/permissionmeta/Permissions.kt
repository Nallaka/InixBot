package me.nallaka.inixbot.meta.permissionmeta

import com.esotericsoftware.yamlbeans.YamlReader
import com.esotericsoftware.yamlbeans.YamlWriter
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.entities.User
import java.io.FileReader
import java.io.FileWriter

class Permissions {
    //Filepath
    private var filePath = System.getProperty("user.dir") + "src/resources/permissions.yml"

    //Create userPermissionMap
    private var userPermissionRegistry: HashMap<String, PermissionLevel> = hashMapOf()

    //Create YamlReader and YamlWriter
    private var yamlReader = YamlReader(FileReader(filePath))
    private var yamlWriter = YamlWriter(FileWriter(filePath))

    //setGuildUsersDefaultPermissions: Gets all users not present in the userPermissionRegistry and gives default permission
    fun setGuildUsersDefaultPermissions(jda: JDA) {
        val userList = jda.users
        userList
                .filterNot { userPermissionRegistry.containsKey(it.id) }
                .forEach { userPermissionRegistry.put(it.id, PermissionLevel.DEFAULT) }
        updateYaml()
    }

    //loadGuildUsersPermissions: Loads permissions levels from file to userPermissionRegistry
    fun loadGuildUsersPermissions() {
        updateYaml()
        var yamlPermissionRegistry: HashMap<String, PermissionLevel> = hashMapOf(yamlReader.read() as Pair<String, PermissionLevel>)
    }
    //TODO: setUserPermissionLevel. Set a user's permission level.
    //TODO: userHasPermissionLevel. Check if User has a permission level


    //Update the permissions.yml file with all changes
    private fun updateYaml() {
        with(yamlWriter) {
            write(userPermissionRegistry)
            close()
        }
    }
}