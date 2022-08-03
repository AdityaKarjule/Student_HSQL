import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {
	public static String getGrade(int total) {
		float percent = total / (float) 3;
		if(percent >= 90) {
			return "A";
		} else if(percent >= 80) {
			return "B";
		} else if(percent >= 70) {
			return "C";
		} else if(percent >= 40) {
			return "D";
		}
		return "E";
	}

	public static void main(String[] args) throws StudentAlreadyExists {
		try {
			DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
			System.out.println("DB Setuped and connected");

			PreparedStatement pst = conn.prepareStatement("INSERT INTO STUDENT VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

			Scanner sc1 = new Scanner(System.in);
			System.out.println("Enter Student roll number to add");
			int rollno = sc1.nextInt();

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM STUDENT WHERE ROLLNO=" + rollno);

			if(rs.next()) {
				throw new StudentAlreadyExists(rollno + " student roll no already exists");
			}

			Scanner sc2 = new Scanner(System.in);
			System.out.println("Student Name:");
			String studentName = sc2.next();
			Scanner sc3 = new Scanner(System.in);
			System.out.println("BirthDate:");
			String studentBirthdate = sc3.next();
			Scanner sc4 = new Scanner(System.in);
			System.out.println("Physics:");
			int physics = sc4.nextInt();
			Scanner sc5 = new Scanner(System.in);
			System.out.println("Chemistry:");
			int chemistry = sc5.nextInt();
			Scanner sc6 = new Scanner(System.in);
			System.out.println("Maths:");
			int maths = sc6.nextInt();
			int total = maths + physics + chemistry;

			pst.setInt(1, rollno);
			pst.setString(2, studentName);
			pst.setString(3, studentBirthdate);
			pst.setInt(4, physics);
			pst.setInt(5, chemistry);
			pst.setInt(6, maths);
			pst.setInt(7, total);
			pst.setString(8, getGrade(total));
			
			
			
			int rows = pst.executeUpdate();
			System.out.println("Student Data Recorded!! " + rows);
			System.out.println("-----------------------------");
			System.out.println("Name "+studentName);
			System.out.println("RollNo "+rollno);
			
			pst.close();
			conn.close();

			sc1.close();
			sc2.close();
			sc3.close();
			sc4.close();
			sc5.close();
			sc6.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}