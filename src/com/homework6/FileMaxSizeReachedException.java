package com.homework6;

public class FileMaxSizeReachedException extends RuntimeException {
    private static final String field = String.format("Max:%d Current:%d Path:%s",
            FileLoggerConfiguration.getMaxFileSize(),
            FileLogger.getLastModifiedFileLength(), FileLogger.getPath().toAbsolutePath());

    public FileMaxSizeReachedException() {
        super(field);
    }

    public FileMaxSizeReachedException(String exceptionMessage) {
        super(field);
    }
}