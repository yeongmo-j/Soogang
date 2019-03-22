package rgst.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rgst.vo.Now_register;

@SuppressWarnings("serial")
@WebServlet("/alreadycheck")
public class AlreadyCheckServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		
		//여기선 지금 이미 수강신청 한 목록에 존재하는지 체크해본다.
		HttpSession session = request.getSession();
		String code = (String)request.getAttribute("code");

		//nowregister은 지금 수강신청 되어있는 목록을 불러옴
		ArrayList<Now_register> nowregister = (ArrayList<Now_register>)session.getAttribute("nowregister");
		Map<String,LinkedList<String>> map = (Map<String,LinkedList<String>>)request.getAttribute("resultMap");

		//수강신청되어있는 목록들 다 불러오면서 같은 코드가 있는지 확인
		boolean isClear = true;
		for(Now_register sub : nowregister) {
			if (sub.getSub_code().equals(code)) {
				map.get("alreadyRegisted").add(code);
				isClear = false;
				break;
			}
		}
		//만약 겹치는게 하나도 없다면 다음으로 이동
		if (isClear) {
			RequestDispatcher rd = request.getRequestDispatcher("/timecheck");
			rd.include(request, response);
		}
	}	
}
