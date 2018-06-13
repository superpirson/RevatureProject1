package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Blobs {
	public static ConnFactory cf =ConnFactory.getInstance();
	
	//to insert the blob into the databse
	public void writeBlob(String fileName, Double id) throws FileNotFoundException 
	{
		Connection conn = cf.getConnection();
		
		try 
		{
			CallableStatement myCall = conn.prepareCall(" {call w_blob(?,?)} ");
			File theFile = new File(fileName);
			FileInputStream input = new FileInputStream(theFile);
			myCall.setBinaryStream(1, input);
			myCall.setDouble(2, id);
			myCall.execute();
			System.out.println("We have the file");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	//to read in the blob from the database; this will download it into local
	public void readBlob(String inputFile,String outputFile ,double id) throws FileNotFoundException, SQLException 
	{
		Connection conn = cf.getConnection();
		Statement mystate = null;
		ResultSet rs = null;
		InputStream input = null;
		PreparedStatement ps = null;
		
		
		try 
		{
			String sql = "select forms from forms_table where form_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, id);
			rs = ps.executeQuery();
			

			File theFile = new File(outputFile+".pdf"); //outputFile == the name it will be downloaded to.
			FileOutputStream output = new FileOutputStream(theFile);
			
			if(rs.next()) 
			{
				input = rs.getBinaryStream(inputFile); //taking in the blob file from the database
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
