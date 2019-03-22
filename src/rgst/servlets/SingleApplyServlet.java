package rgst.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/singleapply")
public class SingleApplyServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		//파라미터로 넘어온 코드 받아서 request범위로 넣어줌
		String[] codes = request.getParameterValues("code");
		
		//선택된 과목들의 상태정보를 담아줄 map 만들어서 request범위로 넣어줌
		Map<String,LinkedList<String>> map = new HashMap<String,LinkedList<String>>();
		map.put("successList", new LinkedList<String>());
		map.put("creditFailList", new LinkedList<String>());
		map.put("timeFailList", new LinkedList<String>());
		map.put("preFailList", new LinkedList<String>());
		map.put("alreadyRegisted", new LinkedList<String>());
		request.setAttribute("resultMap", map);
		
		//만약 codes가 null이면 선택 안되었단 의미이므로,선택하라고 말해준다. 여기 고쳐야 함 
		if (codes==null) {
			RequestDispatcher rd =request.getRequestDispatcher("Error/NotSelectError.jsp");
			rd.forward(request, response);
		}
		
		
		//각각의 파라미터 별로 한번씩 다 수강신청 가능한지를 체크해준다
		//체크의 순서는 credit -> already - >time -> pre 순서이다.
		for (String code : codes) {
			request.setAttribute("code",code);
			response.setContentType("text/html; charset=UTF-8"); 
			RequestDispatcher rd = request.getRequestDispatcher("/creditcheck");
			rd.include(request, response);
		}
		
		//모든 과목들 체크가 끝났으면, 그 결과를 화면에 뿌려준다.
		//그 전에 countmap은 각 결과의 개수를 카운트 해줄거임.
		Map<String, Integer> countmap = new HashMap<String, Integer>();
		int suc = map.get("successList").size();
		int cre = map.get("creditFailList").size();
		int time = map.get("timeFailList").size();
		int pre = map.get("preFailList").size();
		int alrea = map.get("alreadyRegisted").size();
		countmap.put("successList", suc);
		countmap.put("creditFailList",cre);
		countmap.put("timeFailList", time);
		countmap.put("preFailList", pre);
		countmap.put("alreadyRegisted", alrea);
		countmap.put("fail",cre + time + pre + alrea);
		countmap.put("success", suc);
		request.setAttribute("countmap", countmap);

		RequestDispatcher rd =request.getRequestDispatcher("/main/SubmitResult.jsp");
		rd.forward(request, response);
	}	
}
