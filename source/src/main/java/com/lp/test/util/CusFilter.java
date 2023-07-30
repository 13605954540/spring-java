/*
package com.lp.test.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Marker;

public class CusFilter extends TurboFilter {
    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object[] objects, Throwable throwable) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String newFormat = null;
        if(stackTraceElements != null && stackTraceElements.length > 2 && logger.getLevel() == Level.DEBUG) {
            StackTraceElement stackTraceElement = stackTraceElements[2];
            String className = stackTraceElement.getClassName();
            int lineNum = stackTraceElement.getLineNumber();
            newFormat = String.format("%s(%s:%d)", s, className, lineNum);
            ILoggingEvent event = new LoggingEvent(
                    CusFilter.class.getName(),
                    logger.getLoggerContext().getLogger("ROOT"),
                    level,
                    newFormat,
                    null,
                    null
            );
            logger.debug(newFormat, objects);
            return FilterReply.DENY;
        }
        return FilterReply.NEUTRAL;
    }
}
*/
