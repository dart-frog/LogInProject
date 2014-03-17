import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Connect {
	private java.sql.Connection con = null;
	private final String url = "jdbc:sqlserver://";
	private final String serverName= "fourwaylo.com";
	private final String portNumber = "8889";
	private final String databaseName = "csproj";
	private final String userName = "csproj";
	private final String password = "DoYourHomework";
	private final String selectMethod = "cursor";
	
	public Connect(){}
	
	private String getConnectionUrl(){
		return url+serverName+":"+portNumber+";databaseName="+databaseName+";selectMethod=" + selectMethod+ ";";
	}
	
	public java.sql.Connection getConnection(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerConnection");
				con = java.sql.DriverManager.getConnection(getConnectionUrl(), userName, password);
				if(con != null) System.out.println("Connection Successful");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Error Trace in getConnection() : " + e.getMessage());
			}
			return con;
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
			String sql = "INSERT INTO nate.Register(userName, password) VALUES (?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
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
		String sql = "Select ? From nate.Register";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, pop);
		ResultSet rs = pstmt.executeQuery(sql);
		if (rs.getString(pop) == hat){
			pstmt.close();
			return true;
		}
		pstmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public String display(){
		Connection con = getConnection();
		String x ="";
		try{
		String sql = "Select * From nate.Register";
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

