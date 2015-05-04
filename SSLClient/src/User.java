public class User {
	String username, password;
	DB db;

	User() {
		db = new DB();
		db.connect();
	}

	public boolean validateUser(String username, String password) {

	
		if (db.executeScalar("select count(*) from userinfo where user_name='"
				+ username + "'and user_password= '" + password + "';")) {
			return true;
		}
		return false;
	}

	public int getUserId(String username, String password) {
		return db.executeScalar(
				"select user_id from userinfo where user_name='" + username
						+ "'and user_password= '" + password + "';", 1);

	}
}
