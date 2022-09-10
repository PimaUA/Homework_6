package com.homework6;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileLoggerConfigurationLoader implements Loader {
    private String newFileName;
    private LoggingLevel currentLoggingLevel;
    private long maxFileSize;
    private String logEntriesFormat;

    @Override
    public FileLoggerConfiguration load(String configFileName) {
        try (FileReader fr = new FileReader((configFileName));
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("FILE")) {
                    newFileName = line.split(":")[1];
                } else if (line.contains("LEVEL")) {
                    String level = line.split(":")[1];
                    currentLoggingLevel = LoggingLevel.valueOf(level);
                } else if (line.contains("MAX-SIZE")) {
                    String temp = line.split(":")[1];
                    maxFileSize = Long.parseLong(temp);
                } else if (line.contains("FORMAT")) {
                    logEntriesFormat = line.split(":")[1];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FileLoggerConfiguration(newFileName, currentLoggingLevel,
                maxFileSize, logEntriesFormat);
    }
}
