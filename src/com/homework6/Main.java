package com.homework6;

public class Main {
    
    public static void main(String[] args) {
        FileLogger logger = new FileLogger(new FileLoggerConfiguration(
                LoggingLevel.DEBUG, 100));
        logger.debug("test debug");
        //logger.info("test info");

        FileLoggerConfigurationLoader fileLoggerConfigurationLoader =
                new FileLoggerConfigurationLoader();
        FileLoggerConfiguration obj2 =
                fileLoggerConfigurationLoader.load("src/load_config.txt");
        //System.out.println(obj2);

        StdoutLogger loggerOut = new StdoutLogger(new FileLoggerConfiguration(LoggingLevel.DEBUG));
        loggerOut.debug("test debug");
        //loggerOut.info("test info");

        StdoutLoggerConfigurationLoader stdoutLoggerConfigurationLoader = new StdoutLoggerConfigurationLoader();
        stdoutLoggerConfigurationLoader.load("src/load_config.txt");
    }
}
