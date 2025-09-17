/**
 * Vehicle Management System - Project 1
 * @author Your Name
 */
package vms;

/**
 * Employee enum with department attribute.
 */
public enum Employee {
    PATEL(Department.COMPUTER_SCIENCE),
    LIM(Department.ELECTRICAL_ENGINEERING),
    ZIMNES(Department.COMPUTER_SCIENCE),
    HARPER(Department.ELECTRICAL_ENGINEERING),
    KAUR(Department.INFORMATION_TECHNOLOGY_AND_INFORMATICS),
    TAYLOR(Department.MATH),
    RAMESH(Department.MATH),
    CERAVOLO(Department.BUSINESS_ANALYTICS_AND_INFORMATION_TECHNOLOGY);

    private final Department dept;

    Employee(Department dept) {
        this.dept = dept;
    }

    public Department getDepartment() {
        return dept;
    }

    /** Case-insensitive lookup by last name, or null if not found. */
    public static Employee fromString(String s) {
        if (s == null) return null;
        String u = s.trim().toUpperCase();
        for (Employee e : values()) {
            if (e.name().equals(u)) return e;
        }
        return null;
    }
}
