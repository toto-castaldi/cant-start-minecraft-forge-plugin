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
public class KVStoreIntegrationTest {

    @Test
    public void testCall() throws UnirestException {
        // These code snippets use an open-source library. http://unirest.io/java
        HttpResponse<JsonNode> response = Unirest.get("https://toto-execute-your-hooks-v1.p.mashape.com/semaphore")
                .header("X-Mashape-Key", "ifUwrVw7U2mshHF39bWdnfD9k6L6p1dRkjyjsnH1Gx3z8AhpyN")
                .header("Accept", "application/json")
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
    }
}
