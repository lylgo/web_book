package cn.hncj.or.model;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> {
	/**
	 * userImage,name,password
	 */
	private static final long serialVersionUID = 1L;
	public  static User dao = new User();

	public String getUserImage(String userImage) {
		return getStr("userImage");
	}

	public User setUserImage(String userImage) {
		return set("userName", userImage);
	}

	public String getName(String name) {
		return getStr("name");
	}

	public User setName(String name) {
		return set("name", name);
	}

	public String getPassWord(String password) {
		return getStr("password");
	}

	public User setPassWord(String password) {
		return set("password", password);
	}

	public User setEmail(String email) {
		return set("email", email);
	}

	public String getEmail(String email) {
		return getStr("email");
	}

}
