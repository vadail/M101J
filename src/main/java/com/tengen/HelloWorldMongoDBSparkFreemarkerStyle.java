package com.tengen;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldMongoDBSparkFreemarkerStyle {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldMongoDBSparkFreemarkerStyle.class, "/");
		
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
		
		DB database = client.getDB("course");
		final DBCollection collection = database.getCollection("hello");
				
		Spark.get(new Route("/"){

			@Override
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				
				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");
					
					DBObject document = collection.findOne();
					
					helloTemplate.process(document, writer);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					halt(500);
					e.printStackTrace();
				}
				return writer; 
			}
			
			
		});
	}

}
