/**
 * Vehicle Management System - Project 1
 * @author Your Name
 */
package vms;

/**
 * Resizable array of Bookings.
 */
public class Reservation {
    private static final int CAPACITY  = 4;
    private static final int NOT_FOUND = -1;

    private Booking[] bookings;
    private int size;

    public Reservation() {
        this.bookings = new Booking[CAPACITY];
        this.size = 0;
    }

    private int find(Booking booking) {
        for (int i = 0; i < size; i++) {
            if (bookings[i].equals(booking)) return i;
        }
        return NOT_FOUND;
    }

    private void grow() {
        Booking[] nb = new Booking[bookings.length + CAPACITY];
        for (int i = 0; i < size; i++) nb[i] = bookings[i];
        bookings = nb;
    }

    public void add(Booking booking) {
        if (size == bookings.length) grow();
        bookings[size++] = booking;
    }

    public void remove(Booking booking) {
        int idx = find(booking);
        if (idx == NOT_FOUND) return;
        bookings[idx] = bookings[size - 1];
        bookings[size - 1] = null;
        size--;
    }

    public boolean contains(Booking booking) {
        return find(booking) != NOT_FOUND;
    }

    /** Print ordered by license plate, then beginning date. */
    public void printByVehicle() {
        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                int cmp = bookings[j].getVehicle().getPlate().compareTo(bookings[min].getVehicle().getPlate());
                if (cmp == 0) {
                    cmp = bookings[j].getBegin().compareTo(bookings[min].getBegin());
                }
                if (cmp < 0) min = j;
            }
            if (min != i) {
                Booking tmp = bookings[i];
                bookings[i] = bookings[min];
                bookings[min] = tmp;
            }
        }
        for (int i = 0; i < size; i++) System.out.println(bookings[i]);
    }

    /** Print ordered by department, then employee (alphabetical by enum name). */
    public void printByDept() {
        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                Department d1 = bookings[j].getEmployee().getDepartment();
                Department d2 = bookings[min].getEmployee().getDepartment();
                int cmp = d1.ordinal() - d2.ordinal();
                if (cmp == 0) {
                    cmp = bookings[j].getEmployee().name().compareTo(bookings[min].getEmployee().name());
                }
                if (cmp < 0) min = j;
            }
            if (min != i) {
                Booking tmp = bookings[i];
                bookings[i] = bookings[min];
                bookings[min] = tmp;
            }
        }
        for (int i = 0; i < size; i++) System.out.println(bookings[i]);
    }

    public int size() { return size; }

    public Booking getEarliestEndingForVehicle(String plate) {
        Booking best = null;
        for (int i = 0; i < size; i++) {
            if (!bookings[i].getVehicle().getPlate().equals(plate)) continue;
            if (best == null || bookings[i].getEnd().compareTo(best.getEnd()) < 0) {
                best = bookings[i];
            }
        }
        return best;
    }

    public boolean hasConflict(Employee emp, Date b, Date e) {
        for (int i = 0; i < size; i++) {
            if (bookings[i].getEmployee() != emp) continue;
            Date B = bookings[i].getBegin();
            Date E = bookings[i].getEnd();
            boolean overlap = !(e.before(B) || b.after(E));
            if (overlap) return true;
        }
        return false;
    }

    public boolean vehicleUnavailable(String plate, Date b, Date e) {
        for (int i = 0; i < size; i++) {
            if (!bookings[i].getVehicle().getPlate().equals(plate)) continue;
            Date B = bookings[i].getBegin();
            Date E = bookings[i].getEnd();
            boolean overlap = !(e.before(B) || b.after(E));
            if (overlap) return true;
        }
        return false;
    }
}
