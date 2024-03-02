package fr.tathan.swplanets.mixin;

import net.minecraft.CrashReport;
import net.minecraft.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CrashReport.class)
public class CrashReportMixin {

    /**
     * @author TATHAN
     * @reason This add infos about Star Wars movies because why not
     */
    @Overwrite
    private static String getErrorComment() {
        String[] astring = new String[]{"Who set us up the TNT?", "Everything's going to plan. No, really, that was supposed to happen.", "Uh... Did I do that?", "Oops.", "Why did you do that?", "I feel sad now :(", "My bad.", "I'm sorry, Dave.", "I let you down. Sorry :(", "On the bright side, I bought you a teddy bear!", "Daisy, daisy...", "Oh - I know what I did wrong!", "Hey, that tickles! Hehehe!", "I blame Dinnerbone.", "You should try our sister game, Minceraft!", "Don't be sad. I'll do better next time, I promise!", "Don't be sad, have a hug! <3", "I just don't know what went wrong :(", "Shall we play a game?", "Quite honestly, I wouldn't worry myself about that.", "I bet Cylons wouldn't have this problem.", "Sorry :(", "Surprise! Haha. Well, this is awkward.", "Would you like a cupcake?", "Hi. I'm Minecraft, and I'm a crashaholic.", "Ooh. Shiny.", "This doesn't make any sense!", "Why is it breaking :(", "Don't do that.", "Ouch. That hurt :(", "You're mean.", "This is a token for 1 free hug. Redeem at your nearest Mojangsta: [~~HUG~~]", "There are four lights!", "But it works on my machine."};
        String[] nerdInfos = new String[]{"George Lucas had envisioned Luke Skywalker as a girl!", "In the first film, the actor playing C3PO and R2D2 hated each other.", "During the first Star Wars Movie, Harrison Ford (Han Solo) and Carrie Fisher (Princess Leia) had a secret affair"};

        try {
            return (astring[(int)(Util.getNanos() % (long)astring.length)] + "\n// Did you know that " + nerdInfos[(int)(Util.getNanos() % (long)nerdInfos.length)]);
        } catch (Throwable var2) {
            return "Witty comment unavailable :(";
        }
    }

}