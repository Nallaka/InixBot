package me.nallaka.inixbot.utils.permissionmeta

import com.esotericsoftware.yamlbeans.YamlReader
import com.esotericsoftware.yamlbeans.YamlWriter
import me.nallaka.inixbot.utils.BotProperties
import me.nallaka.inixbot.utils.commandmeta.Command
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.entities.User
import java.io.FileReader
import java.io.FileWriter

class Permissions {
    companion object {
        //Create userPermissionMap
        var userPermissionRegistry: HashMap<String, PermissionLevel> = hashMapOf()

        //Create YamlReader and YamlWriter
        private var permissionYamlReader = YamlReader(FileReader(BotProperties.PERMISSIONS_FILE_PATH))
    }

    //setGuildUsersDefaultPermissions: Gets all users not present in the userPermissionRegistry and gives default permission
    fun setGuildUsersDefaultPermissions(jda: JDA) {
        val userList = jda.users
        val guild = jda.guilds[0]
        userList
                .filter { !userPermissionRegistry.containsKey(it.id) && !it.isBot }
                .forEach {
                    if (it.id != guild.owner.user.id) {
                        userPermissionRegistry.put(it.id, PermissionLevel.DEFAULT)
                    } else {
                        userPermissionRegistry.put(it.id, PermissionLevel.OWNER)
                    }
                }
        updateYaml()
    }

    //loadGuildUsersPermissions: Loads permissions levels from file to userPermissionRegistry
    fun loadGuildUsersPermissions() {
        val yamlPermissionRegistry: HashMap<String, String> = permissionYamlReader.read() as HashMap<String, String>
        var tempPermissionLevel: PermissionLevel
        for (entry: Map.Entry<String,String> in yamlPermissionRegistry) {
            if (!userPermissionRegistry.containsKey(entry.key)) {
                tempPermissionLevel = when (entry.value) {
                    "DEFAULT" -> PermissionLevel.DEFAULT
                    "LOW" -> PermissionLevel.LOW
                    "MEDIUM" -> PermissionLevel.MEDIUM
                    "HIGH" -> PermissionLevel.HIGH
                    "ADMIN" -> PermissionLevel.ADMIN
                    "OWNER" -> PermissionLevel.OWNER
                    else -> PermissionLevel.DEFAULT
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
            userPermissionRegistry.get(user.id)?.ordinal!! >= command?.getCmdProperties()!!.commandPermissionLevel.getPermissionLevelOrdinal()


    //Update the permissions.yml file with all changes
    private fun updateYaml() {
        val permissionYamlWriter = YamlWriter(FileWriter(BotProperties.PERMISSIONS_FILE_PATH))
        try {
            with(permissionYamlWriter) {
                write(userPermissionRegistry)
                close()
            }
        } catch (e: com.esotericsoftware.yamlbeans.emitter.EmitterException) {
            e.printStackTrace()
        }
        permissionYamlWriter.clearAnchors()
    }

    //printPermissions: Prints all Users and corresponding PermissionLevel
    fun printPermissions() {
        for (entry: Map.Entry<String, PermissionLevel> in userPermissionRegistry) {
            println("[User] ${entry.key} [Permission] ${entry.value}")
        }
    }
}