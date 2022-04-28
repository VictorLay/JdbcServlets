
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/")
public class IndexServlet extends HttpServlet {

  private final static String INDEX = "/WEB-INF/view/index.jsp";
  private final static String REGISTRATION = "/WEB-INF/view/registration_page.jsp";
  private final static String MENU = "/WEB-INF/view/user_menu.jsp";
  private final static String GREETING = "/WEB-INF/view/greeting_page.jsp";

  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    getServletContext().getRequestDispatcher(REGISTRATION).forward(req, resp);

//    resp.sendRedirect(req.getContextPath() + INDEX);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String name = req.getParameter("forename");
    String surname = req.getParameter("surname");
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    System.out.println("creating");

//    resp.sendRedirect(req.getContextPath() + MENU);
    getServletContext().getRequestDispatcher(MENU).forward(req, resp);
//    req.setCharacterEncoding();
//    super.doPost(req, resp);
  }
}