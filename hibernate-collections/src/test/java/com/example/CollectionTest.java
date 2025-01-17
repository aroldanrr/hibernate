package com.example;

import com.example.model.CreditCard;
import com.example.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionTest {

    @Test
    void basicTypePhones() {

        insertData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        var emp1 = session.find(Employee.class, 1L);

        System.out.println(emp1);

        emp1.getPhones().forEach(System.out::println);
    }

    @Test
    void basicTypeSalaries() {

        insertData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        var emp3 = session.find(Employee.class, 3L);

        System.out.println(emp3);

        emp3.getSalaries().stream().reduce(Double::sum).ifPresent(System.out::println);
    }

    @Test
    void basicPostalCode() {

        insertData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        var emp5 = session.find(Employee.class, 5L);

        System.out.println(emp5);

        System.out.println(emp5.getPhones());
        System.out.println(emp5.getSalaries());
        System.out.println(emp5.getPostalCodes());

    }

    @Test
    void entityType() {

        insertData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        var emp6 = session.find(Employee.class, 6L);

        emp6.getCards().forEach((k, v) -> System.out.println(k + " " + v));

    }



    void insertData(){

        Session session = HibernateUtil.getSessionFactory().openSession();


        session.beginTransaction();

        var emp1 = new Employee("emp1", "1221", List.of("1111", "2222", "3333"));
        var emp2 = new Employee("emp2", "3232", List.of("4444", "5555", "6666"));

        session.persist(emp1);
        session.persist(emp2);

        var emp3 = new Employee("emp3", "2333", List.of("7777", "8888", "9999"), List.of(2000.0, 3200.0, 1200.0));
        var emp4 = new Employee("emp4", "2212", List.of("2323", "3434", "4545"), List.of(1000.0, 2200.0, 3300.0));


        session.persist(emp3);
        session.persist(emp4);

        var emp5 = new Employee("emp5", "4343",
                List.of("5656", "6767", "8989"),
                List.of(2002.0, 3200.0, 1200.0),
                Set.of("18194","18134","18294"));

        session.persist(emp5);

        CreditCard c1 = new CreditCard("1216", "emp6");
        CreditCard c2 = new CreditCard("1892", "emp6");
        CreditCard c3 = new CreditCard("1012", "emp6");

        var emp6 = new Employee("emp6", "4383",
                Map.of("1111", c1,
                        "1281", c2,
                        "1101", c3));

        session.persist(emp6);

        session.getTransaction().commit();
    }
}
