package me.nallaka.inixbot.main

import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDABuilder

class BotMain {
    fun main(args : Array<String>) {
        /*
        TODO: Read Bot Token from file
        TODO: Initialize JDA object
        TODO: Initialize MessageHandler
        TODO: Initialize CommandHandler
        TODO: Initialize User permissions
         */

        val botToken = ""
        val jda = JDABuilder(AccountType.BOT).setToken(botToken)
        jda.addEventListener()

    }
}