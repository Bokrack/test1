package spms.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MySqlMemberDao;
import spms.vo.Member;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("viewUrl", "/member/MemberAdd.jsp");
		/*
		RequestDispatcher rd = request.getRequestDispatcher(
				"/member/MemberAdd.jsp");
		rd.forward(request, response);*/
/*		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>회원 등록</title></head>");
		out.println("<body><h1>회원 등록</h1>");
		out.println("<form action='add' method='post'>");
		out.println("이름: <input type='text' name='name'><br>");
		out.println("이메일: <input type='text' name='email'><br>");
		out.println("암호: <input type='password' name='password'><br>");
		out.println("<input type='submit' value='추가'>");
		out.println("<input type='reset' value='취소'>");
		out.println("</form>");
		out.println("</body></html>");*/
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// CharacterEncodingFilter에서 처리
		//request.setCharacterEncoding("UTF-8");
		
		PreparedStatement stmt = null;

		try {
			ServletContext sc = this.getServletContext();

			MySqlMemberDao memberDao = (MySqlMemberDao) sc.getAttribute("memberDao");
			Member member = (Member)request.getAttribute("member");
			memberDao.addMember(member);
			request.setAttribute("viewUrl", "redirect:list.do");
			/*
			memberDao.addMember(request.getParameter("email"), request.getParameter("password"),
					request.getParameter("name"));
			request.setAttribute("viewUrl", "redirect:list.do");*/
			/*
			response.sendRedirect("list");*/
		} catch (Exception e) {
			throw new ServletException(e);
			
		}
	}
}
