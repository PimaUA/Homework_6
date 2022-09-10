package com.homework6;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements Logger {

    private String newFileName;
    private final LoggingLevel currentLoggingLevel;
    private final long maxFileSize;
    private final String logEntriesFormat;
    private final File dir = (new File("src/logsFolder"));
    private final boolean isDirectoryCreated = dir.mkdir();
    private File[] files = dir.listFiles();
    private long lastModifiedFileLength;
    private Path path;

    public FileLogger(FileLoggerConfiguration fileLoggerConfiguration) {
        this.newFileName = fileLoggerConfiguration.getNewFileName();
        this.currentLoggingLevel = fileLoggerConfiguration.getCurrentLoggingLevel();
        this.maxFileSize = fileLoggerConfiguration.getMaxFileSize();
        this.logEntriesFormat = fileLoggerConfiguration.getLogEntriesFormat();
    }

    //Main methods
    @Override
    public void debug(String debugMessage) {
        getNewFileName();
        path = Paths.get(newFileName);
        if (currentLoggingLevel.equals(LoggingLevel.DEBUG)) {
            writeLogToFile(path, logEntriesFormat + debugMessage + '\n');
        }
    }

    @Override
    public void info(String infoMessage) {
        getNewFileName();
        path = Paths.get(newFileName);
        if (currentLoggingLevel.equals(LoggingLevel.INFO)
                || currentLoggingLevel.equals(LoggingLevel.DEBUG)) {
            writeLogToFile(path, logEntriesFormat + infoMessage + '\n');
        }
    }

    //Auxiliary methods
    private void writeLogToFile(Path path, String content) {
        try {
            Files.write(path, content.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            if (lastModifiedFileLength >= maxFileSize) {
                String exceptionMessage = String.format("Max:%d Current:%d Path:%s",
                        maxFileSize,
                        lastModifiedFileLength, path.toAbsolutePath());
                throw new FileMaxSizeReachedException(exceptionMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileMaxSizeReachedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void getNewFileName() {
        DateTimeFormatter fileNameTimeFormat =
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();
        String strDate = fileNameTimeFormat.format(now);
        if (isDirectoryCreated && files.length == 0) {
            newFileName = String.format("src/logsFolder/Log_%s.txt", strDate);
            File file = new File(newFileName);
            try {
                file.createNewFile();                 /*creation of new file at directory at first start*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!isDirectoryCreated && files.length > 0) {
            File lastModifiedFile = getLastModifiedFile(files);
            if (getLastModifiedFileSize(lastModifiedFile) < maxFileSize) {
                newFileName = String.format("src/logsFolder/%s",  /* file name when directory*/
                        lastModifiedFile.getName());              /* exists and is not empty*/
            } else if (getLastModifiedFileSize(lastModifiedFile) >= maxFileSize) {
                newFileName = String.format("src/logsFolder/Log_%s.txt",
                        strDate);                             /*file name when file size exceeded*/
            }
        }
    }

    private File getLastModifiedFile(File[] files) {
        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }

    private long getLastModifiedFileSize(File lastModified) {
        return lastModifiedFileLength = lastModified.length();
    }
}






