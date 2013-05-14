package com.tengen;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkFreemarkerStyle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");
		
		
		Spark.get(new Route("/"){

			@Override
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				
				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");
					
					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "Freemarker");
					
					helloTemplate.process(helloMap, writer);
					
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
