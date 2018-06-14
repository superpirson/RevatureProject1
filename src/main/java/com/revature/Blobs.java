package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Blobs {
	public static ConnFactory cf =ConnFactory.getInstance();
	
	//to insert the blob into the database
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
			System.out.println(fileName+" has been received.");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	//Reading a Blob from DB and write to a local file
	public void readBlob(String outputFile ,double id) throws FileNotFoundException, SQLException 
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
			ps.setDouble(1, id); //which file from form_id we want to read in. 
			rs = ps.executeQuery();
			

			File theFile = new File(outputFile+".pdf"); //outputFile == the name of the file it will have when downloaded.
			FileOutputStream output = new FileOutputStream(theFile);
			
			// read the blob and store it in the output file.
			if(rs.next()) 
			{
				input = rs.getBinaryStream("files"); //taking in the blob file from the files column
				System.out.println("Reading...");
				System.out.println(sql);
				
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) 
				{
					output.write(buffer);
				}
				
				System.out.println("\nSaved to filepath: "+ theFile.getAbsolutePath()); //this will print out the file path where the file has been stored.
				System.out.println("\nFile downloaded");
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
