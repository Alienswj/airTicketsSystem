package cn.cuit.beans;

public class Users {
	private String username,password,userId,accessRight;
	
	public Users(String username, String password, String userId, String accessRight) {
		super();
		this.username = username;
		this.password = password;
		this.userId = userId;
		this.accessRight = accessRight;
	}
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessRight() {
		return accessRight;
	}

	public void setAccessRight(String accessRight) {
		this.accessRight = accessRight;
	}

	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + ", userId=" + userId + ", accessRight="
				+ accessRight + "]";
	}
	
	
}
