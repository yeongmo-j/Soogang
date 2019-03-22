package rgst.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/main")
public class MainRegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//일단 과목리스트들 include 해준다. 여기에 버튼들도 다 있음
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		if (session.getAttribute("student")==null) {
			response.sendRedirect("auth/login");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/main/subjectList");
		rd.include(request, response);
		
		rd = request.getRequestDispatcher("/main/RegisterList.jsp");
		rd.include(request, response);
		
	}

}
