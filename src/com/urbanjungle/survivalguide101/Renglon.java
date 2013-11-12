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


/*
 * Renglon (row) item, used for populate the ListView with a Title and Subtitle
 * This is a simple row with a background image and a title
 */
public class Renglon {
	String titulo;
	String subtitulo;

	public Renglon(String title, String subtitle) {
		titulo = title;
		subtitulo = subtitle;
	}
}
