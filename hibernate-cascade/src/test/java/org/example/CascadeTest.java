package org.example;

import org.example.model.Address;
import org.example.model.Author;
import org.example.model.Book;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

public class CascadeTest {

    @Test
    void oneToOne() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var adr1 = new Address("calle 1","Madrid");
        var adr2 = new Address("calle 2","Barcelona");

//        session.persist(adr1);
//        session.persist(adr2);

        var a1 = new Author("adr1", 20, adr1);
        var a2 = new Author("adr2", 30, adr2);

        session.persist(a1);
        session.persist(a2);

        session.getTransaction().commit();

        a1 = session.find(Author.class, 1L);
        System.out.println(a1);

    }

//    @Test
//    void ManyToOneBookOwner() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        session.beginTransaction();
//
//        var a1 = new Author("adr1", 20, null);
//
//        var b1 = new Book("b1",a1);
//        var b2 = new Book("b2",a1);
//        var b3 = new Book("b3",a1);
//
//        session.persist(b1);
//        session.persist(b2);
//        session.persist(b3);
//
//
//        session.getTransaction().commit();
//
//        session.close();
//    }
//
//    @Test
//    void OneToManyBookOwnerRemoveAuthor() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        session.beginTransaction();
//
//        var a1 = new Author("adr1", 20, null);
//        session.persist(a1);
//
//        var b1 = new Book("b1",a1);
//        var b2 = new Book("b2",a1);
//        var b3 = new Book("b3",a1);
//
//        session.persist(b1);
//        session.persist(b2);
//        session.persist(b3);
//
//
//        session.getTransaction().commit();
//
//        session.close();
//
//        session = HibernateUtil.getSessionFactory().openSession();
//        a1 = session.find(Author.class, 1L);
//
//        session.beginTransaction();
//        //Se borran todos los libros
//        session.remove(a1);
//        session.getTransaction().commit();
//
//    }


    //Para descomentar hay que cambiar el owner de Author a Book
//    @Test
//    void oneToManyAthorOwner() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        session.beginTransaction();
//
//        var a1 = new Author("adr1", 20, null);
//        session.persist(a1);
//
//        var b1 = new Book("b1");
//        var b2 = new Book("b2");
//        var b3 = new Book("b3");
//
//        a1.getBooks().add(b1);
//        a1.getBooks().add(b2);
//        a1.getBooks().add(b3);
//
//        session.persist(a1);
//
//
//        session.getTransaction().commit();
//
//        session.close();
//
//        session = HibernateUtil.getSessionFactory().openSession();
//        a1 = session.find(Author.class, 1L);
//
//        session.beginTransaction();
//
//        a1.getBooks().removeIf(book -> book.getTitle().equals("b1"));
//        session.persist(a1);
//
//        session.getTransaction().commit();
//        session.close();
//    }

    @Test
    void oneToManyAuthorOwnerRemoveAssociation() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var a1 = new Author("adr1", 20, null);
        session.persist(a1);

        var b1 = new Book("b1");
        var b2 = new Book("b2");
        var b3 = new Book("b3");

        a1.getBooks().add(b1);
        a1.getBooks().add(b2);
        a1.getBooks().add(b3);

        session.persist(a1);


        session.getTransaction().commit();

        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        a1 = session.find(Author.class, 1L);

        session.beginTransaction();

        a1.getBooks().removeIf(book -> book.getTitle().equals("b1"));
        session.persist(a1);

        session.getTransaction().commit();
        session.close();
    }
}
