package utils;

/**
 * The Class is for Constants.
 *
 * @author Bharathish
 */
public class Constants {
	
	/** The Constant WORKING_DIRECTORY. */
	public static final String WORKING_DIRECTORY = System.getProperty("user.dir");

	/** The Constant REPORT_DIRECTORY. */
	public final static String EXTENT_REPORT_DIRECTORY = WORKING_DIRECTORY + "/Reports/Spark/ExtentSpark.html";
	public final static String LOGS_DIRECTORY = WORKING_DIRECTORY + "/Logs/logs.html";
	public final static String GLOBAL_PROP=WORKING_DIRECTORY +"/src/test/resources/Global.properties";

	/** The Constant PROJECT_NAME. */
	public final static String PROJECT_NAME = "NAGP_RestAssured";

	public final static String LOG_PROPERTIES_PATH = WORKING_DIRECTORY + "/src/test/resources/log4j.properties";
	
	public final static String CONFIG_PROP = WORKING_DIRECTORY + "/src/test/resources/config.properties";

	public final static String USERNAME="admin";
	public final static String PASSWORD="password123";
	}
