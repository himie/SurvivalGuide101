package com.urbanjungle.survivalguide101;

import com.activeandroid.Model;

import java.util.List;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;


@Table(name = "Traducciones")
public class Traduccion extends Model {
	@Column(name = "Id")
	public int id;
	@Column(name = "Ingles")
	public String ingles;
	@Column(name = "Espanol")
	public String espanol;

        public Traduccion(){
                super();
        }
        public Traduccion(int id, String ingles, String espanol){
            super();
            this.id = id;
            this.ingles = ingles;
            this.espanol = espanol;
    }
        
        public static List<Traduccion> getAll() {
        	return new Select()
        		.from(Traduccion.class)
        		.execute();
        }
        
}