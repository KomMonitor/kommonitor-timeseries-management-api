package de.hsbo.kommonitor.timeseries_management;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * <p>
 * Abstract RepositoryTest class.
 * </p>
 *
 * @author Benjamin Pross (b.pross@52north.org)
 * @since 1.0.0
 */
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = KomMonitorTimeseriesManagementRunner.class)
//@ComponentScan(basePackageClasses = Utils.class)
public abstract class RepositoryTest {

	static DockerImageName image = DockerImageName.parse("timescale/timescaledb-ha:pg18.1-ts2.23.1-oss")
			.asCompatibleSubstituteFor("postgres");

	static PostgreSQLContainer timescaledb = new PostgreSQLContainer(image).withDatabaseName("integration-tests-db")
			.withUsername("sa").withPassword("sa");

//    static JdbcDatabaseContainer<?> database = new PostgisContainerProvider().newInstance()
//            .withDatabaseName("integration-tests-db").withUsername("sa").withPassword("sa");

	static {
		timescaledb.start();
	}

	@DynamicPropertySource
	static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
		propertyRegistry.add("spring.datasource.url", timescaledb::getJdbcUrl);
		propertyRegistry.add("spring.datasource.password", timescaledb::getPassword);
		propertyRegistry.add("spring.datasource.username", timescaledb::getUsername);
	}
}
