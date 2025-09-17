/**
 * Vehicle Management System - Project 1
 * @author Chaitanya Matrubai
 */

package vms;
import java.util.Calendar;
import java.util.StringTokenizer;
public class Date implements Comparable<Date> {
    private int year;
    private int month; 
    private int day;   

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }


    public static Date parse(String s) {
        if (s == null) return null;
        StringTokenizer st = new StringTokenizer(s, "/");
        if (st.countTokens() != 3) return null;
        try {
            int m = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            return new Date(m, d, y);
        } catch (Exception ex) {
            return null;
        }
    }

    // getters…

    private static boolean isLeap(int year) {
        if (year % QUADRENNIAL != 0) return false;
        if (year % CENTENNIAL != 0) return true;
        return (year % QUATERCENTENNIAL == 0);
    }

    /** Check if this is a valid calendar date (Gregorian rules). */
    public boolean isValid() {
        if (year <= 0) return false;
        if (month < 1 || month > 12) return false;
        if (day < 1) return false;
        int[] monthLen = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        int mdays = monthLen[month];
        if (month == 2 && isLeap(year)) mdays = 29;
        if (day > mdays) return false;
        return true;
    }

    @Override
    public int compareTo(Date o) {
        if (this.year != o.year) return this.year - o.year;
        if (this.month != o.month) return this.month - o.month;
        return this.day - o.day;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Date)) return false;
        Date d = (Date) obj;
        return this.year == d.year && this.month == d.month && this.day == d.day;
    }

    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    public static Date today() {
        Calendar c = Calendar.getInstance();
        return new Date(c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.YEAR));
    }

    
    public boolean before(Date other) {
        if (other == null) return false;
        return this.compareTo(other) < 0;
    }

    /**
     * Check if this date is after the given date.
     * @param other the date to compare against
     * @return true if this date is after the other date, false otherwise
     */
    public boolean after(Date other) {
        if (other == null) return false;
        return this.compareTo(other) > 0;
    }

    // plus after(), daysSince(), addDays(), addMonths()…

    /** Testbed main(): 6 cases */
    public static void main(String[] args) {
        Date[] tests = new Date[]{
            new Date(2,29,2024),
            new Date(2,29,2025),
            new Date(4,31,2025),
            new Date(11,30,2025),
            new Date(0,10,2025),
            new Date(12,0,2025)
        };
        for (Date d : tests) {
            System.out.println(d + " -> " + d.isValid());
        }
    }
}