
package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;



public class User {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/paf_user_service", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUser(String uname, String uemail, String contactno, String uaddress, String upassword, String usertype)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into user (`userID`,`username`,`email`,`contactNo`,`address`,`password`,`user_type`)"
					 + " values (?, ?, ?, ?, ?, ?, ?)";
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, uname); 
			preparedStmt.setString(3, uemail); 
			preparedStmt.setString(4, contactno); 
			preparedStmt.setString(5, uaddress);
			preparedStmt.setString(6, upassword);
			preparedStmt.setString(7, usertype);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newUser = readUser(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the user details.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	
	public String readUser()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border='1'><tr><th>User ID</th><th>User Name</th><th>Email</th>" +
					"<th>Contact No</th>" + 
					"<th>Address</th>"+
					"<th>User Type</th>"+
					//"<th>Password</th>" +
					"</tr>";
	 
			String query = "select * from user";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String userID = Integer.toString(rs.getInt("userID")); 
				String username = rs.getString("username"); 
				String email = rs.getString("email"); 
				String contactNo =rs.getString("contactNo"); 
				String address = rs.getString("address");
				String user_type = rs.getString("user_type");
				//String password = rs.getNString("password");

	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidUserIDUpdate\' name=\'hidUserIDUpdate\' type=\'hidden\' value=\'" + userID + "'>" 
							+ username + "</td>"; 
				output += "<td>" + email + "</td>";
				output += "<td>" + contactNo + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + user_type + "</td>";
				//output += "<td>" + password + "</td>";


				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='" + userID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the user.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	public String updateUser(String uid, String uname, String uemail, String contactno, String address, String upassword, String usertype)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE user SET username=?,email=?,contactNo=?,address=?,password=?,user_type=? WHERE userID=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, uname); 
			preparedStmt.setString(2, uemail); 
			preparedStmt.setString(3, contactno); 
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, upassword);
			preparedStmt.setString(6, usertype);
			preparedStmt.setInt(7, Integer.parseInt(uid));
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newUser = readUser();    
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteUser(String userID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
				
			} 
	 
			// create a prepared statement    
			String query = "delete from user where userID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(userID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newUser = readUser();    
			output = "{\"status\":\"success\", \"data\": \"" +  newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the user.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}

	
}
