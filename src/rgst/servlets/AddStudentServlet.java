package rgst.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rgst.dao.StudentDao;
import rgst.vo.Student;

@SuppressWarnings("serial")
@WebServlet("/admin/add")
public class AddStudentServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/AddStudent.jsp");
		rd.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
			ServletContext sc=  this.getServletContext();

			StudentDao studentDao = (StudentDao)sc.getAttribute("studentDao");
			
			Student newStudent = new Student();
			newStudent.setStu_code(request.getParameter("code")).setStu_name(request.getParameter("name")).setCredit(0)
			.setPassword(request.getParameter("password"));
			//여기부터 채울것 
			
			studentDao.insert(newStudent);
			response.sendRedirect(" 갈 곳 ");
		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
		}
	}
	
}
