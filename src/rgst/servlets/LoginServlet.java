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

import rgst.dao.Now_registerDao;
import rgst.dao.StudentDao;
import rgst.dao.SubjectDao;
import rgst.vo.Now_register;
import rgst.vo.Student;

@SuppressWarnings("serial")
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8"); 
		if (session.getAttribute("student")!=null) {
			response.sendRedirect("../main");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInForm.jsp");
			rd.forward(request, response);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			
			/* Session 범위 : student(로그인 정보) credit(학점) nowregister(지금까지 들었던 과목 리스트) timetable(시간표)
			 * 가 저장됨 
			 * 로그인 성공하면 /main으로 실패하면 /auth/LogInFail.jsp로 이동
			 */
			
			ServletContext sc = this.getServletContext();
			StudentDao studentDao = (StudentDao)sc.getAttribute("studentDao");
			
			//로그인 정보 가져오기
			Student student = studentDao.exist(request.getParameter("code"), request.getParameter("password"));
	
			//로그인 성공 시
			if (student!=null) {
				//로그인 정보 : session범위에 student
				HttpSession session = request.getSession();
				session.setAttribute("student",student);
				//학점 정보 : session범위에 학점 
				session.setAttribute("credit", student.getCredit());
				
				//시간표를 위해 배열 만들어서 x로 초기화								
				String[][] timetable = new String[7][6];
				for (int i=1 ; i<=6 ; i++) {
					for (int j=1 ; j<=5 ; j++) {
						timetable[i][j] = "x";
					}
				}
				
				Now_registerDao nowregisterDao = (Now_registerDao)sc.getAttribute("nowregisterDao");
				SubjectDao subjectDao = (SubjectDao)sc.getAttribute("subjectDao");
				
				try {
					//nowregister :지금까지 들었던 과목들 리스트임
					ArrayList<Now_register> nowregister = nowregisterDao.selectList(new Now_register().setStu_code(student.getStu_code()));
					if (nowregister!=null) {
						//세션 범위에 지금까지 들었던 과목들 리스트 저장되어있음
						session.setAttribute("nowregister", nowregister);
						//그 과목들 리스트 돌려서 시간 얻으면서, 시간 배열에 반영함
						for(Now_register sbj : nowregister) {
							int[] time = subjectDao.getTime(sbj.getSub_code());
							for (int t : time) {
								if (t!=0) {
									timetable[t/10][t%10] = sbj.getSub_code();
								}
							}
						}
					}
				} catch(Exception e) {
				}
				//시간표 session범위에 저장
				session.setAttribute("timetable", timetable);				
				//메인으로 이동
				response.sendRedirect("../main");	
			}else {
				//로그인 실패 시
				RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInFail.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		} 
	}
}
