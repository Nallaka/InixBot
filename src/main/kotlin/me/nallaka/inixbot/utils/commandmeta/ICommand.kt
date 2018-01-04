package me.nallaka.inixbot.utils.commandmeta

import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ICommand(
        val name: String = "",
        val emoji: String = "",
        val description: String = "No description valid for this command.",
        val usage: String = "No usage valid for this command.",
        val aliases: Array<String> = [],
        val commandPermissionLevel: PermissionLevel = PermissionLevel.DEFAULT,
        val isOwnerOnly: Boolean = false
        )
