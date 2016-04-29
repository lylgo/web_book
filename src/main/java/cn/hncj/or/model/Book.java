package cn.hncj.or.model;

import com.jfinal.plugin.activerecord.Model;
public class Book extends Model<Book>  {
	/**
	 * bookImage,bookName,bookBody,bookType,bookPower ,describe
	 */
	private static final long serialVersionUID = 1L;
	public static Book dao = new Book();
	public String getBookImage() {
		return getStr("bookImage");
	}
	public Book setBookImage(String bookImage) {
		return set("bookImage", bookImage);
	}
	public String getBookDescribe() {
		return getStr("describe");
	}
	public Book setBookDescribe(String describe) {
		return set("describe", describe);
	}
	public String getBookName() {
		return getStr("bookName");
	}
	public Book setBookName(String bookName) {
		return set("bookName", bookName);
	}
	public String getBookBody() {
		return getStr("bookpath");
	}
	public Book setBookBody(String bookpath) {
		return set("bookpath", bookpath);
	}
	public String getBookType() {
		return getStr("bookType");
	}
	public Book setBookType(String bookType) {
		return set("bookType", bookType);
	}
	public String getBookPower() {
		return getStr("bookPower");
	}
	public Book setBookPower(String bookPower) {
		return set("bookPower", bookPower);
	}
}