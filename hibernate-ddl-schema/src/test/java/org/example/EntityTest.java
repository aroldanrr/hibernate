package org.example;


import org.example.models.Address;
import org.example.models.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class EntityTest {

    @Test
    void name() {
        insertData();

        var session = HibernateUtil.getSessionFactory().openSession();

        var emp1 = session.find(Employee.class, 1L);
        System.out.println(emp1);
        session.close();
        HibernateUtil.shutdown();
    }

    void insertData(){

        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var emp1 = new Employee("77144303","e1@email.com","1111",3000.0, 20, LocalTime.of(11,30),LocalTime.of(19,30));
        var emp2 = new Employee("77144302","e2@email.com","3333",3000.0, 20, LocalTime.of(10,30),LocalTime.of(18,30));
        var emp3 = new Employee("77144301","e3@email.com","2222",3000.0, 20, LocalTime.of(9,30),LocalTime.of(17,30));

        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);


        session.getTransaction().commit();

    }
}
