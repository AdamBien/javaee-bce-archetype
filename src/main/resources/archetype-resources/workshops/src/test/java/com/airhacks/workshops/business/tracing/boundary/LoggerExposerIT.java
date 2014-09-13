package com.airhacks.workshops.business.tracing.boundary;

import java.util.logging.Logger;
import javax.inject.Inject;
import static org.hamcrest.CoreMatchers.is;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author airhacks.com
 */
@RunWith(Arquillian.class)
public class LoggerExposerIT {

    @Inject
    LoggerInjectionSupport cut;

    @Deployment
    public static WebArchive create() {
        return ShrinkWrap.create(WebArchive.class).
                addClasses(LoggerExposer.class, LoggerInjectionSupport.class).
                addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void loggerProperlyConfigured() {
        Logger logger = this.cut.getLogger();
        String expected = this.cut.getClass().getName();
        String actual = logger.getName();
        assertThat(actual, is(expected));
    }

}
