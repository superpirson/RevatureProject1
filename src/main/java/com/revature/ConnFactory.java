package com.revature;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Properties;

import oracle.jdbc.proxy.annotation.GetProxy;

public class ConnFactory{
	public static Hashtable<String, String> nameMapsToSQL= new Hashtable<String, String>();
	public static  Hashtable<String, String> nameMapsInverse= new Hashtable<String, String>();

	static {
		nameMapsToSQL.put("first-name", "fName");
		nameMapsToSQL.put("date", "date_completed");
		nameMapsToSQL.put("cost", "cost");
		nameMapsToSQL.put("reason", "reason");
		nameMapsToSQL.put("location", "location");
		nameMapsToSQL.put("description", "description");
		nameMapsToSQL.put("last-name", "lName");
		nameMapsToSQL.put("grading-format", "grade");
		nameMapsToSQL.put("event-type", "");
		nameMapsToSQL.put("reason", "reason");
		nameMapsToSQL.put("reason", "reason");
		nameMapsToSQL.put("reason", "reason");
		//Build the inverse
		for(Entry<String, String> e:nameMapsToSQL.entrySet()) {
			nameMapsInverse.put(e.getValue(), e.getKey());
		}
	}
	private static ConnFactory cf = new ConnFactory();
	private static Connection con = null;
	public static CallableStatement myCall = null;
	
	public static synchronized ConnFactory getInstance() 
	{
		if (cf == null) 
		{
			cf = new ConnFactory();
		}
		return cf;		
	}
	
	
	public Connection getConnection() 
	{
		
		try 
		{
			if( con != null&& !con.isClosed()) 
			{
				return con;
			}
			/* DUDE! what are you doing putting this in here? this is the ConnFactory class, it should not be changed.
			//insert callablestatement for account_table
			myCall = con.prepareCall(" {call insert_trform_accounts_table(?,?,?,?,?,?,?,?)} ");
			*/

		} 
		catch (SQLException e1) 
		{
			System.err.println("ERROR! Failed to check if con is closed: " + e1.getMessage());
			e1.printStackTrace();
		}
		//Properties connectionProperties = new Properties();
		try {
			//oracle.jdbc.driver.OracleDriver.access_string.equals("2");

			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println(" res is :"+ connectionProperties.getProperty("url")+ " " +connectionProperties.getProperty("user")+ " " +connectionProperties.getProperty("password"));

			con=DriverManager.getConnection("jdbc:oracle:thin:@patton-revature.clgkhgrymxm8.us-west-2.rds.amazonaws.com:1521:ORCL","alexpatton1", "biglongsqlpassword!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
} 

