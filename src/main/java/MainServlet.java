import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/hello")
public class MainServlet extends HttpServlet {
  private final static String LOGIN = "/WEB-INF/view/enterence.jsp";

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String someText = (String) req.getParameter("text");
    HttpSession session = req.getSession();
    Integer visitCounter = (Integer) session.getAttribute("visitCounter");
    if (visitCounter == null) {
      visitCounter = 1;
    } else {
      visitCounter++;
    }
    session.setAttribute("visitCounter", visitCounter);
    String username = req.getParameter("username");
    resp.setContentType("text/html");
    PrintWriter printWriter = resp.getWriter();
    if (username == null) {
      printWriter.write("Hello, Anonymous" + "<br>");
    } else {
      printWriter.write("Hello, " + username + "<br>");
    }
    printWriter.write("Page was visited " + visitCounter + " times.\n" + someText);
    printWriter.close();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    getServletContext().getRequestDispatcher(LOGIN).forward(req, resp);
  }
}