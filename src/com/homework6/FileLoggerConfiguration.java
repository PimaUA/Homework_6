package com.homework6;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLoggerConfiguration {
    private final String newFileName;
    private final LoggingLevel currentLoggingLevel;
    private final long maxFileSize;
    private final String logEntriesFormat;

    public FileLoggerConfiguration(String newFileName, LoggingLevel currentLoggingLevel,
                                   long maxFileSize) {
        DateTimeFormatter fileTimeLogFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String formattedLogTime = fileTimeLogFormat.format(now);
        this.newFileName = newFileName;
        this.currentLoggingLevel = currentLoggingLevel;
        this.maxFileSize = maxFileSize;
        this.logEntriesFormat = String.format("%s %s Message:", formattedLogTime,
                currentLoggingLevel);
    }

    public FileLoggerConfiguration(String newFileName, LoggingLevel currentLoggingLevel,
                                   long maxFileSize, String logEntriesFormat) {
        this.newFileName = newFileName;
        this.currentLoggingLevel = currentLoggingLevel;
        this.maxFileSize = maxFileSize;
        this.logEntriesFormat = logEntriesFormat;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public LoggingLevel getCurrentLoggingLevel() {
        return currentLoggingLevel;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public String getLogEntriesFormat() {
        return logEntriesFormat;
    }

    @Override
    public String toString() {
        return "FileLoggerConfiguration{" +
                " newFileName=" + newFileName +
                ", currentLoggingLevel=" + currentLoggingLevel +
                ", maxFileSize=" + maxFileSize +
                ", logEntriesFormat=" + logEntriesFormat +
                '}';
    }
}