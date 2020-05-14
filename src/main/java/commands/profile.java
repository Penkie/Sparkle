package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import helper.db;
import org.bson.Document;

public class profile extends Command {
    public profile() {
        this.name = "profile";
        this.aliases = new String[]{"p"};
        this.help = "Get your profile";
    }

    @Override
    protected void execute(CommandEvent event) {
        Document user = db.getUser(event.getAuthor().getId());
        if (user == null) {
            event.reply("You didn't create an account. Please create one with the `create` command.");
        } else {
            event.reply("You have " + user.get("money").toString() + " pepets.");
        }
    }
}
