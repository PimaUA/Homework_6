package com.homework6;

public class Main {
    
    public static void main(String[] args) {
        FileLoggerConfigurationLoader fileLoggerConfigurationLoader =
                new FileLoggerConfigurationLoader();
        FileLoggerConfiguration config = fileLoggerConfigurationLoader.load("src/load_config.txt");
        FileLogger logger = new FileLogger(config);

        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.debug("test debug");
        logger.info("test info");

        StdoutLoggerConfigurationLoader stdoutLoggerConfigurationLoader = new StdoutLoggerConfigurationLoader();
        FileLoggerConfiguration config2=stdoutLoggerConfigurationLoader.load("src/load_config.txt");
        StdoutLogger loggerOut = new StdoutLogger(config2);
        loggerOut.debug("test debug");
        //loggerOut.info("test info");
    }
}
