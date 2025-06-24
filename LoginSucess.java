import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginSuccess")
public class LoginSuccess extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        String userName = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("currentUser".equals(cookie.getName())) {
                    userName = cookie.getValue();
                    break;
                }
            }
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Login Success</h2>");
        out.println("<h3>Login Success!!! Welcome</h3>");
        out.println("<h2>This is a Shopping cart for " + userName + "</h2>");
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }
}
