package de.hsbo.kommonitor.timeseries_management;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.stations.StationsApiController;
import de.hsbo.kommonitor.timeseries_management.api.impl.stations.StationsRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesApiController;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesDataRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesMetadataRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesWithParameterNameApiController;

/**
 * <p>
 * TimeSeriesManagementApiIT class.
 * </p>
 *
 * @author Benjamin Pross (b.pross@52north.org)
 * @since 1.0.0
 */
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@ContextConfiguration(initializers = { TimeSeriesManagementApiIT.Initializer.class })
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TimeSeriesManagementApiIT {

	private static final String ROOT_ENDPOINT = "/timeseries-management";

	private static final String STATIONS_ENDPOINT = ROOT_ENDPOINT + "/stations";

	private static final String TIMESERIES_ENDPOINT = ROOT_ENDPOINT + "/timeseries";

	private static final String TIMESERIES_DATA_ENDPOINT_TEMPLATE = ROOT_ENDPOINT + "/timeseries/%s/%s";

	private static final String TIMESERIES_DATA_BY_NAME_ENDPOINT_TEMPLATE = ROOT_ENDPOINT + "/timeseries/%s";

	private static final MediaType PROVENANCE_ENDPOINT_MEDIA_TYPE = MediaType
			.parseMediaType("application/vnd.org.n52.project.enforce.provenance-service.api.v1+json");

	private static final String STATION_ID = "2234";

	@Autowired
	private StationsRepository stationsRepository;

	@Autowired
	private ParametersRepository parametersRepository;

	@Autowired
	private TimeseriesDataRepository timeseriesDataRepository;

	@Autowired
	private TimeseriesMetadataRepository timeseriesMetadataRepository;

	private MockMvc mvc;

	private StationsApiController stationsApiController;

	private TimeseriesApiController timeseriesApiController;

	private TimeseriesWithParameterNameApiController timeseriesWithParameterNameApiController;

	static DockerImageName image = DockerImageName.parse("timescale/timescaledb-ha:pg18.1-ts2.23.1-oss")
			.asCompatibleSubstituteFor("postgres");

	static PostgreSQLContainer timescaledb = new PostgreSQLContainer(image).withDatabaseName("integration-tests-db")
			.withUsername("sa").withPassword("sa");

	static {
		timescaledb.start();
	}

	@DynamicPropertySource
	static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
		propertyRegistry.add("spring.datasource.url", timescaledb::getJdbcUrl);
		propertyRegistry.add("spring.datasource.password", timescaledb::getPassword);
		propertyRegistry.add("spring.datasource.username", timescaledb::getUsername);
	}

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues
					.of("spring.datasource.url=" + timescaledb.getJdbcUrl(),
							"spring.datasource.username=" + timescaledb.getUsername(),
							"spring.datasource.password=" + timescaledb.getPassword())
					.applyTo(configurableApplicationContext.getEnvironment());
		}
	}

	/**
	 * <p>
	 * setUp.
	 * </p>
	 */
	@BeforeAll
	void setUp() {

		stationsApiController = new StationsApiController(stationsRepository, parametersRepository);

		timeseriesApiController = new TimeseriesApiController(timeseriesMetadataRepository, timeseriesDataRepository,
				parametersRepository, stationsRepository);

		timeseriesWithParameterNameApiController = new TimeseriesWithParameterNameApiController(
				timeseriesMetadataRepository, timeseriesDataRepository, parametersRepository, stationsRepository);

		mvc = MockMvcBuilders
				.standaloneSetup(stationsApiController, timeseriesApiController,
						timeseriesWithParameterNameApiController)
				.setMessageConverters(CustomMessageConverter()).build();

	}

	/**
	 * <p>
	 * testPostTimeseriesData.
	 * </p>
	 *
	 * @throws java.lang.Exception if any.
	 */
	@Test
	void testPostTimeseriesData() {
		try {
			postTimeseriesData();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * <p>
	 * testPostTimeseriesData.
	 * </p>
	 *
	 * @throws java.lang.Exception if any.
	 */
	@Test
	void testGetAggregate() {
		try {
			postTimeseriesData();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		// TODO test get aggregate
	}

	/**
	 * <p>
	 * testPostTimeseriesDataByName.
	 * </p>
	 *
	 * @throws java.lang.Exception if any.
	 */
	@Test
	void testPostTimeseriesDataByName() {
		try {
			postTimeseriesData();
			String timeseriesEdpoint = String.format(TIMESERIES_DATA_BY_NAME_ENDPOINT_TEMPLATE, STATION_ID);
			ResultActions resultActions = performPostRequest(timeseriesEdpoint, "add-timeseries-data-by-name.json");
			resultActions.andExpect(status().isOk());
//			resultActions = performGetRequest(timeseriesEdpoint);
//			resultActions.andExpect(status().isOk());
//			String content = resultActions.andReturn().getResponse().getContentAsString();
//			assertNotNull(content);
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	private void postTimeseriesData() throws Exception {
		String parameterId = postTimeseriesMetadata();
		assertNotEquals("", parameterId);
		String timeseriesEdpoint = String.format(TIMESERIES_DATA_ENDPOINT_TEMPLATE, STATION_ID, parameterId);
		ResultActions resultActions = performPostRequest(timeseriesEdpoint, "add-timeseries-data.json");
		resultActions.andExpect(status().isOk());
		resultActions = performGetRequest(timeseriesEdpoint);
		resultActions.andExpect(status().isOk());
		String content = resultActions.andReturn().getResponse().getContentAsString();
		assertNotNull(content);
		DocumentContext json = JsonPath.parse(content);
	}

	private String postTimeseriesMetadata() throws Exception {
		postStation();
		ResultActions resultActions = performPostRequest(TIMESERIES_ENDPOINT, "register_timeseries.json");
		resultActions.andExpect(status().isCreated());
		return getId(resultActions);
	}

	private void postStation() throws Exception {
		ResultActions resultActions = performPostRequest(STATIONS_ENDPOINT, "add-station.json");
		resultActions.andExpect(status().isCreated());
		String id = getId(resultActions);
		assertEquals(id, STATION_ID);
	}

	private ResultActions performPostRequest(String endpoint, String requestName) throws Exception {
		URL postFileUrl = getClass().getClassLoader().getResource("requests/" + requestName);
		String content = new String(Files.readAllBytes(Paths.get(postFileUrl.toURI())), Charset.forName("UTF-8"));
		ResultActions resultActions = mvc
				.perform(post(endpoint).content(content).contentType(MediaType.APPLICATION_JSON));
		return resultActions;
	}

	private ResultActions performGetRequest(String endpoint) throws Exception {
		ResultActions resultActions = mvc.perform(get(endpoint).contentType(MediaType.APPLICATION_JSON));
		return resultActions;
	}

	private String getId(ResultActions resultActions) {
		String locationHeaderValue = resultActions.andReturn().getResponse().getHeader("location");
		String id = locationHeaderValue.substring(locationHeaderValue.lastIndexOf("/") + 1);
		return id;
	}

	/**
	 * <p>
	 * CustomMessageConverter.
	 * </p>
	 *
	 * @return a {@link org.springframework.http.converter.HttpMessageConverter}
	 *         object
	 */
	public static HttpMessageConverter<Object> CustomMessageConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JtsModule());
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		MappingJackson2HttpMessageConverter customConverter = new MappingJackson2HttpMessageConverter();
		customConverter.setObjectMapper(objectMapper);
		return customConverter;
	}

}
