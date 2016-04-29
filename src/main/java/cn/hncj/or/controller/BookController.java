package cn.hncj.or.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;

import cn.hncj.or.model.Book;
import cn.hncj.or.model.Collection;

public class BookController extends Controller {
	@Before(POST.class)
	public void bookList() {
		UploadFile file = getFile("file");
		String name = file.getFileName();
		Record book = new Record();
		book.set("bookName", file.getFileName().split(".")[0]);
		book.set("bookpath", file.getFileName());
		book.set("bookType", "1");
		Db.save("t_book", "bookId", book);
	}

	public void setbook() {
		render("login.html");
	}

	@Before(POST.class)
	public void getListBook() {
		Integer num = getParaToInt("num");
		Integer type = getParaToInt("type");
		String sql = "SELECT * FROM t_book  where bookPower=" + type;
		Page<Book> page = Book.dao.paginate(num, 100, sql);
		renderJson(page.getList());
	}

	@Before(POST.class)
	public void getpagenumber() {
		String sql = "SELECT * FROM t_book";
		Page<Book> page = Book.dao.paginate(1, 10, sql);
		renderText(page.getTotalPage() + "");
	}

	public void downbook() {
		String bookId = getPara("bookId");
		String sql = "select * from t_book where bookId=?";
		Record book = Db.findFirst(sql, bookId);
		renderFile(book.getStr("bookpath"));
	}

	@Before(POST.class)
	public void collecbook() {
		String uid = getPara("num");
		String bookid = getPara("type");
		Collection collection = new Collection();
		collection.set("uid", uid);
		collection.set("bookid", bookid);
		String sqlstr = "select * from  t_collection where uid =? and bookid=?";
		List<Record> list=Db.find(sqlstr, uid,bookid);
		if(list.size()>0){
			renderText("S");
		}else{
			boolean flag = collection.save();
			if (flag) {
				renderText("S");
			} else {
				renderText("E");
			}
		}
	}

	@Before(POST.class)
	public void collecbooklist() {
		String sql = "SELECT t_book.* FROM t_book INNER JOIN t_collection on t_book.bookId=t_collection.bookid  WHERE t_collection.uid=?";
		String uid = getPara("num");
		List<Record> records = Db.find(sql, uid);
		renderJson(records);
	}

	@Before(POST.class)
	public void delcobooklist() {
		String uid = getPara("num");
		String bookid = getPara("num1");
		String sql = "delete from t_collection where uid = ? and bookid=?";
		int flag = Db.update(sql, uid, bookid);
		if (flag == 1) {
			renderText("S");
		} else {
			renderText("E");
		}

	}
	@Before(POST.class)
	public void getimge(){
		renderFile("image.png");
	}
}
