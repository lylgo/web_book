package cn.hncj.or.model;
import com.jfinal.plugin.activerecord.Model;
public class Collection extends Model<Collection> {
	private static final long serialVersionUID = 1L;
	public static Collection dao = new Collection();
	public String getBookID() {
		return getStr("bookid");
	}
	public Collection setBookID(String bookid) {
		return set("bookid", bookid);
	}
	public String getUserID() {
		return getStr("uid");
	}
	public Collection setUserID(String uid) {
		return set("uid", uid);
	}
}
