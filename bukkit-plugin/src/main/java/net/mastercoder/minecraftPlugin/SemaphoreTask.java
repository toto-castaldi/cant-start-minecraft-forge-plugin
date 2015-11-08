package net.mastercoder.minecraftPlugin;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by goto10 on 27/10/2015.
 */
public class SemaphoreTask extends BukkitRunnable {

    private final ServerCantPlay plugin;

    public SemaphoreTask(ServerCantPlay plugin) {
        this.plugin = plugin;
    }

    public void run() {

        //System.out.println("check semaphore");

        try {
            HttpResponse<JsonNode> response = Unirest.get("https://toto-execute-your-hooks-v1.p.mashape.com/semaphore")
                    .header("Authorization", "Basic c3RvcC1wbGF5LW1pbmVjcmFmdDpyMkhuUEhmbQ==")
                    .header("X-Mashape-Key", "FCKFcesv9PmshjpunUVhQVx88GI7p1HDYROjsnTmJ45NAYWEnd")
                    .header("Accept", "application/json")
                    .asJson();

            int responseCode = response.getStatus();

            //System.out.println("response status" + response.getStatus());
            //System.out.println("response body" + response.getBody().toString());

            if (responseCode == 200) {

                if (response.getBody().getObject().getBoolean("status") == false) {
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