package com.homework6;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileLoggerConfigurationLoader extends FileLoggerConfiguration implements Loader {

    @Override
    public FileLoggerConfiguration load() {
        try (FileReader fr = new FileReader(("src/load_config.txt"));
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
        return new FileLoggerConfiguration(newFileName, currentLoggingLevel, maxFileSize, logEntriesFormat);
    }
}
