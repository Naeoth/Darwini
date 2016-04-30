/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class Database.java
 */

package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import robocode.RobocodeFileWriter;

/**
*
*
* @version 1.0 - 17/11/15
* @author BOIZUMAULT Romain
* @author BUSSENEAU Alexis
* @author GEFFRAULT Luc
* @author MATHIEU Vianney
* @author VAILLAND Guillaume
*/
public class Database {
	
	/*	----- ATTRIBUTE -----	*/
	
		/**
		 *
		 */
		private ArrayList<InputData> dataList;
	
	
	/*	----- CONSTRUCTOR -----	*/
	
		/**
		 *
		 */
		public Database() {
			dataList = new ArrayList<>();
		}
	
	
	/*	----- ACCESSORS -----	*/
		
		/**
		 * 
		 * 
		 * @return a
		 */
		public InputData getLastData() {
			return dataList.get(dataList.size() - 1);
		}
	
	
	/*	----- MUTATORS -----	*/
	
		/**
		 * Insert the specified data in the database.
		 *
		 * @param obj the data to insert
		 *
		 * @return true if the data has been inserted, false otherwise
		 *
		 * @throws NullPointerException if obj is null
		 */
		public boolean insert(InputData obj) throws NullPointerException {
			if (obj == null)
				throw new NullPointerException("The data specified is empty.");
	
			return dataList.add(obj);
		}
	
		/**
		 *
		 *
		 * @param f a
		 *
		 * @throws IOException
		 */
		public void printToSSVM(File f) throws IOException {
			RobocodeFileWriter w = new RobocodeFileWriter(f.getAbsolutePath(), true);
			String s = "";
		
			for (InputData data : dataList)
				s += data.toSSVM() + "\n";
	
			w.write(s);
			w.close();
		}
		
}