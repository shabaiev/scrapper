package com.shabaiev.scraper.practicingoop.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Corolla implements Car {

    private static final int MAX_SPEED = 90;
    private static final int STOP_SPEED = 15;
    private static final Logger log = LoggerFactory.getLogger(Corolla.class);

    @Override
    public void run() {
        log.info("i ran at max speed of " + MAX_SPEED + "mp/h");
    }

    @Override
    public void stop() {
        log.info("i stop in " + STOP_SPEED + " sec");
    }

    @Override
    public void turn(Direction direction) {
        switch (direction){
            case LEFT:
                log.info("Turning left");
                break;
            case RIGHT:
                log.info("Turning right");
                break;
        }
    }
}
