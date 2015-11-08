package com.example.examplemod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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


            String url = "https://toto-execute-your-hooks-v1.p.mashape.com/semaphore";

            URL obj = null;

            try {

                obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Authorization", "Basic c3RvcC1wbGF5LW1pbmVjcmFmdDpyMkhuUEhmbQ==");
                con.setRequestProperty("X-Mashape-Key", "FCKFcesv9PmshjpunUVhQVx88GI7p1HDYROjsnTmJ45NAYWEnd");
                con.setRequestProperty("Accept", "application/json");

                int responseCode = con.getResponseCode();

                if (responseCode == 200) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    this.off = response.toString().contains("false");
                } else {
                	this.off = false;
                }
            } catch (Throwable e) {
                System.out.println("error on semaphore");
                e.printStackTrace();
            }
        }
        this.off = false;
    }

    public boolean isOff() {
        return off;
    }
}
