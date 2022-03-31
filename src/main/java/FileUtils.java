import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static void mkdirIfNotExist(File path) throws IOException {
        if (!path.exists())
            if (!path.mkdir())
                throw new IOException(String.format("Failed to create a folder, %s", path));
    }
}
