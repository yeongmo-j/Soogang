package rgst.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rgst.dao.SubjectDao;
import rgst.vo.Now_register;
import rgst.vo.Student;

@SuppressWarnings("serial")
@WebServlet("/submit")
public class SubmitServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		//여기선 수강신청 성공한 애들을 session에 저장해놓는 일을 함. db에는 저장 안함. db에는 끝날 때 저장함		
		ServletContext sc = this.getServletContext();
		HttpSession session = request.getSession();
		
		//학점 3개 늘려서 session에 저장
		int credit = (int)session.getAttribute("credit") +3;
		session.setAttribute("credit", credit);
		
		Student student = (Student)session.getAttribute("student");
		SubjectDao subjectDao = (SubjectDao)sc.getAttribute("subjectDao");
		
		//현재 세션에서 관리하는 학점 리스트 꺼내온 다음에 해당 과목 등록
		ArrayList<Now_register> nowregister = (ArrayList<Now_register>)session.getAttribute("nowregister");
		nowregister.add(new Now_register().setStu_code(student.getStu_code()).setSub_code((String)request.getAttribute("code")));
		
		//시간표 반영
		String[][] timetable = (String[][])session.getAttribute("timetable");
		int[] time = subjectDao.getTime(request.getParameter("code"));
		for (int t : time) {
			if (t!=0) {
				timetable[t/10][t%10] = (String)request.getAttribute("code");
			}
		}
		
		//이제 다시 처음으로 돌아가서 출력해주면 된다. (/singleapply로 돌아감)
	}	
}
