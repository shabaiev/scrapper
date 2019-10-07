package com.shabaiev.scraper.practicingoop.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Camry implements Car {

    private static final Logger log = LoggerFactory.getLogger(Camry.class);
    private static final int MAX_SPEED = 120;
    private static final int STOP_SPEED = 10;

    @Override
    public void run() {
        log.info("i ran at max speed of " + MAX_SPEED + " mp/h");
    }

    @Override
    public void stop() {
        log.info("i stop in " + STOP_SPEED + " sec");
    }

    @Override
    public void turn(Direction direction) {
        switch (direction){
            case LEFT:
                log.info("turning left");
                break;
            case RIGHT:
                log.info("turning right");
                break;
        }
    }
}
