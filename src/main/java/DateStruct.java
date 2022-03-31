import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStruct {
    private static final SimpleDateFormat yearMonthDayFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private static final SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

    private Date date;
    private String year;
    private String month;
    private String day;
    private String yearMonthDay;

    public DateStruct() {
        refresh();
    }

    public void refresh() {
        date = new Date();
        year = yearFormat.format(date);
        month = monthFormat.format(date);
        day = dayFormat.format(date);
        yearMonthDay = yearMonthDayFormat.format(date);
    }

    public Date getDate() {
        return date;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getYearMonthDay() {
        return yearMonthDay;
    }

    public boolean isMonthPassed() {
        return !month.equals(monthFormat.format(new Date()));
    }

    public boolean isDayPassed() {
        return !day.equals(dayFormat.format(new Date()));
    }
}
