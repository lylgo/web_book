package cn.hncj.or.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * userImage bookImage
 * @author Administrator
 */
public class Image extends Model<Image> {
	private static final long serialVersionUID = 1L;
	private static Image dao = new Image();
	public String getUserImage(String userImage) {
		return getStr("userImage");
	}
	public Image setUserImage(String userImage) {
		return set("userImage", userImage);
	}
	public String getBookImage(String bookImage) {
		return getStr("bookImage");
	}
	public Image setBookImage(String bookImage) {
		return set("bookImage", bookImage);			
	}
}
