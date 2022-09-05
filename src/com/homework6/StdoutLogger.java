package com.homework6;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StdoutLogger extends FileLoggerConfiguration implements Logger {

    public StdoutLogger(LoggingLevel currentLoggingLevel) {
        super();
        super.currentLoggingLevel = currentLoggingLevel;
    }

    @Override
    public void debug(String debugMessage) {
        if (currentLoggingLevel.equals(LoggingLevel.DEBUG)){
        System.out.println(createConsoleLogFormat(LoggingLevel.DEBUG) + debugMessage + '\n');}
    }

    @Override
    public void info(String infoMessage) {
        if (currentLoggingLevel.equals(LoggingLevel.INFO)
                || currentLoggingLevel.equals(LoggingLevel.DEBUG)){
        System.out.println(createConsoleLogFormat(LoggingLevel.INFO) + infoMessage + '\n');}
    }

    String createConsoleLogFormat(LoggingLevel currentLoggingLevel) {
        DateTimeFormatter fileTimeLogFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String formattedFileTime = fileTimeLogFormat.format(now);
        return logEntriesFormat = String.format("%s %s Message:", formattedFileTime,
                currentLoggingLevel);
    }

}
