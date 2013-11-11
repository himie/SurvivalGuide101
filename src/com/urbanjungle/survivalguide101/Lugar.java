package com.urbanjungle.survivalguide101;

import com.activeandroid.Model;

import java.util.List;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;


@Table(name = "Lugares")
public class Lugar extends Model {
	@Column(name = "Id")
	public int id;
	@Column(name = "Nombre")
	public String nombre;
	@Column(name = "Direccion")
	public String direccion;
	@Column(name = "Tel")
	public String tel;
	@Column(name = "Descripcion")
	public String descripcion;
	@Column(name = "Tipo")
	public int tipo;
	@Column(name = "URL")
	public String url;
	@Column(name = "Ciudad")
	public String ciudad;

        public Lugar(){
                super();
        }
        
        public Lugar(int id, String nombre, String direccion, String tel, String descripcion, int tipo, String url, String ciudad){
            super();
            this.id = id;
            this.nombre = nombre;
            this.direccion = direccion;
            this.tel = tel;
            this.descripcion = descripcion;
            this.tipo = tipo;
            this.url = url;
            this.ciudad = ciudad;
    }
        
        public static Lugar getLugar(String nombre, String ciudad){
        	return new Select().from(Lugar.class).where("Nombre = ? AND Ciudad = ?",nombre, ciudad).executeSingle();
        }
        
        public static Lugar getById(int id){
        	return new Select().from(Lugar.class).where("Id = ?",id).executeSingle();
        }
        
        public static List<Lugar> getAll(String tipo, String ciudad) {
        	return new Select()
        		.from(Lugar.class)
        		.where("Tipo = ? AND Ciudad = ?", tipo, ciudad)
        		.execute();
        }
        
}