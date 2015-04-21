package com.db.crud;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.db.model.Item;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

/**
 * Java MongoDB : Convert JSON data to DBObject
 * 
 */

public class Example {

	public static void main(String[] args) {

		try {

			MongoClient client = new MongoClient();
			MongoDatabase database = client.getDatabase("test");
			NotyetHaveName notyet = new NotyetHaveName(database);
			Map values = new HashMap();
			values.put("start", new Date());
			values.put("duration", 12);
			values.put("breakout", 12.47);
			values.put("topic", "sdfd");
			 
			notyet.insertOne(Item.class, values);
			
			
			Document doc = new Document("name", "MongoDB")
            .append("type", "database")
            .append("count", 1)
            .append("info", new Document("x", 203).append("y", 102));
			System.out.println(doc.toJson());
			
			
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
}
