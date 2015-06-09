package com.cgk.game;

import com.mongodb.DBObject;

public interface DatabaseObject {

	/**
	 * returns a DBObject representative of the object
	 * 
	 * @return
	 */
	public DBObject getDBObject();

	/**
	 * sets the objects attributes according to the inputed dbObject
	 * 
	 * @param dbObject
	 */
	public void fromDBObject(DBObject dbObject);

}
