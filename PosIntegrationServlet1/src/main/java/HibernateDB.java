import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
//import sun.tools.java.Environment;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;
import java.util.Properties;

public class HibernateDB {
    private static SessionFactory sessionFactory;

    private static SessionFactory createSessionFactory(){


        Configuration configuration = new Configuration();

        //configuration.configure("hibernate.cfg.xml");

        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/assignment_db?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        properties.setProperty(Environment.USER, "root");
        properties.setProperty(Environment.PASS, "");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.SHOW_SQL, "true");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        configuration.setProperties(properties);

        configuration.addAnnotatedClass(AccountHolders.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;

    }

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null)
            sessionFactory = createSessionFactory();

        return sessionFactory;
    }
}
