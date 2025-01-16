package org.example;

import org.example.model.example2.*;
import org.junit.jupiter.api.Test;

public class EmbeddableIdTest {

    @Test
    void name() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var companyPk1 = new CompanyPk("cif1", "brand1");
        var c1a = session.find(Company.class, companyPk1);
        System.out.println(c1a);

        var companyPk2 = new CompanyPk("cif2", "brand2");
        var c1b = session.find(Company.class, companyPk2);
        System.out.println(c1b);
    }

    void insertData(){

        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var companyPk1 = new CompanyPk("cif1", "brand1");
        var companyPk2 = new CompanyPk("cif2", "brand2");

        var company1a = new Company(companyPk1, "Madrid", 22);
        var company1b = new Company(companyPk2, "Barcelona", 28);

        session.persist(company1a);
        session.persist(company1b);

        session.getTransaction().commit();
        session.close();


    }
}
