/**
 * Account class used to hold certain data for a user.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since Nov 22, 2018
 */
public class Account {
	/**
	 * username for account
	 */
	private String username;
	/**
	 * password for account
	 */
	private String password;
	/**
	 * account type - B for registered buyer
	 * 				- A for author
	 * 				- M for manager
	 * 				- O for operator
	 * 				- S for system admin
	 */
	private String type;
	
	/**
	 * Constructor for account
	 * @param u username
	 * @param p password
	 * @param t type
	 */
	public Account(String u, String p, String t)
	{
		setUsername(u);
		setPassword(p);
		setType(t);
	}
	
	//getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
