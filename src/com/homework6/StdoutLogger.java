package com.homework6;

public class StdoutLogger implements Logger {
    private final LoggingLevel currentLoggingLevel;
    private final String logEntriesFormat;

    public StdoutLogger(FileLoggerConfiguration fileLoggerConfiguration) {
        this.currentLoggingLevel = fileLoggerConfiguration.getCurrentLoggingLevel();
        this.logEntriesFormat = fileLoggerConfiguration.getLogEntriesFormat();
    }

    @Override
    public void debug(String debugMessage) {
        if (currentLoggingLevel.equals(LoggingLevel.DEBUG)) {
            System.out.println(logEntriesFormat + debugMessage + '\n');
        }
    }

    @Override
    public void info(String infoMessage) {
        if (currentLoggingLevel.equals(LoggingLevel.INFO)
                || currentLoggingLevel.equals(LoggingLevel.DEBUG)) {
            System.out.println(logEntriesFormat + infoMessage + '\n');
        }
    }
}
