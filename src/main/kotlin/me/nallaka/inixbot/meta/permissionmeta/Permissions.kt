package me.nallaka.inixbot.meta.permissionmeta

import com.esotericsoftware.yamlbeans.YamlReader
import com.esotericsoftware.yamlbeans.YamlWriter
import me.nallaka.inixbot.meta.commandmeta.Command
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.entities.User
import java.io.FileReader
import java.io.FileWriter

class Permissions {
    companion object {
        //Filepath
        private var filePath = System.getProperty("user.dir") + "/src/main/kotlin/resources/permissions.yml"

        //Create userPermissionMap
        var userPermissionRegistry: HashMap<String, PermissionLevel> = hashMapOf()

        //Create YamlReader and YamlWriter
        private var yamlReader = YamlReader(FileReader(filePath))
    }

    //setGuildUsersDefaultPermissions: Gets all users not present in the userPermissionRegistry and gives default permission
    fun setGuildUsersDefaultPermissions(jda: JDA) {
        val userList = jda.users
        userList
                .filter { !userPermissionRegistry.containsKey(it.id) && !it.isBot }
                .forEach { userPermissionRegistry.put(it.id, PermissionLevel.DEFAULT) }
        updateYaml()
    }

    //loadGuildUsersPermissions: Loads permissions levels from file to userPermissionRegistry
    fun loadGuildUsersPermissions() {
        val yamlPermissionRegistry: HashMap<String, String> = yamlReader.read() as HashMap<String, String>
        var tempPermissionLevel: PermissionLevel = PermissionLevel.DEFAULT
        for (entry: Map.Entry<String,String> in yamlPermissionRegistry) {
            if (!userPermissionRegistry.containsKey(entry.key)) {
                when (entry.value) {
                    "DEFAULT" -> tempPermissionLevel = PermissionLevel.DEFAULT
                    "LOW" -> tempPermissionLevel = PermissionLevel.LOW
                    "MEDIUM" -> tempPermissionLevel = PermissionLevel.MEDIUM
                    "HIGH" -> tempPermissionLevel = PermissionLevel.HIGH
                    "ADMIN" -> tempPermissionLevel = PermissionLevel.ADMIN
                    "OWNER" -> tempPermissionLevel = PermissionLevel.OWNER
                }
                userPermissionRegistry.put(entry.key, tempPermissionLevel)
            }
        }
        updateYaml()
    }

    //setUserPermissionLevel: Sets a particular user's permission level and updates yaml
    fun setUserPermissionLevel(user: User, permissionLevel: PermissionLevel) {
        if (userPermissionRegistry.containsKey(user.id)) {
            userPermissionRegistry.remove(user.id)
        }
        userPermissionRegistry.put(user.id, permissionLevel)
        updateYaml()
    }

    //removeUser: Removes a user from the registry
    fun removeUser(user: User) {
        if (userPermissionRegistry.containsKey(user.id)) {
            userPermissionRegistry.remove(user.id)
        }
        updateYaml()
    }

    //userHasPermissionLevel: Returns is user has designated permissionLevel or higher
    fun userHasPermissionLevel(user: User, permissionLevel: PermissionLevel) : Boolean =
            userPermissionRegistry.containsKey(user.id) && userPermissionRegistry[user.id]?.getPermissionLevelOrdinal()!! >= permissionLevel.getPermissionLevelOrdinal()

    //userHasCommandPermission: Returns if user has permission for a command
    fun userHasCommandPermission(user: User, command: Command?) : Boolean =
            userPermissionRegistry.get(user.id)?.ordinal!! >= command?.commandPermissionLevel!!.getPermissionLevelOrdinal()

    //printPermissions: Prints all Users and corresponding PermissionLevel
    fun printPermissions() {
        for (entry: Map.Entry<String, PermissionLevel> in userPermissionRegistry) {
            println("[User] ${entry.key} [Permission] ${entry.value}")
        }
    }

    //Update the permissions.yml file with all changes
    private fun updateYaml() {
        val yamlWriter = YamlWriter(FileWriter(filePath))
        try {
            with(yamlWriter) {
                write(userPermissionRegistry)
                close()
            }
        } catch (e: com.esotericsoftware.yamlbeans.emitter.EmitterException) {
            e.printStackTrace()
        }
        yamlWriter.clearAnchors()
    }
}