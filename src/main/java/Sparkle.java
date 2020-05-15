import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import commands.farm.chop;
import commands.user.create;
import commands.ping;
import commands.user.profile;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Sparkle {

    public static void main(String[] args) throws IOException, LoginException, IllegalArgumentException, RateLimitedException {
        List<String> list = Files.readAllLines(Paths.get("config.txt"));

        // Config access
        String token = list.get(0);
        String ownerId = list.get(1);
        String prefix = list.get(2);
        String version = list.get(3);

        // Init
        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder client = new CommandClientBuilder();

        // Settings of the bot
        client.useDefaultGame();
        client.setActivity(Activity.playing("?help | " + version));
        client.setOwnerId(ownerId);
        client.setPrefix(prefix);

        // Adding commands
        client.addCommands(
                new ping(),
                new profile(),
                new create(),
                new chop()
        );

        new JDABuilder(AccountType.BOT)
                .setToken(token)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Loading..."))
                .addEventListeners(waiter, client.build())
                .build();
    }

}