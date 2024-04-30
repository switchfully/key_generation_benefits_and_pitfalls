package com.switchfully.keygeneration;

import com.switchfully.keygeneration.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class KeyGenerationApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(KeyGenerationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KeyGenerationApplication.class, args);
    }

    private final Repository repository;

    public KeyGenerationApplication(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.clearDatabase();

        logger.info("The CONTINENT table has a UUID primary key column with a randomly generated UUID as its default value. The Continent entity has the @GeneratedValue annotation on its UUID id field");
        logger.info(String.format("We can rely on JPA's autogeneration, which, by the way, does not consult the database for the generated value. Europe: %s", repository.merge(new Continent("Europe"))));
        logger.info(String.format("Instead of allowing us to choose the id as '215257ae-180e-49cf-88f4-fde968d3edf0', JPA will first do a useless search for a row with that id, and then create our entity with a different random uuid: %s", repository.merge(new Continent(UUID.fromString("215257ae-180e-49cf-88f4-fde968d3edf0"), "Africa"))));
        Continent originalAsia = new Continent("Asia");
        logger.info(String.format("JPA doesn't bother to assign ids before the entity is persisted. Original asia: %s", originalAsia));
        Continent mergedAsia = repository.merge(originalAsia);
        logger.info(String.format("JPA doesn't bother to inform the original object of its id when the entity is persisted. Original asia: %s, merged asia: %s", originalAsia, mergedAsia));


        try {
            repository.merge(new Country("Belgium"));
        } catch (RuntimeException e) {
            logger.error("JPA does not figure out there is a default value defined on the primary key for the table when using UUID id and not annotating the id field with @GeneratedValue: %s");
        }
        logger.info(String.format("But we are free to provide our own id '0d2b29c6-10b2-4c18-8bee-36bbe17878fe'. France: %s", repository.merge(new Country(UUID.fromString("0d2b29c6-10b2-4c18-8bee-36bbe17878fe"), "France"))));

        logger.info(String.format("There can be no confusion in the simple case: no default values means we have to provide an id '42cf340b-2281-4a43-8b00-bb678493e746'. Brussels: %s", repository.merge(new City(UUID.fromString("42cf340b-2281-4a43-8b00-bb678493e746"), "Brussels"))));

        logger.info(String.format("There is no default value defined on the column, but jpa does not consult the database and simply chooses a generated value itself in the case of uuids. Main street: %s", repository.merge(new Street("Main street"))));

        logger.info("The REGION table uses an autoincrementing numerical primary key. That again chances the behaviour of JPA, and the chance is database vendor specific...");
        logger.info(String.format("With postgresql, JPA is able to capture the generated id during the execution of the insert statement: %s", repository.merge(new Region("Sahara"))));
        logger.info("For other databases, JPA will need extra query executions, separate transactions, run risk of concurrency issues, including indefinitely blocked database tables....");

        Continent australia = new Continent("Australia");
        Set<Continent> continents = new HashSet<>();
        continents.add(australia);
        australia = repository.merge(australia);
        logger.error(String.format("Is australia in the set of continents? %s", continents.contains(australia)));
        logger.warn("This is a fairly common and often very difficult to find bug.");
        logger.warn("Unless the database administrators demand it (and they might), never use generated values. Not on your table definitions, nor on your entities.");
    }
}
