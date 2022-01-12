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
 public class loginservlet extends HttpServlet
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
String s1=request.getParameter("name");
String s2=request.getParameter("password");
ResultSet rs = stmt.executeQuery("select * from regd where voterid='"+s1+"' and pass='"+s2+"'");
if(rs.next())
{
    String stat = rs.getString("status");
    if(stat.equals("0"))
    {
    stmt.executeUpdate("update regd set status='"+1+"' where voterid='"+s1+"'");
    RequestDispatcher req=request.getRequestDispatcher("profile.html");
    req.include(request,response);
    }
    else
    {
        out.println("<html><script>alert('You have already casted your vote');</script></html>");
        RequestDispatcher req=request.getRequestDispatcher("Homepage.html");
        req.include(request,response);
    }
}
 else if(s1.equals("admin") && s2.equals("admin"))
 {
     response.sendRedirect("admin.html");
 }
 else
 {
     response.sendRedirect("registration.html");
 }
}catch(SQLException | ClassNotFoundException ex )
{
Logger.getLogger(loginservlet.class.getName()).log(Level.SEVERE, null, ex);
}
}
}