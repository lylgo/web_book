package cn.hncj.or.common;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import cn.hncj.or.model.Book;
import cn.hncj.or.model.Collection;
import cn.hncj.or.model.Contact;
import cn.hncj.or.model.Image;
import cn.hncj.or.model.User;
/**
 * 数据库映射
 * @author Administrator
 */
public class MappingKit {
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("t_image", Image.class);
		arp.addMapping("t_user", User.class);
		arp.addMapping("t_book", Book.class);
		arp.addMapping("t_collection", Collection.class);
		arp.addMapping("t_contact", Contact.class);
	}
}

