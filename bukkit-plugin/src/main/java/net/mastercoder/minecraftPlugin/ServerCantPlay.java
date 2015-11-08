package net.mastercoder.minecraftPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class ServerCantPlay extends JavaPlugin {

    @Override
    public void onEnable() {
        new ExampleListener(this);
    }

    class ExampleListener implements Listener {

        private final ServerCantPlay plugin;
        private BukkitTask bukkitTask;

        public ExampleListener(ServerCantPlay plugin) {
            this.plugin = plugin;
            plugin.getServer().getPluginManager().registerEvents(this, plugin);
        }

        @EventHandler
        public void onJoin(PlayerJoinEvent event) {
            tryStart();
        }

        @EventHandler
        public void onJoin(PlayerDeathEvent event) {
            tryStart();
        }

        @EventHandler
        public void onJoin(PlayerInteractEvent event) {
            tryStart();
        }

        private void tryStart() {
            if (bukkitTask == null) {
                bukkitTask = new SemaphoreTask(this.plugin).runTaskTimer(plugin, 400, 200 * 6); //200 = 10 secs
            }
        }

    }
}