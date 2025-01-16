package org.example.models;


import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalTime;

@Entity
@Table(
        name = "employess",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"dni","email"})},
        indexes = {
                @Index(columnList = "salary DESC", name = "idx_salary"),
                @Index(columnList = "nss", name = "idx_nss", unique = true),
                @Index(columnList = "check_in_time, check_out_time", name = "idx_check_in_out")
        }
)

@Check(constraints = "check_in_time < check_out_time and salary > 0 and age > 0")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 9)
    private String dni;

    private String email;

    private String nss;

    @Column(scale = 2)
    private Double salary;

    @ColumnDefault("18")
    private int age;

    @Column(name = "check_in_time")
    private LocalTime chekIn;

    @Column(name = "check_out_time")
    private LocalTime chekOut;

    public Employee() {}

    public Employee(String dni, String email, String nss, Double salary, int age, LocalTime chekIn, LocalTime chekOut) {
        this.dni = dni;
        this.email = email;
        this.nss = nss;
        this.salary = salary;
        this.age = age;
        this.chekIn = chekIn;
        this.chekOut = chekOut;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", nss='" + nss + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", chekIn=" + chekIn +
                ", chekOut=" + chekOut +
                '}';
    }
}
