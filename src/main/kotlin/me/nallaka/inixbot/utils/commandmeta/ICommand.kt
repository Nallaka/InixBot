package me.nallaka.inixbot.utils.commandmeta

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
annotation class ICommand(
        val name: String = "",
        val emoji: String = "",
        val description: String = "No description valid for this command.",
        val usage: String = "No usage valid for this command.",
        val aliases: Array<String> = [],
        val isOwnerOnly: Boolean = false
        )
