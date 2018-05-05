package au.com.maxcheung.lookup;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class AppContextTest {

    private static final String FLYWAY_LOCATIONS_CLASSPATH_DB_H2 = "flyway.locations=classpath:db/h2";
    private static final String SERVER_PORT_ARG = "server.port=0";

    @Test
    public void shouldCreatePropertiesMap() {
        assertEquals(1,App.makeMap(new String[] {SERVER_PORT_ARG}).size());
        assertEquals(0,App.makeMap(new String[] {"some random argument"}).size());
    }

    @Test
    public void shouldRunAppWithServerPort() {
        String[] properties = new String[2];
        properties[0] = SERVER_PORT_ARG;
        properties[1] = FLYWAY_LOCATIONS_CLASSPATH_DB_H2;
        App.main(properties);
        assertEquals(2,properties.length);
    }
}