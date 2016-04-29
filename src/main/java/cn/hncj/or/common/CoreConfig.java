package cn.hncj.or.common;
import java.util.Properties;

import org.eclipse.jetty.server.Authentication.User;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PathKit;
import com.jfinal.log.Log4jLogFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;

import cn.hncj.or.controller.BaseController;
import cn.hncj.or.controller.BookController;
import cn.hncj.or.controller.UserController;
import cn.hncj.or.plugin.Getc3p0plugin;
/**
 * API引导式配置
 */
@SuppressWarnings("unused")
public class CoreConfig extends JFinalConfig {
	private Properties config = loadPropertyFile("db.properties", "utf8");
	private Properties system=loadPropertyFile("system.properties","utf-8");
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setBaseViewPath(system.getProperty("BaseViewPath"));//设置view基础路径
		//me.setBaseUploadPath(system.getProperty("iMagePath"));
		me.setLogFactory(new Log4jLogFactory());
		me.setViewType(ViewType.FREE_MARKER);
		me.setBaseUploadPath(system.getProperty("iMagePath"));
		me.setBaseDownloadPath(system.getProperty("iMagePath"));
	}
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", UserController.class);
		me.add("/book", BookController.class,"/");
        me.add("/base",BaseController.class);
	}
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin C3p0Plugin = Getc3p0plugin.createC3p0Plugin(config.getProperty("jdbcUrl"),
				config.getProperty("user"), config.getProperty("password").trim());
		me.add(C3p0Plugin);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		me.add(arp);
		// ehcache
		me.add(new EhCachePlugin(PathKit.getRootClassPath() + "/ehcache.xml"));
		// 所有配置在 MappingKit 中搞定
		MappingKit.mapping(arp);
	}
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
	}
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
	}
}
