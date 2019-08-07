/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.EntityManagerFactoryCreator;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonFacadeTest {

    public PersonFacadeTest() {
    }

    private static PersonFacade facade;
    private static EntityManagerFactory emf;

    @BeforeClass
    public static void setUpClass() {
        emf = EntityManagerFactoryCreator.getEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/mydb_test",
                "dev",
                "ax2",
                EntityManagerFactoryCreator.Strategy.DROP_AND_CREATE);
        facade = PersonFacade.getPersonFacade(emf);
    }
    
    @Before
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Person").executeUpdate();
            em.persist(new Person("a", "b", "c", "d", "a@b.dk"));
            em.persist(new Person("a2", "b2", "c2", "d2", "a2@b.dk"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @After
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            System.out.println("Deleting test data");
            em.getTransaction().begin();
            em.createQuery("DELETE from Person").executeUpdate();
            em.getTransaction().commit();
            System.out.println("Deleted test data");
        } finally {
            em.close();
        }
    }

    @Test
    public void testAddPerson() {
        Person p = facade.addPerson("a", "b", "c", "d", "a@b.dk");
        //If p has an id, it must have been generated by the database;
        assertNotNull("Expected a Person instance with a DB-generated ID",p.getId());
    }

    @Test
    public void testGetPersons() {
        List<Person> persons = facade.getPersons();
        assertEquals(2, persons.size());
        //Observe we can't count on getting data in the same order as it was inserted
        String fn1 = persons.get(0).getFirstName();
        String fn2 = persons.get(1).getFirstName();
        boolean namesAreDifferent = fn1.equals(fn2);        
        assertTrue("Expected two Person instances with different names",namesAreDifferent);
    }

}
