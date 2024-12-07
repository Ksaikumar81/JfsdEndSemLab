package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Initialize Hibernate session factory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Insert Records
        Transaction transaction = session.beginTransaction();
        Customer customer1 = new Customer();
        customer1.setName("SaiKumar");
        customer1.setEmail("saikumar@gamil.com");
        customer1.setAge(25);
        customer1.setLocation("Maharastra");

        Customer customer2 = new Customer();
        customer2.setName("Vamshi");
        customer2.setEmail("Vamshi@gamil.com");
        customer2.setAge(30);
        customer2.setLocation("kadapa");

        session.save(customer1);
        session.save(customer2);
        transaction.commit();

        // Apply Criteria Queries
        Criteria criteria = session.createCriteria(Customer.class);

        System.out.println("=== Customers with age > 25 ===");
        criteria.add(Restrictions.gt("age", 25));
        List<Customer> customers = criteria.list();
        for (Customer c : customers) {
            System.out.println(c.getName() + " - " + c.getAge());
        }

        System.out.println("=== Customers with location like '%York%' ===");
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.like("location", "%York%"));
        customers = criteria.list();
        for (Customer c : customers) {
            System.out.println(c.getName() + " - " + c.getLocation());
        }

        // Close the session and factory
        session.close();
        sessionFactory.close();
    }
}
