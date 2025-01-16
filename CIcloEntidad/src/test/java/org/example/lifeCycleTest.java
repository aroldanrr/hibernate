package org.example;

import org.example.models.Address;
import org.example.models.Author;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
/*
1.Transient
2.Managed
3.Detached
4.Removed
*/
public class lifeCycleTest {

    @Test
    void detached() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Address address = new Address("antonio mariscal","Granada","España");
        address.setId(1L);



        session.beginTransaction();
        //PersistentObjectException: Detached entity pased to persist:
        //session.persist(address);
        session.merge(address);
        session.getTransaction().commit();

        session.detach(address);

        session.beginTransaction();
        var address1 = session.find(Address.class, 1L);
        address1.setCity("Mallorca");
        session.persist(address1);
        session.getTransaction().commit();
    }

    @Test
    void managed() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Address address = new Address("antonio carvajal","Granada","España");

        session.persist(address);

        session.getTransaction().commit();

        System.out.println(session.contains(address));
    }

    @Test
    void removed() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        //Address address = new Address("antonio mariscal","Granada","España");
        Address address1 = new Address();
        address1.setId(2L);

        session.beginTransaction();
        session.remove(address1);
        session.getTransaction().commit();

        System.out.println(session.contains(address1));
    }

    @Test
    void diferentSessions() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        var address3 = session.find(Address.class, 3L);
        System.out.println(session.contains(address3));
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        System.out.println(session.contains(address3));

    }


    @Test
    void load() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        //Diferencia la diferencia entre .find y .getReference es que .find hace una consulta a base de datos y .getReference
        //no hace la consulta, es decir si intentamos acceder a un dato despues de cerrar la sesion con .getReference nos daria un
        //error, pero con .find si podriamos acceder ya que hace la consulta antes de cerrar la sesion
        var address3 = session.getReference(Address.class, 3L);
        //var address3 = session.find(Address.class, 3L);


        System.out.println("=========================");
        session.close();
        System.out.println(address3.getCountry());
    }
}
