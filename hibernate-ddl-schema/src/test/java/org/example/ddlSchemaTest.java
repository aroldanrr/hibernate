package org.example;

import org.example.models.Address;
import org.example.models.Author;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

public class ddlSchemaTest {

    @Test
    void insertData() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var addr1 = new Address("Antonio Carvajal", "Granada","España");

        session.persist(addr1);

        session.getTransaction().commit();

        session.close();

    }
}
