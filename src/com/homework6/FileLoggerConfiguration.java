package com.homework6;

public class FileLoggerConfiguration  {
    protected static String newFileName;
    protected LoggingLevel currentLoggingLevel;
    protected static long maxFileSize = 100;
    protected String logEntriesFormat;

    public FileLoggerConfiguration(String newFileName, LoggingLevel currentLoggingLevel,
                                   long maxFileSize, String logEntriesFormat) {
        FileLoggerConfiguration.newFileName = newFileName;
        this.currentLoggingLevel = currentLoggingLevel;
        FileLoggerConfiguration.maxFileSize = maxFileSize;
        this.logEntriesFormat = logEntriesFormat;
    }

    public FileLoggerConfiguration() {
    }

    public static long getMaxFileSize() {
        return maxFileSize;
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