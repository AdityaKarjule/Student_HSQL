import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest {
	public static void main(String[] args) {
		try {
			DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
			Statement statement = conn.createStatement();
			
			System.out.println("Enter student roll no. to search");
			Scanner sc = new Scanner(System.in);
			int rollNo = sc.nextInt();
			
			ResultSet rs = statement.executeQuery("SELECT * FROM STUDENT WHERE ROLLNO=" + rollNo);
			if(rs.next()) {
				int rollno = rs.getInt(1);
				String studentName = rs.getString(2);
				String birthDate = rs.getString(3);
				int physics = rs.getInt(4);
				int chemistry = rs.getInt(5);
				int maths = rs.getInt(6);
				int total = rs.getInt(7);
				String grade = rs.getString(8);
				System.out.println("---------------------------------------------------");
				System.out.println(rollno + " " + studentName + " "+  birthDate + " " + physics + " " + chemistry + " " + maths + " " + total + " " + grade );
				rs.close();
				statement.close();
				conn.close();
				
			} else {
				throw new StudentNotFoundException("Student not found with rollno " + rollNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (StudentNotFoundException e) {
		
			e.printStackTrace();
		}
	}

}




/*
 * 
 * 
 * 
 * 
 * 
 * 
 * */
