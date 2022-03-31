import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        CsvWriter csvWriter = new CsvWriter();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int i = 0;
        while(i < 1000000) {
            Date now = new Date();
            csvWriter.write(String.format("%s,%s\n", "HELLO", sdf.format(now)));
            Thread.sleep(1);
            i++;
        }
        csvWriter.flush();
        csvWriter.close();
    }
}
