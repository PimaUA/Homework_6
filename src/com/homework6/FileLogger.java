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

public class FileLogger extends FileLoggerConfiguration implements Logger {

    private static final File dir = (new File("src/logsFolder"));
    private static final boolean isDirectoryCreated = dir.mkdir();
    private static File[] files = dir.listFiles();
    private static long lastModifiedFileLength;
    private static Path path;

    public FileLogger(LoggingLevel currentLoggingLevel) {
        super();
        super.currentLoggingLevel = currentLoggingLevel;
    }

    //Main methods
    @Override
    public void debug(String debugMessage) {
        getNewFileName();
        path = Paths.get(newFileName);
        if (currentLoggingLevel.equals(LoggingLevel.DEBUG)){
        writeLogToFile(path, createFileLogFormat(LoggingLevel.DEBUG) + debugMessage + '\n');}
    }

    @Override
    public void info(String infoMessage) {
        getNewFileName();
        path = Paths.get(newFileName);
        if (currentLoggingLevel.equals(LoggingLevel.INFO)
                || currentLoggingLevel.equals(LoggingLevel.DEBUG)){
        writeLogToFile(path, createFileLogFormat(LoggingLevel.INFO) + infoMessage + '\n');}
    }

    //Auxiliary methods
    private static void writeLogToFile(Path path, String content) {
        try {
            Files.write(path, content.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);//}

            if (lastModifiedFileLength >= getMaxFileSize()) {
                throw new FileMaxSizeReachedException("exception");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileMaxSizeReachedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void getNewFileName() {
        DateTimeFormatter fileNameTimeFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();
        String strDate = fileNameTimeFormat.format(now);
        if (isDirectoryCreated && files.length == 0) {
            newFileName = String.format("src/logsFolder/Log_%s.txt", strDate);
            File file = new File(newFileName);
            try {
                file.createNewFile();                            /*creation of new file at directory at first start*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!isDirectoryCreated && files.length > 0) {
            File lastModifiedFile = getLastModifiedFile(files);
            if (getLastModifiedFileSize(lastModifiedFile) < getMaxFileSize()) {
                newFileName = String.format("src/logsFolder/%s",
                        lastModifiedFile.getName());            /*file name when directory exists and is not empty*/
            } else if (getLastModifiedFileSize(lastModifiedFile) >= getMaxFileSize()) {
                newFileName = String.format("src/logsFolder/Log_%s.txt",
                        strDate);                               /*file name when file size exceeded*/
            }
        }
    }

    private String createFileLogFormat(LoggingLevel currentLoggingLevel) {
        DateTimeFormatter fileTimeLogFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String formattedFileTime = fileTimeLogFormat.format(now);
        return logEntriesFormat = String.format("%s %s Message:", formattedFileTime,
                currentLoggingLevel);
    }

    private static File getLastModifiedFile(File[] files) {
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

    static Path getPath() {
        return path;
    }

    static long getLastModifiedFileLength() {
        return lastModifiedFileLength;
    }
}






