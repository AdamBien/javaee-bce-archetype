package com.airhacks.workshops.business.tracing.boundary;

import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author airhacks.com
 */
public class LoggerInjectionSupport {

    @Inject
    Logger logger;

    public Logger getLogger() {
        return logger;
    }

}
