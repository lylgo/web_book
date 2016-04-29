package cn.hncj.or.service;

import java.io.ObjectInputStream.GetField;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.hncj.or.model.User;

public class UserService {
	public static String reguservice(String name, String pass, String email) {
		Record red = Db.findById("t_user", "email", email);
		if (red != null) {
			return "XM";
		} else {
			User user = new User();
			user.set("name", name);
			user.set("password", pass);
			user.set("email", email);
			boolean flag = user.save();
			if (flag) {
				return "SU";
			} else {
				return "ER";
			}
		}
	}

	public static String  loginService(String email,String pass){
		Record red = Db.findById("t_user", "email", email);
		if(red!=null){
			if(red.get("password").equals(pass)){
				return "SU#"+red.getStr("name");
			}else{
				return "ERPASS";
			}
		}else{
			return "REG";
		}
	}
	
}
