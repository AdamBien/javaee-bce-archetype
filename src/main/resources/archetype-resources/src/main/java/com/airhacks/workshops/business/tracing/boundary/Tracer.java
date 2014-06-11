package com.airhacks.workshops.business.tracing.boundary;

import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author airhacks.com
 */
public class Tracer {

    private final Logger logger;

    @Inject
    public Tracer(Logger logger) {
        this.logger = logger;
    }

    public void trace(String msg) {
        logger.info(msg);
    }
}
