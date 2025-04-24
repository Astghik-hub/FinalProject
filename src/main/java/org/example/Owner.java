package org.example;

import java.util.Objects;

public class Owner {
    private String fname;
    private String lname;

    public Owner(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
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
        return "Owner{" +
               "fname='" + fname + '\'' +
               ", lname='" + lname + '\'' +
               '}';
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
