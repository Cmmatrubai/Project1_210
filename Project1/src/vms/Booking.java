/**
 * Vehicle Management System - Project 1
 * @author Your Name
 */
package vms;

/**
 * Booking (reservation) for a vehicle.
 */
public class Booking {
    private Date     begin;
    private Date     end;
    private Employee employee;
    private Vehicle  vehicle;

    public Booking(Date begin, Date end, Employee employee, Vehicle vehicle) {
        this.begin = begin;
        this.end = end;
        this.employee = employee;
        this.vehicle = vehicle;
    }

    public Date getBegin() { return begin; }
    public Date getEnd() { return end; }
    public Employee getEmployee() { return employee; }
    public Vehicle getVehicle() { return vehicle; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Booking)) return false;
        Booking b = (Booking) obj;
        return this.vehicle.equals(b.vehicle) &&
               this.begin.equals(b.begin) &&
               this.end.equals(b.end);
    }

    @Override
    public String toString() {
        return vehicle + " [beginning " + begin + " ending " + end + ":" + employee.name() + "]";
    }
}
