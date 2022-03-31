import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    private File file;
    private FileWriter fileWriter;
    private final RotatingFileHandler rotatingFileHandler;

    public CsvWriter() throws IOException {
        String baseDir = "./data";
        String baseFileName = "kafka.csv";
        rotatingFileHandler = new RotatingFileHandler(baseDir, baseFileName, 1024);
        file = rotatingFileHandler.getWorkingFile();
        fileWriter = new FileWriter(file);
    }

    public void write(String msg) throws IOException {
        if (rotatingFileHandler.rolloverRequired()) {
            flush();
            close();
            file = rotatingFileHandler.rollover();
            fileWriter = new FileWriter(file);
        }
        fileWriter.write(msg);
    }

    public void flush() throws IOException {
        fileWriter.flush();
    }

    public void close() throws IOException {
        fileWriter.close();
    }
}
