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
import javax.servlet.http.HttpSession;

import rgst.vo.Now_register;
import rgst.vo.Student;

@SuppressWarnings("serial")
@WebServlet("/cancel")
public class SubjectCancelServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//과목 하나 세션에서 삭제해준다. 학점, 리스트, 시간표 다 없애준다
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
				
		int credit = (int)session.getAttribute("credit")-3;
		session.setAttribute("credit", credit);

		String sub_code = (String)request.getParameter("code");
		
		ArrayList<Now_register> nowregister = (ArrayList<Now_register>)session.getAttribute("nowregister");
		
		//여기서 지워줄 것임
		for(int i=0 ; i<nowregister.size() ; i++) {
			if (nowregister.get(i).getSub_code().equals(sub_code)) {
				nowregister.remove(i);
			}
		}
		
		String[][] timetable = (String[][])session.getAttribute("timetable");
		for (int i=1 ; i<=6 ; i++) {
			for (int j=1 ; j<=5 ; j++) {
				if (timetable[i][j].equals(sub_code))
					timetable[i][j] = "x";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("main");
		rd.forward(request, response);
	}	
}
