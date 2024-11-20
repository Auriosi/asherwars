package org.auriosi.asherWars;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;

public class GameManager {
    public enum GameState {
        LOBBY,
        STARTING,
        INGAME,
        END
    }

    private AsherWars plugin;

    private GameState gameState;
    private final Scoreboard scoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();
    private final Team nextUpdateTeam = scoreboard.registerNewTeam("nextUpdate");
    private final Team nextUpdateTimeTeam = scoreboard.registerNewTeam("nextUpdateTime");
    private final ArrayList<Base> bases = new ArrayList<>();

    private final ArrayList<String> teamNames = new ArrayList<>();
    private final ArrayList<TextColor> teamColors = new ArrayList<>();
    private final ArrayList<ChatColor> chatColors = new ArrayList<>();
    private final ArrayList<Location> shopNPCLocations = new ArrayList<>();
    private final ArrayList<Location> upgradeNPCLocations = new ArrayList<>();

    public final String shopNPCSkinValue = "ewogICJ0aW1lc3RhbXAiIDogMTcyNjAxNTIwMjk5MSwKICAicHJvZmlsZUlkIiA6ICIyMmNiM2U0ZDdmOTY0ZmNjYjE1MDIyNWIwN2YzMTEyMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0dWxleW1hbkIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmU5MzdmMjg0OTdjNzljMDA2YTU5NTA3NGFmYjM1NTA4NDQ2YTg5ZmVhNDU0NGZjNDI3NDFhOTFmYTE3MzIwOCIKICAgIH0KICB9Cn0=";
    public final String shopNPCSkinSignature = "rYI7bK3XBR8AGXE/vPAMnglAiYgUKm5XjB90GEmDO5G9r29w912iyDDujcIs3xXZOxy8g73AQOfTmgFEqxUd4C1bySzOzCZfLV4twV86WLdeghLQX7ZD061JI2H/BnRLCPO+Nytkhz5fawSauaJFFnbqyujP0BoDbu+2nXzfUsGDXouWDACK8kHoUx+nl7i7MaHzxHK5qdYmbMbXuxQQdQyJJddqDi28WH7a3V94Rc74zezfDGrbVEQZuO3HHjd1gu7LMgyTlVog9i9fjuTm+Yi5swVYqXa5Jke+KI+gRFGBxvnaEs4/LbyzkylrLICCgh2ruUAvH2iGz2mnB6xnnpRNrehTsHHpJAIher/sn0DoBS1N0pd8LkH0ypHth2ZMD2wHl4mhxQZWiqqTtcWFrmnCH9rkF4sXIaPqD24fOB2xy5T0dVeMiAV9mRq2K+UpQoSEi5cxsjW0yQjmebgcdOFxWhWax69pHQIyw30jrPeyPqM/4UhpYYwUju6VC1S8eQ3lSFlo5bAcWo5aL65yJWcb8GPMHWkBvp2nCaofRhy349S8vshIf61uYBbQOTVW2viFUT/oigcb4XIcQ0l/rNCb5aA0l2KzEhkpF8vI5f/Bg3JchSnccInuR9pujHSyvn6vypDDDWE4WYifqIP1F4GTPs3hE/rCPiYaMU2Vl60=";
    public final String upgradeSkinValue = "ewogICJ0aW1lc3RhbXAiIDogMTU4ODk1NjkyNDM2NiwKICAicHJvZmlsZUlkIiA6ICJmMjc0YzRkNjI1MDQ0ZTQxOGVmYmYwNmM3NWIyMDIxMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJIeXBpZ3NlbCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83NzA1NjU1NjYwMmIzMTA4NTIxMGI2MjM0Y2Y3Mzc4NDg5YjJlOTVlZjM2MDMxOWEyOGJhZWMzYzE3Y2Q1ZjhkIgogICAgfQogIH0KfQ==";
    public final String upgradeSkinSignature = "ij7f4rfsYvEdqWsUwG/C/Qmra10eJGxs7oIAQGzf1E0om8GaXxIDz8EPvs/20yZdDuix0ptiLmz+gsqNDuL3rxBr4a5rVClNNzABROU/iiNJf0uPAJ+604VxHu2E8/Xedklo/BZWjhFGiqa2jxg4XmVvbPr9Sy0IiFmOKQsTXOuhNy551EUQjnrJ+4/Gsr32mioX7PnuzhyLmrjQivZagN3u/IQh0T6eb2Wi/kJY9O176KxzjEP4TrtTyQFDaJhqozED23UaWmMtpTx+qQ8YBZ02O4UjI9LDg0u5IyDh1LcCPwMyoUBFjOPPuDeZ8MFrtQYdabaCFn06c3EwxMTF6zXKJe08GJZ77ldUwuMhnNV1k08eg2P9UwidY1gEX702I2JcL6lMiD8kB+HMllib9xzb73Y1C1kgDzu3f1Y7oA5mU/EnZn+yGBwrSBhFD+/KYtOlpQzjGiimwIVcOYmGWz+2J2aMHBibJl6X0/ULDUXOr4b0Uc1RL1efr0vF3TS2F71D+fNGsFApTMwQIqN+wpCMHEK/3xl1bX1+ov7xvWW6y97dZ0eceFVQwDn/OXmr1jpy6m1htdi08eAk3i4VmfC783U4ERwEBUzopMHj3ofem02wNxp6o9NLyqsForoVz1nibB4YOIYeL5yGJSKlCpb5Tfv/07jjN0s2u2xx6Zk=";

    public GameManager(@NotNull AsherWars plugin) {
        this.gameState = GameState.LOBBY;
        this.plugin = plugin;
        teamNames.add("Asher's Angels");
        teamNames.add("Asher Devout");
        teamNames.add("Asher Ashers");
        teamNames.add("Dolphins");
        teamColors.add(TextColor.color(0x00ff00));
        teamColors.add(TextColor.color(0xff0000));
        teamColors.add(TextColor.color(0x0000ff));
        teamColors.add(TextColor.color(0xffff00));
        chatColors.add(ChatColor.GREEN);
        chatColors.add(ChatColor.RED);
        chatColors.add(ChatColor.BLUE);
        chatColors.add(ChatColor.YELLOW);
        shopNPCLocations.add(new Location(plugin.getServer().getWorld("world"), 0, 0, 0));
        shopNPCLocations.add(new Location(plugin.getServer().getWorld("world"), 0, 0, 0));
        shopNPCLocations.add(new Location(plugin.getServer().getWorld("world"), 0, 0, 0));
        shopNPCLocations.add(new Location(plugin.getServer().getWorld("world"), 0, 0, 0));
        upgradeNPCLocations.add(new Location(plugin.getServer().getWorld("world"), 0, 0, 0));
        upgradeNPCLocations.add(new Location(plugin.getServer().getWorld("world"), 0, 0, 0));
        upgradeNPCLocations.add(new Location(plugin.getServer().getWorld("world"), 0, 0, 0));
        upgradeNPCLocations.add(new Location(plugin.getServer().getWorld("world"), 0, 0, 0));
        Objective objective = scoreboard.registerNewObjective("asherWarsTitle", Criteria.DUMMY, Component.text("Asher Wars").color(TextColor.color(0x00ff00)).decorate(TextDecoration.BOLD));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        nextUpdateTeam.addEntry(ChatColor.WHITE.toString());
        nextUpdateTimeTeam.addEntry(ChatColor.BLACK.toString());
        nextUpdateTeam.prefix(Component.text("Game Starting in:").color(TextColor.fromHexString("#00FF00")));
        nextUpdateTimeTeam.prefix(Component.text("Soon!").color(TextColor.fromHexString("#00FF00")));
        objective.getScore(ChatColor.GRAY + LocalDate.now().toString()).setScore(15);
        objective.getScore(ChatColor.BLACK + " ").setScore(14);
        objective.getScore(ChatColor.WHITE.toString()).setScore(13);
        objective.getScore(ChatColor.BLACK.toString()).setScore(12);
        objective.getScore(ChatColor.DARK_BLUE + " ").setScore(11);
        objective.getScore(ChatColor.DARK_GREEN + " ").setScore(1);
        objective.getScore(ChatColor.GOLD + "Developed by Auriosi").setScore(0);
        for (int i = 0; i < 4; i++) {
            String teamName = teamNames.get(i);
            Team team = scoreboard.registerNewTeam(teamName);
            team.displayName(Component.text(teamName));
            team.color(NamedTextColor.nearestTo(teamColors.get(i)));
            team.prefix(Component.text("").color(teamColors.get(i)));
            team.setAllowFriendlyFire(false);
            Team scoreboardTeam = scoreboard.registerNewTeam("scoreboard" + teamName);
            scoreboardTeam.addEntry(chatColors.get(i).toString());
            scoreboardTeam.prefix(Component.text(teamName + " ").color(teamColors.get(i)).decorate(TextDecoration.BOLD));
            scoreboardTeam.suffix(Component.text("✔").color(NamedTextColor.GREEN));
            objective.getScore(chatColors.get(i).toString()).setScore((int) (2.0 + (double) i + Math.floor((8.0 - (double) i) / 2.0)));
            Location heartLocation;
            switch (i) {
                case 0: // Asher's Angels
                    heartLocation = new Location(plugin.getServer().getWorld("world"),
                            getPlugin().getConfig().getInt("AshersAngels.heartBlockLocation.x"),
                            getPlugin().getConfig().getInt("AshersAngels.heartBlockLocation.y"),
                            getPlugin().getConfig().getInt("AshersAngels.heartBlockLocation.z")
                    );
                    break;
                case 1: // Asher Devout
                    heartLocation = new Location(plugin.getServer().getWorld("world"),
                            getPlugin().getConfig().getInt("AsherDevout.heartBlockLocation.x"),
                            getPlugin().getConfig().getInt("AsherDevout.heartBlockLocation.y"),
                            getPlugin().getConfig().getInt("AsherDevout.heartBlockLocation.z")
                    );
                    break;
                case 2: // Asher Ashers
                    heartLocation = new Location(plugin.getServer().getWorld("world"),
                            getPlugin().getConfig().getInt("AsherAshers.heartBlockLocation.x"),
                            getPlugin().getConfig().getInt("AsherAshers.heartBlockLocation.y"),
                            getPlugin().getConfig().getInt("AsherAshers.heartBlockLocation.z")
                    );
                    break;
                case 3: // Dolphins
                    heartLocation = new Location(plugin.getServer().getWorld("world"),
                            getPlugin().getConfig().getInt("Dolphins.heartBlockLocation.x"),
                            getPlugin().getConfig().getInt("Dolphins.heartBlockLocation.y"),
                            getPlugin().getConfig().getInt("Dolphins.heartBlockLocation.z")
                    );
                    break;
                default:
                    heartLocation = new Location(plugin.getServer().getWorld("world"), 0, 0, 0);
                    break;
            }
            Base base = new Base(this, team, scoreboardTeam, i, shopNPCLocations.get(i), upgradeNPCLocations.get(i), getPlugin().getServer().getWorld("world").getBlockData(heartLocation));
            plugin.getLogger().info("Registered team " + teamName);
        }
    }

    public void startGame() {
        gameState = GameState.STARTING;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public AsherWars getPlugin() {
        return plugin;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public ArrayList<Base> getBases() {
        return bases;
    }

    public void addPlayerToBase(@NotNull Player player, @NotNull String teamName) {
        for (Base base : bases) {
            if (base.getTeam().getName().equals(teamName)) {
                base.addPlayer(player);
                return;
            }
        }
    }

    public void removePlayerFromBase(@NotNull Player player) {
        for (Base base : bases) {
            if (base.getPlayers().contains(player)) {
                base.removePlayer(player);
                return;
            }
        }
    }

    public void sendNPCPackets(@NotNull Player player) {
        for (Base base : bases) {
            base.sendNPCPackets(player);
        }
    }
}