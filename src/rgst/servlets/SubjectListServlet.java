package rgst.servlets;

import java.io.IOException;
import java.util.ArrayList;

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
@WebServlet("/main/subjectList")
public class SubjectListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		//모든 과목 리스트 request범위에 넣고 jsp로 넘김
		
		ServletContext sc = this.getServletContext();
		SubjectDao subjectDao = (SubjectDao)sc.getAttribute("subjectDao");
		
		try {
			//모든 과목 리스트를 받아서 request범위에 넣어줌
			ArrayList<Subject> subjectList = subjectDao.selectList();
			if (subjectList!=null) {
				request.setAttribute("subjectList", subjectList);
				RequestDispatcher rd = request.getRequestDispatcher("/main/SubjectList.jsp");
				rd.include(request, response);
			}
		} catch (Exception e) {
			/*
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
			*/
		}

	}

}
