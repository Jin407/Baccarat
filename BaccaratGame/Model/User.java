package Model;

abstract public class User {

	private String loginName;
	
	public User(String loginName, String password) {
		this.loginName = loginName;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
