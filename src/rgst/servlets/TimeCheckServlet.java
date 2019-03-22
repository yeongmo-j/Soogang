package rgst.servlets;

import java.io.IOException;
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

import rgst.dao.SubjectDao;

@SuppressWarnings("serial")
@WebServlet("/timecheck")
public class TimeCheckServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		//지금까지 수강신청 된 과목들과 겹치는 시간이 있는지 확인
		ServletContext sc = this.getServletContext();
		HttpSession session = request.getSession();
		
		//시간표 꺼냄
		String[][] timetable = (String[][])session.getAttribute("timetable");
		Map<String,LinkedList<String>> map = (Map<String,LinkedList<String>>)request.getAttribute("resultMap");
				
		SubjectDao subjectDao = (SubjectDao)sc.getAttribute("subjectDao");
		
		//시간 2개를 리턴받음
		int[] time = subjectDao.getTime((String)request.getAttribute("code"));
		int i;
		//시간이 겹치면 실패에 추가하고 끝내버림
		for (i=0 ; i<=1 ; i++ ) {
			if (!timetable[time[i]/10][time[i]%10].equals("x")) {
				map.get("timeFailList").add((String)request.getAttribute("code") + " : " + timetable[time[i]/10][time[i]%10] + "과 시간이 겹칩니다.");
				break;
			}
		}
		//시간 안겹치면 다음으로 이동
		if (i>=2) {
			RequestDispatcher rd = request.getRequestDispatcher("/precheck");
			rd.include(request, response);
		}
		
	}	
}
