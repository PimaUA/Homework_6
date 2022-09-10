package com.homework6;

public class FileMaxSizeReachedException extends RuntimeException {

    public FileMaxSizeReachedException(String message) {
        super(message);
    }
}