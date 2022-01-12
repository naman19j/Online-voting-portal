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
 public class voteServ extends HttpServlet
{
@Override
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("text/html");
final String JDBC_DRIVER="com.mysql.jdbc.Driver";
final String DB_URL="jdbc:mysql://localhost:3306/login";
final String USER="root";
final String PASS="root";
try
{
Class.forName("com.mysql.jdbc.Driver");
Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
PrintWriter out = response.getWriter();
Statement stmt=conn.createStatement();
String s1=request.getParameter("party");
ResultSet rs = stmt.executeQuery("select * from voterecord where party_name='"+s1+"'");
if(rs.next())
{
   int s2 = rs.getInt("vote_count");
   s2=s2+1;
   stmt.executeUpdate("update voterecord set vote_count='"+s2+"' where party_name='"+s1+"'");
}

out.println("<html>\n" +
"    <style>\n" +
"        .button {\n" +
"        border-radius: 4px;\n" +
"        background-color: lightblue;\n" +
"        border: none;\n" +
"        color: darkblue;\n" +
"        text-align: center;\n" +
"        font-size: 20px;\n" +
"        padding: 20px;\n" +
"        width: 123px;\n" +
"        transition: all 0.5s;\n" +
"        cursor: pointer;\n" +
"        margin: 5px;\n" +
"}\n" +
"    </style>\n" +
"    <body style=\"background-image: url(projectback.jpg);\n" +
"            background-repeat: no-repeat;\n" +
"            background-size: cover;\n" +
"            background-attachment: fixed;\n" +
"            text-align: center\">\n" +
"                \n" +
"        <h1>YOUR VOTE HAS BEEN SUCCESSFULLY RECORDED</h1><br>\n" +
"        <h2>CLICK TO LOGOUT</h2><br>\n" +
"        <form action=\"Homepage.html\" target=\"_blank\">\n" +
"            <button type=\"submit\" class=\"button\">LOG OUT</button>\n" +
"        </form>\n" +
"    </body>\n" +
"</html>");

}catch(SQLException | ClassNotFoundException ex )
{
Logger.getLogger(voteServ.class.getName()).log(Level.SEVERE, null, ex);
}
}
}