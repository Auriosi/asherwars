package org.auriosi.asherWars;

import com.destroystokyo.paper.event.player.PlayerSetSpawnEvent;
import io.papermc.paper.event.player.PlayerOpenSignEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.BrewingStandFuelEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.event.world.StructureGrowEvent;

public class GameplayDisableListeners implements Listener {
    private final AsherWars plugin;

    public GameplayDisableListeners(AsherWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void BatToggleSleepEvent(BatToggleSleepEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void BlockCookEvent(BlockCookEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void BlockFadeEvent(BlockFadeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void BlockFormEvent(BlockFormEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void BlockShearEntityEvent(BlockShearEntityEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void BrewEvent(BrewEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void BrewingStandFuelEvent(BrewingStandFuelEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void CraftItemEvent(CraftItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void EnchantItemEvent(EnchantItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void EntityBreedEvent(EntityBreedEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void EntityEnterLoveModeEvent(EntityEnterLoveModeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void EntityMountEvent(EntityMountEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void EntityPortalEvent(EntityPortalEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void EntityTameEvent(EntityTameEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void EntityTransformEvent(EntityTransformEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void FurnaceBurnEvent(FurnaceBurnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void LeavesDecayEvent(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void MoistureChangeEvent(MoistureChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void PlayerArmorStandManipulateEvent(PlayerArmorStandManipulateEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void PlayerBedEnterEvent(PlayerBedEnterEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void PlayerBucketEntityEvent(PlayerBucketEntityEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void PlayerEditBookEvent(PlayerEditBookEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void PlayerFishEvent(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.BITE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerLeashEntityEvent(PlayerLeashEntityEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void PlayerPortalEvent(PlayerPortalEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void PlayerShearEntityEvent(PlayerShearEntityEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void PlayerOpenSignEvent(PlayerOpenSignEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void PlayerSetSpawnEvent(PlayerSetSpawnEvent event) {
        if (event.getCause() == PlayerSetSpawnEvent.Cause.BED) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void RaidTriggerEvent(RaidTriggerEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void SheepDyeWoolEvent(SheepDyeWoolEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void SignChangeEvent(SignChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void StructureGrowEvent(StructureGrowEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void VillagerAcquireTradeEvent(VillagerAcquireTradeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void VillagerCareerChangeEvent(VillagerCareerChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void VillagerReplenishTradeEvent(VillagerReplenishTradeEvent event) {
        event.setCancelled(true);
    }
}
