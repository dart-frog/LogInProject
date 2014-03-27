import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.Cookie;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;


public class Connect {
	private java.sql.Connection con = null;
	private final String url = "jdbc:sqlserver://";
	private final String serverName= "fourwaylo.com";
	private final String portNumber = "8889";
	private final String databaseName = "csproj";
	private final String userName = "csproj";
	private final String password = "DoYourHomework";
	private final String selectMethod = "cursor";
	/*
	   SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("csproj");
        ds.setPassword("DoYourHomework");
        ds.setServerName("fourwaylo.com");
        ds.setPortNumber(8889);
        ds.setDatabaseName("csproj");
        try {
                     Connection con = ds.getConnection();
                     Statement stmt = con.createStatement();
                     stmt.executeQuery("SELECT * from My.Table;");
                    
              } catch (Exception e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
              }
 
Let us know if you’re still having issues
	 */
	public Connect(){}
	
	private String getConnectionUrl(){
		return url+serverName+":"+portNumber+";databaseName="+databaseName+";selectMethod=" + selectMethod+ ";";
	}
	
	public java.sql.Connection getConnection(){
		
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("csproj");
		ds.setPassword("DoYourHomework");
		ds.setServerName("fourwaylo.com");
		ds.setPortNumber(8889);
		ds.setDatabaseName("csproj");
		
		try {
		    Connection conn = ds.getConnection();
		    return conn;
		} 
		catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
        return null;
	}
	private void closeConnection(){
		try{
			if(con!= null)
				con.close();
			con = null;
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	public void addNewUser(String username, String password){
		Connection con = getConnection();
		try{
			String sql = "INSERT INTO nate.Registry (userName, password) VALUES (?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			int count = pstmt.executeUpdate();
			System.out.println("ROWS AFFECTED:" + count);
			pstmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void setSessionId(String username, String userId){
		Connection con = getConnection();
		try{
			String sql = "INSERT INTO nate.Users (userName, sessionId) VALUES(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, userId);
			int count = pstmt.executeUpdate();
			System.out.println("ROWS AFFECTED:" + count);
			pstmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public boolean checkSessionId(Cookie[] list){
		Connection con = getConnection();
		try{
			String sql = "SELECT sessionId FROM nate.Users;";
			Statement stmt = con.createStatement();		
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				for (Cookie c: list){
					if (c.getName() == "sessionId"){
						if (c.getValue() == rs.getString("sessionId")){
							return true;
						}
					}
				}
			}
			stmt.close();		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
		
	}
	public boolean SameUser(String userxName, String passxword){
		boolean correctPassword = false;
		Connection con = getConnection();
		try{
			String sql = "SELECT userName, password FROM nate.Registry;";
			Statement stmt = con.createStatement();
			//pstmt.setString(1, pop);
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			HashMap users = new HashMap();
			while(rs.next()){
				users.put(rs.getString("userName"), rs.getString("password"));
			}
			System.out.print(users.toString());
			if(users.get(userxName) == null){
				correctPassword = false;
			}
			if (users.get(userxName).equals(passxword)){
				correctPassword = true;
			}
			else{
				correctPassword = false;
			}
		
		stmt.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		if (correctPassword){
			return true;
		}
		return false;
	}
	public String display(){
		Connection con = getConnection();
		String x = "Users \n";
		try{
		String sql = "Select * From nate.Registry";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			x += (rs.getString("userName") + rs.getString("passWord"));
		}
		stmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return x;
	}
	
	
}

