package registerTests.registerTests;

import com.mycompany.devman.BackendSetup;
import com.mycompany.devman.domain.*;
import com.mycompany.devman.repositories.MailConfigRepository;
import com.sun.activation.registries.MailcapFile;
import com.sun.activation.registries.MailcapParseException;
import com.sun.activation.registries.MailcapTokenizer;
import com.sun.mail.imap.protocol.MailboxInfo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.ini4j.Config;
import org.junit.Before;
import org.junit.Test;
import sun.net.www.protocol.mailto.MailToURLConnection;

import javax.activation.MailcapCommandMap;
import javax.mail.event.MailEvent;
import javax.mail.internet.MailDateFormat;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;

/**
 * Created by bloczek on 17.05.2017.
 */
public class MailConfigRepositoryTest {
    @Before
    public void setup() {
        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(User.class)
                .addAnnotatedClass(Leave.class)
                .addAnnotatedClass(Message.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(Task.class)
                .addAnnotatedClass(Team.class)
                .addAnnotatedClass(WorkTime.class)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                .setProperty("hibernate.connection.url", "jdbc:h2:mem:")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .buildSessionFactory();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        BackendSetup.setSessionFactory(sessionFactory);
        BackendSetup.setValidatorFactory(validatorFactory);
    }

    @Test (expected = Exception.class)
    public void setMailConfig() throws Exception {
        MailConfig mailConfig = new MailConfig();
        MailcapCommandMap.getDefaultCommandMap();
        MailcapTokenizer.nameForToken(2);
        MailDateFormat.getAvailableLocales();
        MailConfigRepository.setMailConfig(mailConfig);
    }

    @Test (expected = Exception.class)
    public void getMailCOnfig() throws Exception {
        MailConfig mailConfigg = new MailConfig();
        MailcapCommandMap.getDefaultCommandMap();
        MailConfigRepository.getMailCOnfig();
        MailDateFormat.getAvailableLocales();
        MailToURLConnection.getDefaultAllowUserInteraction();
        MailcapTokenizer.nameForToken(3);

    }

}