package rgst.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/creditcheck")
public class CreditCheckServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		//여기선 수강신청이 24학점이 안넘는지 체크해준다. 
		HttpSession session = request.getSession();
		
		//이 map은 현재 코드의 결과를 담기때문에 매번 꺼내서 써줘야 한다.
		Map<String,LinkedList<String>> map = (Map<String,LinkedList<String>>)request.getAttribute("resultMap");
		
		if ((int)session.getAttribute("credit")+3>24) {
			map.get("creditFailList").add((String)request.getAttribute("code"));
			//실패할 경우 실패 리스트에 넣고 그냥 끝내면 된다.
		}  else {
			//성공 할 경우 다음으로 이동.
			RequestDispatcher rd = request.getRequestDispatcher("/alreadycheck");
			rd.include(request, response);
		}
	}	
}
