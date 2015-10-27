package net.mastercoder.minecraftPlugin;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by goto10 on 27/10/2015.
 */
public class SemaphoreTask extends BukkitRunnable {

    private final ServerCantPlay plugin;

    public SemaphoreTask(ServerCantPlay plugin) {
        this.plugin = plugin;
    }

    public void run() {
        try {
            String url = "http://default-environment-7drtpyd2a7.elasticbeanstalk.com/semaphore";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                if (response.toString().contains("false")) {
                    Player[] onlinePlayers = plugin.getServer().getOnlinePlayers();
                    for (int i = 0; i < onlinePlayers.length; i++) {
                        onlinePlayers[i].kickPlayer("Can't play now");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}