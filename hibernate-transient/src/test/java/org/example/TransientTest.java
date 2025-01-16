package org.example;

import org.example.model.Employee;
import org.example.model.Product;
import org.example.services.BonusService;
import org.example.services.PricingService;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class TransientTest {

    private BonusService bonusService = new BonusService();
    private PricingService pricingService = new PricingService();

    @Test
    void employeeBonusNull() {

        insertData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "Select e from Employee e";
        var employees = session.createQuery(jpql, Employee.class).list(); //Delvoveria varios empleados

        employees.forEach(System.out::println);
    }

    @Test
    void employeeBonusCalculate() {

        insertData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "Select e from Employee e";

        //Esto devolveria una lista de los empleados
        // var employees = session.createQuery(jpql, Employee.class).list()
        var employees = session.createQuery(jpql, Employee.class).list();

        employees.forEach(bonusService::calculateBonus);

        employees.forEach(System.out::println);
    }

    @Test
    void product() {

        insertData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "Select p from Product p";


        var prices = session.createQuery(jpql, Product.class)
                .getResultList().stream()
                .map(pricingService::calculatePriceWithTaxes)
                .map(Product::getPriceWithTaxes)
                .collect(Collectors.toList());

        System.out.println(prices);

        var totalPrice = session.createQuery(jpql, Product.class)
                .getResultList().stream()
                .map(pricingService::calculatePriceWithTaxes)
                .map(Product::getPriceWithTaxes)
                .reduce(0.0, Double::sum);

        System.out.println(totalPrice);
    }


    void insertData(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        var emp1 = new Employee("e1@gmail.com",1200.0, LocalDate.of(2018,1,1));
        emp1.setBonus(100.0);
        var emp2 = new Employee("e2@gmail.com",1000.0, LocalDate.of(2019,1,1));
        var emp3 = new Employee("e3@gmail.com",2000.0, LocalDate.of(2017,1,1));
        var emp4 = new Employee("e4@gmail.com",1300.0, LocalDate.of(2020,1,1));

        session.beginTransaction();
        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);
        session.persist(emp4);


        var prod1 = new Product("prod1",100.0);
        var prod2 = new Product("prod2",200.0);
        var prod3 = new Product("prod3",300.0);

        session.persist(prod1);
        session.persist(prod2);
        session.persist(prod3);

        session.getTransaction().commit();
    }
}
