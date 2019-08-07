//package rest;
//
//import dto.PersonDto;
//import entity.EntityManagerFactoryCreator;
//import entity.Person;
//import java.io.IOException;
//import java.net.URI;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriBuilder;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.glassfish.grizzly.http.server.HttpServer;
//import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.junit.AfterClass;
//import org.junit.Before;
//
//public class PersonResourceTest {
//
//    private int TEST_PORT = 7777;
////  private static URI getBaseURI() {
////    return UriBuilder.fromUri("http://localhost/api").port(7777).build();
////  }
//
//    
//    static final URI BASE_URI = UriBuilder.fromUri("http://localhost/api").port(7777).build();
//    private static HttpServer httpServer;
//    private static EntityManagerFactory emf;
//    
//    static HttpServer startServer() {
//        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
//
//        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
//    }
//
//    public PersonResourceTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//        
//       System.setProperty("IS_TEST", "jdbc:mysql://localhost:3307/mydb_test");
//        emf = EntityManagerFactoryCreator.getEntityManagerFactory(
//                "pu",
//                "jdbc:mysql://localhost:3307/mydb_test",
//                "dev",
//                "ax2",
//                EntityManagerFactoryCreator.Strategy.DROP_AND_CREATE); 
//       httpServer =  startServer();
//    }
//    @AfterClass
//    public static void tearDownClass() {
//        httpServer.shutdownNow();
//    }
//    
//    @Before
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createQuery("DELETE from Person").executeUpdate();
//            em.persist(new Person("a", "b", "c", "d", "a@b.dk"));
//            em.persist(new Person("a2", "b2", "c2", "d2", "a2@b.dk"));
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    private static final String REST_URI 
//      = "http://localhost:7777/api/person/all";
//    @Test
//    public void testGetPerson() throws Exception {
//        
//    }
//
//    @Test
//    public void testGetPersons() throws Exception {
//         Client client = ClientBuilder.newClient();
//         Response response = client.target(REST_URI).request(MediaType.APPLICATION_JSON).get();
//         
//         
//         System.out.println("--->"+persons);
//         System.out.println("--->"+persons.getClass());
//         System.out.println("--->"+persons.get(0));
//         assertEquals(2, persons.size());
//         
//        //Observe we can't count on getting data in the same order as it was inserted
//        //String fn1 = persons.get(0).getFirstName();
////        String fn2 = persons.get(1).getFirstName();
////        boolean namesAreDifferent = !fn1.equals(fn2);        
////        assertTrue("Expected two Person instances with different names",namesAreDifferent);
//    }
//
//    @Test
//    public void testGetMsg() {
//    }
//
//    public static void main(String[] args) throws IOException {
//         System.setProperty("IS_TEST", "jdbc:mysql://localhost:3307/mydb_test");
//        System.out.println("Starting grizzly... (Press any key to stop the server)");
//        HttpServer httpServer = startServer();
//        System.in.read();
//        httpServer.shutdownNow();
//    }
//}
