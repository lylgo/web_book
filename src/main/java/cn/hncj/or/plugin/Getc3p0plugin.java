package cn.hncj.or.plugin;

import com.jfinal.plugin.c3p0.C3p0Plugin;
public class Getc3p0plugin {
	public static C3p0Plugin createC3p0Plugin(String jdbcUrl,String user,String password) {
		return new C3p0Plugin(jdbcUrl,user, password);
	}
}
