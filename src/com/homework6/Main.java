package com.homework6;

public class Main {
    public static void main(String[] args) {

      FileLogger logger=new FileLogger(LoggingLevel.DEBUG);
        logger.debug("test debug");
        //logger.info("test info");

FileLoggerConfigurationLoader fileLoggerConfigurationLoader=new FileLoggerConfigurationLoader();
       //FileLoggerConfiguration obj =fileLoggerConfigurationLoader.load();
        //System.out.println(obj);
    }
}
