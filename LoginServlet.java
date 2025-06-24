import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    urlPatterns = { "/LoginServlet" },
    initParams = {
        @WebInitParam(name = "usernames", value = "user1,user2,user3"),
        @WebInitParam(name = "passwords", value = "pwd1,pwd2,pwd3"),
        @WebInitParam(name = "cardIDs", value = "111,222,333")
    }
)
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String usr = request.getParameter("user");
        String pwd = request.getParameter("password");
        String card = request.getParameter("cardID");
        boolean found = false;

        String[] usernames = getServletConfig().getInitParameter("usernames").split(",");
        String[] passwords = getServletConfig().getInitParameter("passwords").split(",");
        String[] cardIDs = getServletConfig().getInitParameter("cardIDs").split(",");

        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(usr) && passwords[i].equals(pwd) && cardIDs[i].equals(card)) {
                found = true;
                Cookie userCookie = new Cookie("currentUser", usr);
                userCookie.setMaxAge(60 * 60); // 1 hour
                response.addCookie(userCookie);
                response.sendRedirect("LoginSuccess");
                break;
            }
        }

        if (!found) {
            out.println("<h2>Error</h2>");
            out.println("<h4>Invalid user, please try again by clicking the following link</h4>");
            out.println("<a href='Login.jsp'>Login</a>");
        }

        out.close();
    }
}
