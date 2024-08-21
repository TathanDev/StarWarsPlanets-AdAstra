package fr.tathan.swplanets.common.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Comment;
import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;
import com.teamresourceful.resourcefulconfig.web.annotations.Gradient;
import com.teamresourceful.resourcefulconfig.web.annotations.Link;
import com.teamresourceful.resourcefulconfig.web.annotations.WebInfo;

@Config(
        value = "swplanets"
)
@WebInfo(
        title = "Star Wars Planets",
        description = "May the config be with you !",

        icon = "planet",
        gradient = @Gradient(value = "45deg", first = "#7F4DEE", second = "#E7797A"),

        links = {
                @Link(value = "https://discord.gg/Rc7Mxcy2m3", icon = "gamepad-2", title = "Discord"),
                @Link(value = "https://github.com/TathanDev/StarWarsPlanets-AdAstra/", icon = "github", title = "GitHub"),

                @Link(value = "https://curseforge.com/minecraft/mc-mods/star-wars-planets-ad-astra", icon = "curseforge", title = "CurseForge"),
                @Link(value = "https://modrinth.com/mod/star-wars-planets-ad-astra", icon = "modrinth", title = "Modrinth"),
        }
)

public class SWPlanetsConfig {

    @ConfigEntry(
            id = "explosionUpgradeRadius",
            type = EntryType.FLOAT,
            translation = "config.swplanets.explosionUpgradeRadius"
    )
    @Comment("What should be the radius of the explosion for the laser when the blaster have the Explosion Upgrade.")
    public static float explosionUpgradeRadius = 2.0F;

    @ConfigEntry(
            id = "explosionUpgradeFire",
            type = EntryType.BOOLEAN,
            translation = "config.swplanets.explosionUpgradeFire"
    )
    @Comment("Should the explosion upgrade for the blaster set the world on fire?")
    public static boolean explosionUpgradeFire = false;

    @ConfigEntry(
            id = "maxBlasterEnergy",
            type = EntryType.LONG,
            translation = "config.swplanets.maxBlasterEnergy"
    )
    @Comment("What is the max energy a blaster can have ?")
    public static long maxBlasterEnergy = 10_000;

    @ConfigEntry(
            id = "lightSabersAttackModifier",
            type = EntryType.INTEGER,
            translation = "config.swplanets.lightSabersAttackModifier"
    )
    @Comment("What should be the attack modifier for the light sabers?")
    public static int lightSabersAttackModifier = 13;

    @ConfigEntry(
            id = "jawaTrade",
            type = EntryType.BOOLEAN,
            translation = "config.swplanets.jawaTrade"
    )
    @Comment("Should jawa's be able to trade ?")
    public static boolean jawaTrade = true;


    @ConfigEntry(
            id = "jawaMaxTrade",
            type = EntryType.INTEGER,
            translation = "config.swplanets.jawaMaxTrade"
    )
    @Comment("What should be the maximum amount of trades a Jawa can do.")
    public static int jawaMaxTrade = 7;

}
