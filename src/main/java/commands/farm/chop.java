package commands.farm;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import helper.db;
import org.bson.Document;

import java.util.Random;

public class chop extends Command {
    public chop() {
        this.name = "chop";
        this.help = "Cut some wood";
    }

    @Override
    protected void execute(CommandEvent event) {
        boolean check = helper.db.checkUser(event.getAuthor().getId());
        if (!check) {
            event.reply("You need to create an account.");
            return;
        }

        Document farm = db.getFarm(event.getAuthor().getId());
        int result = helper.randomizer.random(1, 5);
        int toChange = result + (Integer) farm.get("wood");

        helper.db.updateUser(event.getAuthor().getId(), "farm", "wood", toChange);

        event.reply(":evergreen_tree: You chopped a tree. **+" + result + " wood**");
    }
}
