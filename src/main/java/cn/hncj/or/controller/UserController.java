package cn.hncj.or.controller;

import java.awt.Point;
import java.io.File;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;

import cn.hncj.or.handler.StringHandler;
import cn.hncj.or.service.UserService;

public class UserController extends Controller {
	@Before(POST.class)
	public void reguser() {
		String name = getPara("name");
		String pass = getPara("pass");
		String email = getPara("email");
		if (!StringHandler.isEmpty(name) && !StringHandler.isEmpty("pass") && !StringHandler.isEmpty("email")) {
			if (!StringHandler.isEmpty("userImage")) {
				String msg = UserService.reguservice(name, pass, email);
				renderText(msg);
			}
		}
	}

	@Before(POST.class)
	public void login() {
		String pass = getPara("pass");
		String email = getPara("email");
		if (!StringHandler.isEmpty(pass) && !StringHandler.isEmpty(email)) {
			String msg = UserService.loginService(email, pass);
			renderText(msg);
		} else {
			renderText("数据异常,重新登录");
		}
	}

	@Before(POST.class)
	public void upImage() {
		UploadFile upImage = getFile("file");
		String email = getPara("email");
		// File file=new File("H:/Db_Image");
		String sql = "UPDATE t_user set userImage=? where email=" + "'" + email + "'";
		Db.update(sql, upImage.getFileName());
		renderText("S");
	}

	public void downImage() {
		String email = getPara("email");
		String sql = "select * from t_user where email=?";
		Record cord = Db.findFirst(sql, email);
		renderFile(cord.getStr("userImage"));
	}

	@Before(POST.class)
	public void getUserMsg() {
		String email = getPara("email");
		String sql = "select name ,password from t_user where email=?";
		Record cord = Db.findFirst(sql, email);
		if (cord != null) {
			renderText(cord.getStr("name") + "#" + cord.getStr("password"));
		} else {
			renderText("E");
		}
	}

	@Before(POST.class)
	public void upUserMsg() {
		String email = getPara("email");
		String num = getPara("num");
		String type = getPara("type");
		int flag;
		if (type.equals("n")) {
			String sql = "UPDATE t_user set name=? where email=" + "'" + email + "'";
			flag = Db.update(sql, num);
		} else {
			String sql = "UPDATE t_user set password=? where email=" + "'" + email + "'";
			flag = Db.update(sql, num);
		}
		if (flag != 0) {
			renderText("S");
		} else {
			renderText("E");
		}
	}
}
