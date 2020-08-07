package com.recipe.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DispatcherServlet() {}
    /**
     * 서버가 수신한 각 요청에 해당하는 컨트롤러를 로드하여 컨트롤러들이 역할을 수행할 수 있도록 처리
     * @author CJK
     */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Controller controller = null;
		Properties env = new Properties();
		ServletContext sc = this.getServletContext();
		env.load(new FileInputStream(sc.getRealPath("controller.properties")));
		String forwardPath = "/fail.jsp";
		
		try {
			Class clazz = Class.forName(env.getProperty(request.getServletPath())); //요청에 해당하는 컨트롤러 클래스를 로드한다
			controller = (Controller) clazz.getMethod("getInstance", null).invoke(null, null); //컨트롤러는 싱글톤 패턴으로 구현하기 때문에, getInstance 메소드를 이용해 컨트롤러 객체를 가져온다.
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		if(controller != null) forwardPath = controller.execute(request, response); //컨트롤러 로드에 실패했다면 fail.jsp로 forward
		if(!"".equals(forwardPath)) forward(request, response, forwardPath);
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}

}
