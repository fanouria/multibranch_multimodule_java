package gr.codelearn.core.showcase.database;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.h2.message.DbException;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.exit;
import static org.h2.tools.Server.startWebServer;

public class DatabaseClassicConnection {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseClassicConnection.class);
	private static final Lorem generator = LoremIpsum.getInstance();

	private static final String DB_CONNECTION_URL_FILE_MODE = "jdbc:h2:~/sample";
	private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:sample";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";
	private final Properties sqlCommands = new Properties();
	private Server server;

	public static void main(String[] args) {
		if (args.length == 0) {
			logger.debug("No arguments passed.");
		}

		DatabaseClassicConnection demo = new DatabaseClassicConnection();
		demo.loadSqlCommands();
		demo.startH2Server();
		demo.loadDatabaseDriver();

		// We need to share the connection otherwise we want be able to see memory databases. Following a different
		// scenario, we will need to utilize file mode(store db files in a specific file location.
		Connection connection = demo.getConnection();

		demo.createTable(connection);
		demo.insertData(connection);

		// Commit transaction
		demo.commitData(connection);

		demo.insertGeneratedData(connection, 20);
		demo.readData(connection);

		demo.rollbackData(connection);

		demo.insertGeneratedData(connection, 30);
		demo.readData(connection);
		demo.updateData(connection);
		demo.readData(connection);
		demo.deleteData(connection);
		demo.readData(connection);

		demo.stopH2Server();
	}

	private void loadSqlCommands() {
		try (InputStream inputStream = DatabaseClassicConnection.class.getClassLoader()
				.getResourceAsStream("sql.properties")) {
			if (inputStream == null) {
				logger.error("Sorry, unable to find sql.properties, exiting application.");
				// Abnormal exit
				exit(-1);
			}

			//load a properties file from class path, inside static method
			sqlCommands.load(inputStream);
		} catch (IOException ex) {
			logger.error("Sorry, unable to parse sql.properties, exiting application.", ex);
			// Abnormal exit
			exit(-1);
		}
	}

	private void createTable(Connection connection) {
		try (Statement statement = connection.createStatement()) {
			int result = statement.executeUpdate(sqlCommands.getProperty("create.table"));
			logger.info("Created table command was successful with result {}.", result);
		} catch (SQLException ex) {
			logger.error("Error while creating table(s).", ex);
			exit(-1);
		}
	}

	private void insertData(Connection connection) {
		try (Statement statement = connection.createStatement()) {

			//@formatter:off
			// Classic insert statements
			runInsertCommands(statement,
							  sqlCommands.getProperty("insert.table.001"),
							  sqlCommands.getProperty("insert.table.002"),
							  sqlCommands.getProperty("insert.table.003"),
							  sqlCommands.getProperty("insert.table.004"));
			//@formatter:on

			// Enable transaction support
			connection.setAutoCommit(false);

			// Insert data in batch mode
			statement.addBatch(sqlCommands.getProperty("insert.table.005"));
			statement.addBatch(sqlCommands.getProperty("insert.table.006"));
			statement.addBatch(sqlCommands.getProperty("insert.table.007"));
			statement.addBatch(sqlCommands.getProperty("insert.table.008"));
			statement.addBatch(sqlCommands.getProperty("insert.table.009"));
			statement.addBatch(sqlCommands.getProperty("insert.table.010"));

			int[] rowsAffectedArray = statement.executeBatch();
			logger.info("Insert commands were successful with {} row(s) affected.",
						Arrays.stream(rowsAffectedArray).summaryStatistics().getSum());
		} catch (SQLException ex) {
			logger.error("Error while inserting data.", ex);
		}
	}

	private void runInsertCommands(Statement statement, String... commands) throws SQLException {
		for (String command : commands) {
			logger.info("Insert command was successful with {} row(s) affected.", statement.executeUpdate(command));
		}
	}

	private void insertGeneratedData(Connection connection, int howManyStatements) {
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(sqlCommands.getProperty("insert.table.000"))) {

			int maximumIdValue = findMaximumIdValue(connection);
			batchInsert(preparedStatement, howManyStatements, maximumIdValue);

			int[] rowsAffectedArray = preparedStatement.executeBatch();
			logger.info("Insert batch command were successful with {} row(s) affected.",
						Arrays.stream(rowsAffectedArray).summaryStatistics().getSum());

		} catch (SQLException ex) {
			logger.error("Error while inserting data.", ex);
		}
	}

	private void batchInsert(PreparedStatement preparedStatement, int howManyStatements, int maximumIdValue)
			throws SQLException {
		for (int i = 1; i <= howManyStatements; i++) {
			// Clear parameters from the statement so it can be reused
			preparedStatement.clearParameters();

			// 111 is hardcoded as we expect the maximum value of id column to be 110, according to our hypothesis scenario
			preparedStatement.setInt(1, maximumIdValue + i);
			preparedStatement.setString(2, generator.getFirstName());
			preparedStatement.setString(3, generator.getLastName());
			preparedStatement.setInt(4, ThreadLocalRandom.current().nextInt(18, 70));

			// Add command to batch
			preparedStatement.addBatch();
		}
	}

	private void updateData(Connection connection) {
		try (Statement statement = connection.createStatement()) {
			int rowsAffected = statement.executeUpdate("UPDATE Registration SET age = age + 10 WHERE id > 130");

			logger.info("Update command was successful with {} row(s) affected.", rowsAffected);
		} catch (SQLException ex) {
			logger.error("Error while updating data.", ex);
		}
	}

	private void deleteData(Connection connection) {
		try (Statement statement = connection.createStatement()) {
			int rowsAffected = statement.executeUpdate("DELETE FROM Registration WHERE id > 120");

			logger.info("Delete command was successful with {} row(s) affected.", rowsAffected);
		} catch (SQLException ex) {
			logger.error("Error while updating data.", ex);
		}
	}

	private void readData(Connection connection) {
		try (Statement statement = connection
				.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			 ResultSet resultSet = statement.executeQuery(sqlCommands.getProperty("select.table.001"))) {

			logger.info("---------------------------------------------------------------------");
			//@formatter:off
			int rowCount =1;
			while (resultSet.next()) {
				logger.info("{}. {}:{}, {}:{}, {}:{}, {}:{}", rowCount++,
							resultSet.getMetaData().getColumnName(1), resultSet.getString(1),
							resultSet.getMetaData().getColumnName(2), resultSet.getString(2),
							resultSet.getMetaData().getColumnName(3), resultSet.getString(3),
							resultSet.getMetaData().getColumnName(4), resultSet.getString(4));
			}
			//@formatter:on
			logger.info("---------------------------------------------------------------------");

		} catch (SQLException ex) {
			logger.error("Error while reading data.", ex);
		}
	}

	private int findMaximumIdValue(Connection connection) {
		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(sqlCommands.getProperty("select.table.002"))) {

			if (resultSet.isBeforeFirst()) {
				resultSet.next();

				logger.info("---------------------------------------------------------------------");
				logger.info("Maximum id value found is {}.", resultSet.getInt(1));
				logger.info("---------------------------------------------------------------------");

				return resultSet.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("Error while reading data.", ex);
		}

		return 0;
	}

	private void rollbackData(Connection connection) {
		try {
			// Rollback transaction
			connection.rollback();
			logger.debug("---------------------------------------------------------------------");
			logger.debug("Transaction was rolled back.");
			logger.debug("---------------------------------------------------------------------");
		} catch (SQLException ex) {
			logger.warn("Unable to rollback transaction.");
		}
	}

	private void commitData(Connection connection) {
		try {
			// Commit transaction
			connection.commit();
			logger.debug("---------------------------------------------------------------------");
			logger.debug("Transaction was committed.");
			logger.debug("---------------------------------------------------------------------");
		} catch (SQLException ex) {
			logger.warn("Unable to commit transaction.");
		}
	}

	private void startWebClient(Connection connection) {
		// Get connection
		try {
			// Start web client
			startWebServer(connection);
		} catch (SQLException ex) {
			logger.warn("Error while starting web client.", ex);
		}
	}

	private Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException ex) {
			logger.error("Error while retrieving database connection.", ex);
			exit(-1);
		}
		return connection;
	}

	private void startH2Server() {
		try {
			server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
			server.start();
		} catch (SQLException e) {
			//
			logger.error("Error while starting H2 server.", DbException.convert(e));
			exit(-1);
		}
		logger.info("H2 server has started with status '{}'.", server.getStatus());
	}

	private void stopH2Server() {
		if (server == null) {
			return;
		}
		if (server.isRunning(true)) {
			server.stop();
			server.shutdown();
			logger.info("H2 server has been shutdown.");
		}
		server = null;
	}

	private void loadDatabaseDriver() {
		org.h2.Driver.load();

		// Traditional way of loading database driver
		// H2 driver from http://www.h2database.com
		/*
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
		logger.info("H2 JDBC driver server has been successfully loaded.");
	}
}
