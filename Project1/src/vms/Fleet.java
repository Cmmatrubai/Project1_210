/**
 * Vehicle Management System - Project 1
 * @author Chaitanya Matrubai
 */
package vms;

/**
 * Resizable array of vehicles.
 */
public class Fleet {
    private static final int CAPACITY  = 4; // initial capacity
    private static final int NOT_FOUND = -1;

    private Vehicle[] fleet;
    private int size;

    public Fleet() {
        this.fleet = new Vehicle[CAPACITY];
        this.size = 0;
    }

    private int find(Vehicle vehicle) {
        for (int i = 0; i < size; i++) {
            if (fleet[i].equals(vehicle)) return i;
        }
        return NOT_FOUND;
    }

    private void grow() {
        Vehicle[] nf = new Vehicle[fleet.length + CAPACITY];
        for (int i = 0; i < size; i++) nf[i] = fleet[i];
        fleet = nf;
    }

    public void add(Vehicle vehicle) {
        if (size == fleet.length) grow();
        fleet[size++] = vehicle;
    }

    public void remove(Vehicle vehicle) {
        int idx = find(vehicle);
        if (idx == NOT_FOUND) return;
        fleet[idx] = fleet[size - 1];
        fleet[size - 1] = null;
        size--;
    }

    public boolean contains(Vehicle vehicle) {
        return find(vehicle) != NOT_FOUND;
    }

    /** Selection sort in-place by Vehicle.compareTo (make, then obtained). */
    public void printByMake() {
        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (fleet[j].compareTo(fleet[min]) < 0) min = j;
            }
            if (min != i) {
                Vehicle tmp = fleet[i];
                fleet[i] = fleet[min];
                fleet[min] = tmp;
            }
        }
        for (int i = 0; i < size; i++) {
            System.out.println(fleet[i]);
        }
    }

    public Vehicle getByPlate(String plate) {
        for (int i = 0; i < size; i++) {
            if (fleet[i].getPlate().equals(plate)) return fleet[i];
        }
        return null;
    }

    public int size() { return size; }
}