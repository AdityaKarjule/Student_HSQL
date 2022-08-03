import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectStudent {
public static void main(String[] args) {
	try {
		System.out.println("Registering Student...");    
		DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
		System.out.println("Student  registered....");
		
		System.out.println("Trying to connect to the DB");
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
		
		System.out.println("Connected to the DB : "+conn);
		
		System.out.println("trying to make a statment");
		Statement statement = conn.createStatement();
		System.out.println("Statement created : "+statement);
		
		System.out.println("Trying to execute the statement....");
		ResultSet rs = statement.executeQuery("SELECT * FROM STUDENT");
		System.out.println("Statement executed, got the result....");
			
		while(rs.next()) {
			int rollNo = rs.getInt(1) ;
			String sname = rs.getString(2) ;
			String birthDate = rs.getString(3);
			int physics = rs.getInt(4) ;
			int chemistry = rs.getInt(5) ;
			int maths = rs.getInt(6) ;
			int total = rs.getInt(7) ;
			String grade = rs.getString(8);
			
			System.out.println("STUDENT NAME   : "+sname);
			System.out.println("Roll Number  : "+rollNo);
			System.out.println("PHYSICS  : "+physics);
			System.out.println("CHEMISTRY  : "+chemistry);
			System.out.println("MATHS  : "+maths);
			System.out.println("TOTAL  : "+total);
			System.out.println("GRADE : "+grade);
			
			System.out.println("------------------------");
		}
		rs.close();	statement.close();	conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
}



