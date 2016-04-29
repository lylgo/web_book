package cn.hncj.or.controller;

import java.io.File;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.hncj.or.model.Contact;

public class BaseController extends  Controller{
	@Before(POST.class)
	public void getvision() {
		String sql = "select vision, desce from update_apk";
		Record map = Db.findFirst(sql);
		if (map != null) {
			renderJson(map);
		} else {
			renderText("N");
		}
	}
	public void downapk() {
		File file = new File("H:/Db_Image/updatevision");
		File[] filename = file.listFiles();
		if(filename.length>0){
			renderFile(new File(filename[0].getPath()));
		}
	}
	@Before(POST.class)
	public void contact(){
		String contact=getPara("cont");
		String address=getPara("add");
		Contact con=new Contact();
		con.set("contact", contact);
		con.set("address", address);
		boolean flag=con.save();
		if(flag){
			renderText("S");
		}else{
			renderText("E");
		}
	}
}
