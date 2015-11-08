package it.toto.stopPlayMinecraft.bukkitPlugin;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by toto on 07/11/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class MashapeIntegrationTest {

    @Test
    public void testCall() throws UnirestException {
        // These code snippets use an open-source library. http://unirest.io/java
        HttpResponse<JsonNode> response = Unirest.get("https://toto-execute-your-hooks-v1.p.mashape.com/semaphore")
                .header("Authorization", "Basic c3RvcC1wbGF5LW1pbmVjcmFmdDpyMkhuUEhmbQ==")
                .header("X-Mashape-Key", "FCKFcesv9PmshjpunUVhQVx88GI7p1HDYROjsnTmJ45NAYWEnd")
                .header("Accept", "application/json")
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getBody().getObject().getBoolean("status"));

    }
}
