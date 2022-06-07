
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/")
public class IndexServlet extends HttpServlet {

  private final static String MENU = "/WEB-INF/view/user_menu.jsp";
  private final static String SIMPLE = "/WEB-INF/htmlFromRusik/header/header.jsp";

  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    getServletContext().getRequestDispatcher(SIMPLE).forward(req, resp);

//    resp.sendRedirect(req.getContextPath() + INDEX);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {


    System.out.println("creating");

//    resp.sendRedirect(req.getContextPath() + MENU);
    getServletContext().getRequestDispatcher(MENU).forward(req, resp);
//    req.setCharacterEncoding();
//    super.doPost(req, resp);
  }
}