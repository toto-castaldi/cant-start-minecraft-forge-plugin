package it.toto.services.awsJenkins;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by toto on 14/12/14.
 */
@Singleton
@Slf4j
public class Semaphore {

    private boolean status = false;

    @Inject
    public Semaphore(
    ) {
    }

    public void off() {
        this.status = false;
    }

    public void on() {
        this.status = true;
    }

    public boolean status() {
        return status;
    }
}
