package cn.hncj.or.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * userImage bookImage
 * @author Administrator
 */
public class Contact extends Model<Contact> {
	private static final long serialVersionUID = 1L;
	private static Contact dao = new Contact();
	public String getContact(String contact) {
		return getStr("contact");
	}
	public Contact setContact(String contact) {
		return set("contact", contact);
	}
	public String getAddress(String address) {
		return getStr("address");
	}
	public Contact setAddress(String address) {
		return set("address", address);			
	}
}
