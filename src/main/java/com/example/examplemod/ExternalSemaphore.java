package com.example.examplemod;

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
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("check semaphore");

            String url = "http://www.google.com/search?q=toto";

            URL obj = null;
            try {
                obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());

                off = response.toString().contains("OFF");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean isOff() {
        return off;
    }
}
