package com.example.examplemod;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by toto on 27/10/15.
 */
public class ExternalSemaphore implements Runnable {

    private static Object sticky = new Object();
    private static ExternalSemaphore instance;
    private boolean live;
    private Thread thread;
    private final String USER_AGENT = "Mozilla/5.0";
    private final long sleepTime = 20000;
    private boolean off;

    private ExternalSemaphore() {
        this.live = true;
        this.off = false;
    }

    public static void start() {
        ExternalSemaphore externalSemaphore = getInstance();
        externalSemaphore.startThread();
    }

    private void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    public static ExternalSemaphore getInstance() {
        if (instance == null) {
            synchronized (sticky) {
                if (instance == null) {
                    instance = new ExternalSemaphore();
                }
            }
        }
        return instance;
    }

    @Override
    public void run() {
        while (live) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Check semaphore");


            try {

                HttpResponse<JsonNode> response = Unirest.get("https://toto-execute-your-hooks-v1.p.mashape.com/semaphore")
                        .header("Authorization", "Basic c3RvcC1wbGF5LW1pbmVjcmFmdDpyMkhuUEhmbQ==")
                        .header("X-Mashape-Key", "FCKFcesv9PmshjpunUVhQVx88GI7p1HDYROjsnTmJ45NAYWEnd")
                        .header("Accept", "application/json")
                        .asJson();

                int responseCode = response.getStatus();

                System.out.println("response status" + response.getStatus());
                System.out.println("response body" + response.getBody().toString());

                if (responseCode == 200) {

                    this.off = response.getBody().getObject().getBoolean("status") == false;
                } else {
                	this.off = false;
                }
            } catch (Exception e) {
            	this.off = false;
                e.printStackTrace();
            }
        }
        this.off = false;
    }

    public boolean isOff() {
        return off;
    }
}
