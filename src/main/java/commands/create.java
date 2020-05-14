package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import helper.db;

public class create extends Command {
    public create() {
        this.name = "create";
        this.help = "Create your account";
    }

    @Override
    protected void execute(CommandEvent event) {
        boolean check = helper.db.checkUser(event.getAuthor().getId());
        if (check) {
            event.reply("You already have an account.");
        } else {
            event.reply("Creating an account for you...");
            helper.db.createUser(event.getAuthor().getId());
            event.reply("Account created!");
        }
    }
}
