import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class userinfo extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException{
response.setContentType("text/html");
final String JDBC_DRIVER="com.mysql.jdbc.Driver";
final String DB_URL="jdbc:mysql://localhost:3306/login";
final String USER="root";
final String PASS="root";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt=conn.createStatement();
                PrintWriter out=response.getWriter();
                ResultSet rs=stmt.executeQuery("select * from regd");
                out.println("<body style=\"background-image: url(projectback.jpg);\n" +
"  background-repeat: no-repeat;\n" +
"  background-size: cover;\n" +
"  background-attachment: fixed; text-align:center\">\n" +
"<h1 style=\"text-align: center; background-color: black;color: wheat\">Users Details</h1>");
                out.println("<table border=1 width=100% height=40%><br><br>");  
             out.println("<tr><th>First Name</th><th>Last Name</th><th>Gender</th><th>DOB</th><th>Phone Number</th><th>Email</th><th>Aadhar Number</th><th>Password</th><th>Address</th><th>VoterID Number</th><th>Voting Status</th></tr>");  
             while (rs.next()) 
             {  
                 String s1 = rs.getString("fname");  
                 String s2 = rs.getString("lname");  
                 String s3 = rs.getString("gender");  
                 String s4 = rs.getString("dob");  
                 String s5 = rs.getString("phone");  
                 String s6 = rs.getString("email");  
                 String s7 = rs.getString("aadhar");
                 String s8 = rs.getString("pass");
                 String s9 = rs.getString("address");
                 String s10 = rs.getString("voterid");
                 String s11 = rs.getString("status");
                 out.println("<tr><td>" + s1 + "</td><td>" + s2 + "</td><td>" + s3 + "</td><td>"+ s4 + "</td><td>"+ s5 + "</td><td>"+ s6 + "</td><td>"+ s7 + "</td><td>" + s8 + "</td><td>" + s9 + "</td><td>" + s10 + "</td><td>" + s11 + "</td></tr>");   

             }
             out.println("</table>");  
             out.println("<br><br>\n" +
"<form action=\"admin.html\">\n" +
"    <button type=\"submit\" class=\"button\" style=\" border-radius: 4px;\n" +
"  background-color: lightgreen;\n" +
"  border: none;\n" +
"  color: darkblue;\n" +
"  text-align: center;\n" +
"  font-size: 20px;\n" +
"  padding: 20px;\n" +
"  width: 200px;\n" +
"  transition: all 0.5s;\n" +
"  cursor: pointer;\n" +
"  margin: 5px;\">GO BACK</button>\n" +
"</form>");
             out.println("</html></body><br><br>");
                }catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(userinfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}