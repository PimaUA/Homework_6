package com.homework6;

public class Main {
    public static void main(String[] args) {
        FileLogger logger = new FileLogger(new FileLoggerConfiguration(
                "src/logsFolder/Log_09.09.2022 18.42.04",
                LoggingLevel.DEBUG, 100));
        logger.debug("test debug");
        //logger.info("test info");

        FileLoggerConfigurationLoader fileLoggerConfigurationLoader =
                new FileLoggerConfigurationLoader();
        FileLoggerConfiguration obj2 =
                fileLoggerConfigurationLoader.load("src/load_config.txt");
        //System.out.println(obj2);
    }
}
