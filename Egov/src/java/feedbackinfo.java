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
public class feedbackinfo extends HttpServlet {
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
                ResultSet rs=stmt.executeQuery("select * from feedback");
                out.println("<body style=\"background-image: url(projectback.jpg);\n" +
"  background-repeat: no-repeat;\n" +
"  background-size: cover;\n" +
"  background-attachment: fixed; text-align:center\">\n" +
"<h1 style=\"text-align: center; background-color: black;color: wheat\">VOTE  COUNT</h1>");
                out.println("<table border=1 width=100% height=50%><br><br>");  
             out.println("<tr><th>NAME</th><th>EMAIL</th><th>VIEWS</th></tr>");  
             while (rs.next()) 
             {  
                 String s1 = rs.getString("name");  
                 String s2 = rs.getString("email");
                 String s3 = rs.getString("views");
                 out.println("<tr><td>" + s1 + "</td><td>" + s2 + "</td><td>" + s3 + "</td></tr>");   

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
                Logger.getLogger(feedbackinfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}