package com.db.crud;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

import com.db.crud.anno.Collection;
import com.mongodb.client.MongoDatabase;

/**
 * @author qijunbo
 * 
 *         In a lot of OR mapping tool, values are populated into a POJO, and
 *         then storied into data base, but this class is different. In this
 *         class, POJO class is only used as a template, we get value for each
 *         field defined in template(parameter template) from the map (
 *         parameter values) to form a instance of org.bson.Document.
 * 
 */

public class NotyetHaveName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private MongoDatabase database;

	public NotyetHaveName() {
	}

	public NotyetHaveName(MongoDatabase database) {
		this.database = database;
	}

	public MongoDatabase getDatabase() {

		return database;
	}

	private Document getFieldDocument(Class<?> template, Map<String, ?> values) {

		Document doc = new Document();

		for (Field f : template.getDeclaredFields()) {

			// TODO if the type of f is wrapper of primitive, things will become
			// tricky.
			if (f.getType().isPrimitive() || isPrimitiveWrapper(f.getType())) {
				doc.append(f.getName(), getFieldValue(f, values));
			} else {
				doc.append(f.getName(), getFieldDocument(f.getType(), values));
			}
		}
		return doc;
	}

	private Object getFieldValue(Field field, Map<String, ?> values) {

		return values.get(field.getName());

		// TODO get Annotation of this field, if this field is required, then
		// throw a FieldMissingException.
		// TODO get Annotation of this field, if this fired is @id, then create
		// a ObjectId align with MongoDB
	}

	/**
	 * Get value of field defined in template to form a org.bson.Document, and
	 * save it to the default Mongodb.
	 * 
	 * @param template
	 *            a POJO Class. used to define the domain model.
	 * @param values
	 *            a map contain all the values of domain model, usually a Map
	 *            from the HttpServletRequest. use the method
	 *            HttpServletRequest.getParameterMap()
	 */
	public void insertOne(Class<?> template, Map<String, ?> values) {

		Collection coll = template.getAnnotation(Collection.class);

		String collection = coll == null ? template.getSimpleName() : (coll
				.name() == null ? template.getSimpleName() : coll.name());

		Document doc = getFieldDocument(template, values);
		// TODO get Annotation of this field , if @Reference, then save this
		// field as a reference to another document.
		if (doc.isEmpty()) {
			throw new NullDocumentException();
		} else {
			getDatabase().getCollection(collection).insertOne(doc);
		}
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}

	public static boolean isPrimitiveWrapper(Class<?> clazz) {
		return BSONType.indexOf(clazz) > 0;
	}

	private static final List<?> BSONType = Arrays.asList(Boolean.class,
			Character.class, Byte.class, Short.class, Integer.class,
			Long.class, Binary.class, ObjectId.class, Float.class,
			Double.class, Void.class, String.class, List.class, Date.class);

}
