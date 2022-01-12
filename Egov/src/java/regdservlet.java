import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class regdservlet extends HttpServlet {

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
String s1=request.getParameter("fname");
String s2=request.getParameter("lname");
String s3=request.getParameter("gender");
String s4=request.getParameter("dob");
String s5=request.getParameter("phone");
String s6=request.getParameter("email");
String s7=request.getParameter("aadhar");
String s8=request.getParameter("address");
String s10=request.getParameter("pass");
String s9="profile.jpg";
int min=1000000;
int max=9999999;

int random=(int) (Math.random()*(max-min+1)+min);
String vid = "GDN"+random;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt=conn.createStatement();
                PrintWriter out=response.getWriter();
                stmt.executeUpdate("insert into regd values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s10+"','"+s8+"','"+vid+"','0')");
                out.println("<html>\n" +
                    "<head>\n" +
                    "    <title>Voter ID Card</title>\n" +
                    "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                    "<style>\n" +
                    ".card {\n" +
                    "  box-shadow: 0 16px 32px 0 rgba(0, 0, 0, 0.2);\n" +
                    "  max-width: 320px;\n" +
                    "  margin: auto;\n" +
                    "  text-align: center;\n" +
                    "  font-family: arial;\n" +
                    "}\n" +
                    "\n" +
                    ".title {\n" +
                    "  color: grey;\n" +
                    "  font-size: 18px;\n" +
                    "}\n" +
                    "\n" +
                        "body {\n" +
                    "  background-image:url(projectback.jpg);\n" +
                    "  background-repeat:no-repeat;\n" +
                    " background-size: cover;\n" +
                    "  background-attachment: fixed;\n" +
                    "}\n" +
                    "button {\n" +
                    "  border: none;\n" +
                    "  outline: 0;\n" +
                    "  display: inline-block;\n" +
                    "  padding: 8px;\n" +
                    "  color: white;\n" +
                    "  background-color: #000;\n" +
                    "  text-align: center;\n" +
                    "  cursor: pointer;\n" +
                    "  width: 100%;\n" +
                    "  font-size: 18px;\n" +
                    "}\n" +
                    "\n" +
                    "a {\n" +
                    "  text-decoration: none;\n" +
                    "  font-size: 22px;\n" +
                    "  color: black;\n" +
                    "}\n" +
                    "\n" +
                    "button:hover, a:hover {\n" +
                    "  opacity: 0.7;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                        
                    "<h1 style=\"text-align:center;background-color: black;color:white\">Voter ID Card</h1>\n" +
                    "\n" +
                    "<div class=\"card\">\n" +
                    "  <img src="+s9+" alt=\"Profile Image\" style=\"width:100%\">\n" +
                    "  <h1>"+s1+" "+s2+"</h1>\n" +
                    "  <p class=\"title\">Date of Birth:<br>"+s4+"</p>\n" +
                    "  <p>Address:<br>"+s8+"</p>\n" +
                    "  <p><button>VOTER ID: GDN"+random+"</button></p>\n" +
                    "</div>\n" +
                            "<br><br><br><form action=\"login.html\">"+
                            "<button type=\"submit\">Login Here</button>"+
                            "</form>"+
                    "\n" +
                    "</body>\n" +
                    "</html>\n");
                }catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(regdservlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}