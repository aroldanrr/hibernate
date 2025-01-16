package org.example.model.example2;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CompanyPk implements Serializable {

    private static final long serialVersionUID = -432342545345451L;
    private String cif;

    private String brand;

    public CompanyPk() {
    }

    public CompanyPk(String cif, String brand) {
        this.cif = cif;
        this.brand = brand;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "CompanyPk{" +
                "cif='" + cif + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
