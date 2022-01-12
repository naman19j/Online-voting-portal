import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class feedbackserv extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
        @Override
public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        response.setContentType("text/html");
final String JDBC_DRIVER="com.mysql.jdbc.Driver";
final String DB_URL="jdbc:mysql://localhost:3306/login";
final String USER="root";
final String PASS="root";
String s1=request.getParameter("name");
String s2=request.getParameter("email");
String s3=request.getParameter("views");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt=conn.createStatement();
                PrintWriter out=response.getWriter();
                stmt.executeUpdate("insert into feedback values('"+s1+"','"+s2+"','"+s3+"')");
                out.println("<script>\n" +
"    alert(\"Your Feedback has been recorded. Thank You.\");\n" +
"    </script>");
                RequestDispatcher req=request.getRequestDispatcher("Homepage.html");
                req.include(request,response);
                
                }catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(feedbackserv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}