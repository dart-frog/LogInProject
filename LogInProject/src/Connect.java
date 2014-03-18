import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

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
	public boolean SameUser(String hat, String pop){
		Connection con = getConnection();
		try{
			String sql = "SELECT " +  pop+ " FROM nate.Registry";
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, pop);
			ResultSet rs = pstmt.executeQuery();
			if (rs.getString(0).equals(hat)){
				pstmt.close();
				System.out.println();
				return true;
			}
		pstmt.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public String display(){
		Connection con = getConnection();
		String x ="";
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

