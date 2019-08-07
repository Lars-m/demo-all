package entity;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryCreator {

    public enum Strategy {
        NONE {
            @Override
            public String toString() {
                return "drop-and-create";
            }
        },
        CREATE {
            @Override
            public String toString() {
                return "create";
            }
        },
        DROP_AND_CREATE {
            @Override
            public String toString() {
                return "drop-and-create";
            }
        }
    };

    
    public static EntityManagerFactory getEntityManagerFactory(String puName,
            String connection_str,
            String user,
            String pw,
            Strategy strategy) {

        Properties props = new Properties();
        
        //A test running on a different thread can alter values to use via properties
        System.out.println("IS Testing: " + System.getProperty("IS_TEST"));
        if (System.getProperty("IS_TEST") != null) {
            connection_str = System.getProperty("IS_TEST");
            user = System.getProperty("USER") != null ? System.getProperty("USER") : user;
            pw = System.getProperty("PW") != null ? System.getProperty("PW") : pw;
        }
        
        //A deployment server MUST set the following values which will override the defaults
        boolean isDeployed = (System.getenv("SERVER") != null);
        if (isDeployed) {
            user = System.getenv("USER") != null ? System.getenv("USER") : "";
            pw = System.getenv("PW") != null ? System.getenv("PW") : "";
        }
        
        props.setProperty("javax.persistence.jdbc.user", user);
        props.setProperty("javax.persistence.jdbc.password", pw);
        props.setProperty("javax.persistence.jdbc.url", connection_str);
        if (strategy != Strategy.NONE) {
            props.setProperty("javax.persistence.schema-generation.database.action", strategy.toString());
        }
        return Persistence.createEntityManagerFactory(puName, props);
    }

    
}
