package rgst.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rgst.dao.SubjectDao;
import rgst.vo.Subject;

@SuppressWarnings("serial")
@WebServlet("/admin/addSbj")
public class AddSubjectServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/AddSubject.jsp");
		rd.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
			ServletContext sc=  this.getServletContext();

			SubjectDao subjectDao = (SubjectDao)sc.getAttribute("subjectDao");
			
			Subject newSubject = new Subject();
			newSubject.setSub_code(request.getParameter("code")).setSub_name(request.getParameter("name"))
			.setProfessor(request.getParameter("profname")).setClassroom(request.getParameter("classroom"))
			.setTime1(request.getParameter("time1")).setTime2(request.getParameter("time2")).setPre(request.getParameter("pre"));
			
			subjectDao.insert(newSubject);
			
			response.sendRedirect(" 갈 곳 ");
		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
		}
	}
	
}
