
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) throws StudentNotFoundException {
		try {
			System.out.println("Registering driver...");    
			DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
			System.out.println("Driver registered....");

			System.out.println("Trying to connect to the DB");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");

			System.out.println("Connected to the DB : "+conn);

			System.out.println("trying to make a Prepared statment");
			PreparedStatement pst = conn.prepareStatement("DELETE FROM STUDENT WHERE ROLLNO=?");
			System.out.println("Prepared Statement created : "+pst);


			Scanner scan1 = new Scanner(System.in);
			System.out.println("Enter roll number : ");
			int roll = scan1.nextInt();

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from student where rollno="+roll);

			if(rs.next()) {
				pst.setInt(1, roll);
				System.out.println("Trying to execute the prepared statement....");
				int rows = pst.executeUpdate();
				System.out.println("--------------------------------------------------");
				System.out.println(" Record deleted !"+rows);
			}
			else {
				StudentNotFoundException ex = new StudentNotFoundException("Student Roll number does not exists : "+roll);
				throw ex;
			}
			scan1.close();
			st.close();
			pst.close();	conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}