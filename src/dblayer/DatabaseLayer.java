package dblayer;

public class DatabaseLayer {	
	public enum DBType {
		MySQL, PostgreSQL, MongoDB
	}	
	
	public DatabaseLayer() {}
	
	public static DBHandler getDBHandler(DBType dbtype, String driver, String url, String username, String password) throws Exception {
		DBHandler dbhandler;
		
		switch (dbtype) {
		case MySQL:
			dbhandler = new MySQLHandler(driver, url, username, password);
			break;
		case PostgreSQL:
			System.out.println("PostgreHandler");
		case MongoDB:
			System.out.println("MongoDBHandler");
		default:
			System.out.println("Unsupported database type");
			dbhandler = null;
			System.exit(0);
		}	
	
		return dbhandler;
	}
}