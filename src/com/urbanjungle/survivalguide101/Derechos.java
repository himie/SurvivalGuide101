/*
 * American Data Fest 2013
 * SOS Latino California Edition
 * Team: Urban Jungle
 * 
 * Designer: 
 *  Fernanda Gonzalez Jaquez (s.fernandagonzalez@gmail.com)
 *
 *Developers:
 *
 * Luis Fernando Mata Lic�n (wisimata@gmail.com)
 *
 * Jorge Arturo Alejos Loya (A00758689@itesm.mx)
 *
 * Gerardo Anselmo Torres P�rez (chemoatp@gmail.com)
 *
 * David Alejandro Jim�nez Jaramillo (himie.las@gmail.com)
 * 
 */
package com.urbanjungle.survivalguide101;

import com.activeandroid.Model;

import java.util.List;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/*
 * This class makes the object of Derechos (Rights) based on the table Leyes (Law) using the ORM
 */
@Table(name = "Leyes")
public class Derechos extends Model {
	@Column(name = "Id")
	public int id;
	@Column(name = "Nombre")
	public String nombre;
	@Column(name = "Descripcion")
	public String descripcion;

	public Derechos() {
		super();
	}

	public Derechos(int id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public static Derechos getDerechos(String nombre) {
		return new Select().from(Derechos.class).where("Nombre = ?", nombre)
				.executeSingle();
	}

	public static Derechos getById(int id) {
		return new Select().from(Derechos.class).where("Id = ?", id)
				.executeSingle();
	}

	public static List<Derechos> getAll() {
		return new Select().from(Derechos.class).execute();
	}

}