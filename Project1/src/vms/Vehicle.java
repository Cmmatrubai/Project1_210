/**
 * Vehicle Management System - Project 1
 * @author Your Name
 */
package vms;

/**
 * Vehicle entity.
 */
public class Vehicle implements Comparable<Vehicle> {
    private String plate;
    private Date   obtained;
    private Make   make;
    private int    mileage;

    public Vehicle(String plate, Date obtained, Make make, int mileage) {
        this.plate = plate;
        this.obtained = obtained;
        this.make = make;
        this.mileage = mileage;
    }

    // Getters
    public String getPlate() {
        return plate;
    }

    public Date getObtained() {
        return obtained;
    }

    public Make getMake() {
        return make;
    }

    public int getMileage() {
        return mileage;
    }

    // Setters
    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setObtained(Date obtained) {
        this.obtained = obtained;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vehicle)) return false;
        Vehicle v = (Vehicle) obj;
        return this.plate != null && this.plate.equals(v.plate);
    }

    @Override
    public String toString() {
        return plate + ":" + make + ":" + obtained + " [mileage:" + mileage + "]";
    }

    @Override
    public int compareTo(Vehicle o) {
        int mk = this.make.ordinal() - o.make.ordinal();
        if (mk != 0) return mk;
        return this.obtained.compareTo(o.obtained);
    }

    /** Testbed main(): three compareTo cases. */
    public static void main(String[] args) {
        Vehicle a = new Vehicle("A", new Date(1,1,2020), Make.CHEVY, 100);
        Vehicle b = new Vehicle("B", new Date(1,1,2020), Make.FORD, 100);
        Vehicle c = new Vehicle("C", new Date(1,2,2020), Make.CHEVY, 100);
        Vehicle a2= new Vehicle("X", new Date(1,1,2020), Make.CHEVY, 0);

        System.out.println("a vs b -> " + Integer.signum(a.compareTo(b))); // -1
        System.out.println("a vs c -> " + Integer.signum(a.compareTo(c))); // -1
        System.out.println("a vs a2 -> " + Integer.signum(a.compareTo(a2))); // 0
    }
}
