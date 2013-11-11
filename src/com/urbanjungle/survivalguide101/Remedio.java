package com.urbanjungle.survivalguide101;

import com.activeandroid.Model;

import java.util.List;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;


@Table(name = "Remedios")
public class Remedio extends Model {
	@Column(name = "Id")
	public int id;
	@Column(name = "Nombre")
	public String nombre;
	@Column(name = "Descripcion")
	public String descripcion;

        public Remedio(){
                super();
        }
        public Remedio(int id, String nombre, String descripcion){
            super();
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
    }
        
        public static Remedio getRemedio(String nombre){
        	return new Select().from(Remedio.class).where("Nombre = ?", nombre).executeSingle();
        }
        
        public static Remedio getById(int id){
        	return new Select().from(Remedio.class).where("Id = ?",id).executeSingle();
        }
        
        public static List<Remedio> getAll() {
        	return new Select()
        		.from(Remedio.class)
        		.execute();
        }
        
}