import java.io.File;
import java.io.IOException;

public class RotatingFileHandler {
    private final File baseDir;
    private final String baseFileName;
    private final long maxBytes;
    private final DateStruct date;
    private File workingDir;
    private File workingFile;

    public RotatingFileHandler(String baseDir, String baseFileName, long maxBytes) throws IOException {
        this.baseDir = new File(baseDir);
        FileUtils.mkdirIfNotExist(this.baseDir);
        this.baseFileName = baseFileName;
        this.maxBytes = maxBytes;
        date = new DateStruct();
        setWorkingDir();
        setWorkingFile();
    }

    private void setWorkingDir() throws IOException {
        File dir = new File(baseDir, date.getYear());
        FileUtils.mkdirIfNotExist(dir);
        dir = new File(dir, date.getMonth());
        FileUtils.mkdirIfNotExist(dir);
        workingDir = dir;
    }

    public File getWorkingFile() {
        return workingFile;
    }

    private void setWorkingFile() {
        workingFile = new File(workingDir, baseFileName);
    }

    public String getFileNameWithDate() {
        return String.format("%s.%s", baseFileName, date.getYearMonthDay());
    }

    public int getFileNumber() {
        String[] files = workingDir.list((dir, name) -> name.startsWith(getFileNameWithDate()));
        return files == null ? 0 : files.length;
    }

    public String getNextFileNameWithDate() {
        return String.format("%s-%d", getFileNameWithDate(), getFileNumber() + 1);
    }

    public boolean rolloverRequired() {
        // SIZE
        if (workingFile.length() > maxBytes)
            return true;
        // DATE - yyyyMM
        if (date.isMonthPassed())
            return true;
        // DATE - yyyMMdd
        return date.isDayPassed();
    }

    public File rollover() throws IOException {
        File nextFileWithDate = new File(workingDir, getNextFileNameWithDate());
        if (!workingFile.renameTo(nextFileWithDate))
            throw new IOException(String.format("Failed to rename file to %s", nextFileWithDate));
        date.refresh();
        setWorkingDir();
        setWorkingFile();
        return workingFile;
    }
}
