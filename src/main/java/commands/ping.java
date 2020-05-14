package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class ping extends Command {
    public ping() {
        this.name = "ping";
        this.aliases = new String[]{"test"};
        this.help = "Pong !";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage("Pong!");
        event.reply("Pong !");
    }
}
