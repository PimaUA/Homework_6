package com.homework6;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StdoutLogger implements Logger {
    private final LoggingLevel currentLoggingLevel;
    private final String logEntriesFormat;

    public StdoutLogger(FileLoggerConfiguration fileLoggerConfiguration) {
        DateTimeFormatter fileTimeLogFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String formattedLogTime = fileTimeLogFormat.format(now);
        this.currentLoggingLevel = fileLoggerConfiguration.getCurrentLoggingLevel();
        this.logEntriesFormat = String.format("%s %s Message:", formattedLogTime,
                currentLoggingLevel);
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
