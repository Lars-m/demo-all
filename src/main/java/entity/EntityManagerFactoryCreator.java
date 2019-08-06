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

    public static String getValFromEnvironment(String key, String defaultVal) {
        String value = System.getenv(key);
        return value != null ? value : defaultVal;
    }

    public static EntityManagerFactory getEntityManagerFactory(String puName, 
                                                               String connection_str, 
                                                               String user, 
                                                               String pw, 
                                                               Strategy strategy) {

        Properties props = new Properties();

//        String db_user = getValFromEnvironment("USER", user);
//        String password = getValFromEnvironment("PASSWORD",pw);
//        String connectionStr = getValFromEnvironment("CONNECTION_STR",connection_str);
//        
        System.out.println("-------------->" + strategy.toString());
        props.setProperty("javax.persistence.jdbc.user", user);
        props.setProperty("javax.persistence.jdbc.password", pw);
        props.setProperty("javax.persistence.jdbc.url", connection_str);
        if (strategy != Strategy.NONE) {
            props.setProperty("javax.persistence.schema-generation.database.action", strategy.toString());
        }
        return Persistence.createEntityManagerFactory(puName, props);
    }

    public static EntityManagerFactory getEntityManagerFactory(String puName,                                                                
                                                               Strategy strategy) {

        Properties props = new Properties();

        String user = System.getenv("USER");
        String pw = System.getenv("PASSWORD");
        String connection_str = System.getenv("CONNECTION_STR");
        
        System.out.println("-------------->" + strategy.toString());
        props.setProperty("javax.persistence.jdbc.user", user);
        props.setProperty("javax.persistence.jdbc.password", pw);
        props.setProperty("javax.persistence.jdbc.url", connection_str);
        if (strategy != Strategy.NONE) {
            props.setProperty("javax.persistence.schema-generation.database.action", strategy.toString());
        }
        return Persistence.createEntityManagerFactory(puName, props);
    }

}
