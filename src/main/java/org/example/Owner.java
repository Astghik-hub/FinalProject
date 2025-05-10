package org.example;

import java.util.Objects;

public class Owner {
    private String fname;
    private String lname;

    public Owner(String fname, String lname) {
        this.fname = toTitleCase(fname);
        this.lname = toTitleCase(lname);
    }

    /**
     * converts a String to titleCase
     *
     * @param str the string to convert
     * @return the first letter uppercase and the rest lowercase
     */
    public String toTitleCase(String str) {
        if (str.isEmpty()) {
            return "";
        } else return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(fname, owner.fname) && Objects.equals(lname, owner.lname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, lname);
    }

    @Override
    public String toString() {
        return fname + ' ' + lname;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
}
