package br.com.torres.ddlGenerator.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

	private static DataSource instance;
	private static Connection connection;

	private DataSource() {

	}

	public static synchronized Connection get() {
		try {
			if (instance == null) {
				connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=FRT_HMG_MANAGER_SANTANDER;user=sa;password=fromtis!Q@W#E");
				instance = new DataSource();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
