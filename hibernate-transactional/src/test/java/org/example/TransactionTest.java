package org.example;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
API Transaction de Hibernate proporciona una manera uniforme de manejar transacciones
 independendiente mente del mecanismo utilizado: JDBC; JTA.

 */

public class TransactionTest {

    //@Transactional()
    @Test
    @DisplayName("Modo JDBC: La transsacción la gestiona la aplicación localmente")
    public void jdbcTransaction() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            // Modo JDBC: llama a java.sql.Connection#setAutoCommit(false) para iniciar una transacción
            session.beginTransaction();
            session.createMutationQuery("UPDATE Customer set age = :age where id = :id")
                    .setParameter("age", 18)
                    .setParameter("id", 1L)
                    .executeUpdate();

            //Llama a java.sql.Connection() para confirmar la transacción
            session.getTransaction().commit();
        }catch(Exception e){
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
                e.printStackTrace();

        }finally {
            session.close();
            HibernateUtil.shutdown();
        }

    }

    @Test
    @DisplayName("Modo JTA CMT: La transsacción la inicia el contenedor/servidor de aplicaciones")
    public void jdbc_jta_cmt() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            // Modo JTA CMT: la transacción la gestiona el servidor de aplicaciones
//            session.beginTransaction();
            session.createMutationQuery("UPDATE Customer set age = :age where id = :id")
                    .setParameter("age", 18)
                    .setParameter("id", 1L)
                    .executeUpdate();

            // Modo JTA CMT: la transacción la confirma el servidor de aplicaciones
            //session.getTransaction().commit();
        }catch(Exception e){
//            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
//                session.getTransaction().rollback();
//            }
            e.printStackTrace();

        }finally {
            session.close();
            HibernateUtil.shutdown();
        }

    }

    @Test
    @DisplayName("Modo JTA BMT:La aplicación inicia la transacción e interactúa con JTA")
    public void jdbc_jta_bmt() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            // Modo JTA BMT:esta llamada invoca begin en el USerTransaction y TransactionManager
            session.beginTransaction();
            session.createMutationQuery("UPDATE Customer set age = :age where id = :id")
                    .setParameter("age", 18)
                    .setParameter("id", 1L)
                    .executeUpdate();

            //Modo JTA CMT: Modo JTA BMT:esta llamada invoca commit en el USerTransaction y TransactionManager
            session.getTransaction().commit();
        }catch(Exception e){
            //Modo JTA CMT: Modo JTA BMT:esta llamada invoca rollback en el USerTransaction y TransactionManager
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();

        }finally {
            session.close();
            HibernateUtil.shutdown();
        }

    }
}
