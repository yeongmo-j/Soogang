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
import rgst.vo.Now_register;
import rgst.vo.Student;

@SuppressWarnings("serial")
@WebServlet("/save")
public class SaveAndLogOutServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html; charset=UTF-8"); 
		HttpSession session = request.getSession();
		int credit = (int)session.getAttribute("credit");
		if (credit<9) {
			RequestDispatcher rd =request.getRequestDispatcher("/Error/LessThan9.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd =request.getRequestDispatcher("/main/SaveAndLogout.jsp");
			rd.include(request, response);

			//여기선 지금까지 등록한 수강신청 정보를 직접 db에 저장해준다
			ServletContext sc = this.getServletContext();

			Student student = (Student)session.getAttribute("student");		
			StudentDao studentDao = (StudentDao)sc.getAttribute("studentDao");


			//학생에 학점도 업데이트 할 것.
			try {
				studentDao.updateCredit(student.getStu_code(), credit);
			} catch (Exception e) {
			}

			ArrayList<Now_register> nowregister = (ArrayList<Now_register>)session.getAttribute("nowregister");
			Now_registerDao nowregisterDao = (Now_registerDao)sc.getAttribute("nowregisterDao");
			
			//지금까지 등록된 모든 과목들을 일단 삭제시켜둔 뒤에 다시 등록할 것이다.
			try {
				nowregisterDao.delete(student.getStu_code());
			} catch(Exception e) {
			}
			//이제 등록할 차례다
			try {
				for (Now_register sub : nowregister) {
					nowregisterDao.insert(student.getStu_code(), sub.getSub_code());
				}
			} catch (Exception e) {		
			}
			//이제 로그아웃 해주고 로그인 화면으로 가면 된다.
			session.invalidate();
		}
	}	
}
