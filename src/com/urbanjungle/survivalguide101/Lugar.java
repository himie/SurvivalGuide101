/*
 * American Data Fest 2013
 * ITESM Chihuahua
 * Chihuahua, Chihuahua Mexico
 * November 2013
 * SOS Latino California Edition
 * Team: Urban Jungle
 * 
 * Designer: 
 *  Fernanda Gonzalez Jaquez (s.fernandagonzalez@gmail.com)
 *
 *Developers:
 *
 * Luis Fernando Mata Licón (wisimata@gmail.com)
 *
 * Jorge Arturo Alejos Loya (A00758689@itesm.mx)
 *
 * Gerardo Anselmo Torres Pérez (chemoatp@gmail.com)
 *
 * David Alejandro Jiménez Jaramillo (himie.las@gmail.com)
 * 
 */
package com.urbanjungle.survivalguide101;

import com.activeandroid.Model;

import java.util.List;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
/* 
 * This class makes the object of Lugares (Places), 
 * from the table of the same name using the ORM.
 * 
 * A lugar (place) its defined as a physical place and it
 * can be of three kinds:
 * Hospital
 * NGO
 * Consulate
 * 
 * A Lugar has this parameters:
 * Nombre (Name)
 * Direccion (Address)
 * Tel (Telephone)
 * Descripcion (Description).- This defines a detailed description about the, place,
 * it depends of the kind of place:
 * 		Hospitals.- Hospital specialty
 * 		NGOs.- What kind of services it provides
 * 		Consulates.- Null object
 * Tipo (Kind).- It depends of the type of place:
 * 			0 Hospitals
 * 			1 NGO
 * 			4 Consulates
 * URL.- If available
 * Ciudad (City) .- Specifies the city of the place
 * 
 * 
 */

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