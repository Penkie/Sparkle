package commands.user;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import helper.db;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bson.Document;

import java.awt.*;

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
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle(event.getAuthor().getName() + "'s profile")
                    .setDescription(
                            "**Sparks**: " + user.get("sparks")
                            + "\n\n**Wood level**: " + user.get("woodlvl")
                            + "\n**Stone level**: " + user.get("stonelvl")
                            + "\n**Fish level**: " + user.get("fishlvl")
                    )
                    .setColor(new Color(0xF060CA));
            event.getChannel().sendMessage(embed.build()).queue();
        }
    }
}
