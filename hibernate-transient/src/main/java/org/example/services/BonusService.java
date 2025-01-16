package org.example.services;

import org.example.model.Employee;

import java.time.LocalDate;

public class BonusService {

    public BonusService() {}

    public void calculateBonus(Employee employee) {
        int joinedYear = employee.getJoinDate().getYear();
        int currentYear = LocalDate.now().getYear();
        int elapsedYear = currentYear - joinedYear;
        Double bonus = elapsedYear * 100.0;
        employee.setBonus(bonus);
    }
}
