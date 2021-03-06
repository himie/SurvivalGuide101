/*
 * American Data Fest 2013
 * ITESM Chihuahua
 * Chihuahua, Chihuahua Mexico
 * November 2013
 * SOS Latino California Edition
 * Team: Urban Jungle
 * 
 */
package com.urbanjungle.survivalguide101;

import com.activeandroid.Model;

import java.util.List;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/*
 * In order to use and have access to SQLite Databases we used an ORM called ActiveAndroid
 * This ORM lets us use the data on the databases just like if they were objects 
 * of Java.
 * 
 * The object of Bookmark, has:
 * Id_bookmark.- The id of the object that has been bookmarked
 * Id_table.- The table which the bookmark comes from
 * 
 * This class makes the object of Bookmarks, from the table of the same name using the ORM
 */
@Table(name = "Bookmarks")
public class Bookmark extends Model {
	@Column(name = "Id")
	public int id;
	@Column(name = "Id_bookmark")
	public int id_bookmark;
	@Column(name = "Id_tabla")
	public int id_tabla;

	public Bookmark() {
		super();
	}

	public Bookmark(int id, int id_bookmark, int id_tabla) {
		super();
		this.id = id;
		this.id_bookmark = id_bookmark;
		this.id_tabla = id_tabla;
	}

	public static Bookmark getBookmark(int id_bookmark, int id_tabla) {
		return new Select()
				.from(Bookmark.class)
				.where("id_bookmark = ? AND id_tabla = ?", id_bookmark,
						id_tabla).executeSingle();
	}

	public static List<Bookmark> getAll() {
		return new Select().from(Bookmark.class).execute();
	}

}