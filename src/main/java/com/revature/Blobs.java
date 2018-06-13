package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Blobs {
	public static ConnFactory cf =ConnFactory.getInstance();
	
	public void writeBlob(String fileName) throws FileNotFoundException 
	{
		Connection conn = cf.getConnection();
		
		try 
		{
			CallableStatement myCall = conn.prepareCall(" {call w_blob(?)} ");
			File theFile = new File(fileName);
			FileInputStream input = new FileInputStream(theFile);
			myCall.setBinaryStream(1, input);
			myCall.execute();
			System.out.println("We have the file");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	public void readBlob(String fileName) throws FileNotFoundException, SQLException 
	{
		Connection conn = cf.getConnection();
		Statement mystate = null;
		ResultSet rs = null;
		InputStream input = null;
		
		
		try 
		{
			mystate = conn.createStatement();
			String sql = "select forms from forms_table where form_id = ?";
			rs = mystate.executeQuery(sql);
			File theFile = new File("Forms_from_DB.pdf");
			FileOutputStream output = new FileOutputStream(theFile);
			
			if(rs.next()) 
			{
				input = rs.getBinaryStream(fileName);
				System.out.println("Reading...");
				System.out.println(sql);
				
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) 
				{
					output.write(buffer);
				}
				
				System.out.println("\nSaved to filepath: "+ theFile.getAbsolutePath());
				System.out.println("\nCompleted Successfully!");
				
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
