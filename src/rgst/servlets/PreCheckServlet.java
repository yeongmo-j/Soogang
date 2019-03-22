package rgst.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rgst.dao.Past_registerDao;
import rgst.dao.SubjectDao;
import rgst.vo.Now_register;
import rgst.vo.Student;

@SuppressWarnings("serial")
@WebServlet("/precheck")
public class PreCheckServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		//선수과목을 수강 했는지 확인함
		ServletContext sc = this.getServletContext();
		HttpSession session = request.getSession();
		Map<String,LinkedList<String>> map = (Map<String,LinkedList<String>>)(request.getAttribute("resultMap"));
		Student student = (Student)session.getAttribute("student");
		Past_registerDao pastregisterDao = (Past_registerDao)sc.getAttribute("pastregisterDao");
		SubjectDao subjectDao = (SubjectDao)sc.getAttribute("subjectDao");
		
		try {
			//선수과목 꺼내서 pre 지역변수에 저장 
			String pre = subjectDao.getPre((String)request.getAttribute("code"));
			//만약 선수과목이 없다면 x로 표기가 됨
			if (pre.equals("x") || pastregisterDao.exist(student.getStu_code(), pre)) {
				//성공했고, 지금이 마지막 단계이므로, 아예 성공 리스트에 박아버림
				map.get("successList").add((String)request.getAttribute("code"));
				
				//그리고 하나씩 순서대로 등록을 하므로 여기서 등록을 감. 결과는 singleapply로 돌아간 뒤에 출려해줄것임
				RequestDispatcher rd = request.getRequestDispatcher("/submit");
				rd.include(request, response);
			} else {
				//실패할경우 여기서 끝
				map.get("preFailList").add((String)request.getAttribute("code") + " : " + pre + "를 이수하지 않으셨습니다.");
			}
		} catch (Exception e) {
		}
	}	
}
