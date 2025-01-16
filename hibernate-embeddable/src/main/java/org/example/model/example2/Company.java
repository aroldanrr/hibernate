package org.example.model.example2;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;

public class Company {

    @EmbeddedId
    @AttributeOverride(name = "cif",column = @Column(name = "company_cif"))
    private CompanyPk id;

    private String location;

    private int employeeNum;

    public Company() {
    }

    public Company(CompanyPk id, String location, int employeeNum) {
        this.id = id;
        this.location = location;
        this.employeeNum = employeeNum;
    }

    public CompanyPk getId() {
        return id;
    }

    public void setId(CompanyPk id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", employeeNum=" + employeeNum +
                '}';
    }
}
