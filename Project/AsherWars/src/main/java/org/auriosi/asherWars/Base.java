package org.auriosi.asherWars;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Base {
    private final GameManager gameManager;

    private final PacketContainer shopNPCJoinPacket = new PacketContainer(PacketType.Play.Server.PLAYER_INFO);
    private final PacketContainer shopNPCAddPacket = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
    private final PacketContainer upgradeNPCJoinPacket = new PacketContainer(PacketType.Play.Server.PLAYER_INFO);
    private final PacketContainer upgradeNPCAddPacket = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);

    private final Team team;
    private final Team scoreboardTeam;
    private final ArrayList<Player> players = new ArrayList<>();
    private final BlockData heartBlockData;

    public Base(
            @NotNull GameManager gameManager,
            @NotNull Team team,
            @NotNull Team scoreboardTeam,
            int teamIndex,
            @NotNull Location shopNPCLocation,
            @NotNull Location upgradeNPCLocation,
            @NotNull BlockData heartBlockData
    ) {
        this.gameManager = gameManager;
        this.team = team;
        this.scoreboardTeam = scoreboardTeam;
        this.heartBlockData = heartBlockData;
        UUID shopNPCUUID = UUID.randomUUID();
        UUID upgradeNPCUUID = UUID.randomUUID();
        int shopNPCEntityId = 14504 + (teamIndex*10);
        int upgradeNPCEntityId = 14505 + (teamIndex*10);
        WrappedGameProfile shopNPCGameProfile = new WrappedGameProfile(shopNPCUUID, "Shop");
        WrappedGameProfile upgradeNPCGameProfile = new WrappedGameProfile(upgradeNPCUUID, "Upgrades");
        shopNPCGameProfile.getProperties().put("textures", new WrappedSignedProperty("textures", gameManager.shopNPCSkinValue, gameManager.shopNPCSkinSignature));
        upgradeNPCGameProfile.getProperties().put("textures", new WrappedSignedProperty("textures", gameManager.upgradeSkinValue, gameManager.upgradeSkinSignature));
        PlayerInfoData shopNPCPlayerInfoData = new PlayerInfoData(
                shopNPCGameProfile,
                0,
                EnumWrappers.NativeGameMode.CREATIVE,
                null
        );
        PlayerInfoData upgradePlayerInfoData = new PlayerInfoData(
                upgradeNPCGameProfile,
                0,
                EnumWrappers.NativeGameMode.CREATIVE,
                null
        );
        // You must set fieldIndex to 1 to account for a bug in ProtocolLib
        shopNPCJoinPacket.getPlayerInfoDataLists().write(1, Collections.singletonList(shopNPCPlayerInfoData));
        upgradeNPCJoinPacket.getPlayerInfoDataLists().write(1, Collections.singletonList(upgradePlayerInfoData));

        shopNPCAddPacket.getEntityTypeModifier().write(0, EntityType.PLAYER);
        upgradeNPCAddPacket.getEntityTypeModifier().write(0, EntityType.PLAYER);
        shopNPCAddPacket.getIntegers().write(0, shopNPCEntityId);
        upgradeNPCAddPacket.getIntegers().write(0, upgradeNPCEntityId);
        shopNPCAddPacket.getUUIDs().write(0, shopNPCUUID);
        upgradeNPCAddPacket.getUUIDs().write(0, upgradeNPCUUID);
        shopNPCAddPacket.getDoubles().write(0, shopNPCLocation.getX());
        shopNPCAddPacket.getDoubles().write(1, shopNPCLocation.getY());
        shopNPCAddPacket.getDoubles().write(2, shopNPCLocation.getZ());
        upgradeNPCAddPacket.getDoubles().write(0, upgradeNPCLocation.getX());
        upgradeNPCAddPacket.getDoubles().write(1, upgradeNPCLocation.getY());
        upgradeNPCAddPacket.getDoubles().write(2, upgradeNPCLocation.getZ());
        shopNPCAddPacket.getBytes().write(0, (byte) ((shopNPCLocation.getYaw() * 256.0F) / 360.0F));
        shopNPCAddPacket.getBytes().write(1, (byte) ((shopNPCLocation.getPitch() * 256.0F) / 360.0F));
        upgradeNPCAddPacket.getBytes().write(0, (byte) ((upgradeNPCLocation.getYaw() * 256.0F) / 360.0F));
        upgradeNPCAddPacket.getBytes().write(1, (byte) ((upgradeNPCLocation.getPitch() * 256.0F) / 360.0F));
    }

    public Team getTeam() {
        return team;
    }

    public Team getScoreboardTeam() {
        return scoreboardTeam;
    }

    public BlockData getHeartBlockData() {
        return heartBlockData;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(@NotNull Player player) {
        players.add(player);
        team.addEntry(player.getName());
    }

    public void removePlayer(@NotNull Player player) {
        players.remove(player);
        team.removeEntry(player.getName());
    }

    public void clearPlayers() {
        for (Player player : players) {
            team.removeEntry(player.getName());
        }
        players.clear();
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void sendNPCPackets(@NotNull Player player) {
        gameManager.getPlugin().getProtocolManager().sendServerPacket(player, shopNPCJoinPacket);
        gameManager.getPlugin().getProtocolManager().sendServerPacket(player, upgradeNPCJoinPacket);
        gameManager.getPlugin().getProtocolManager().sendServerPacket(player, shopNPCAddPacket);
        gameManager.getPlugin().getProtocolManager().sendServerPacket(player, upgradeNPCAddPacket);
    }
}