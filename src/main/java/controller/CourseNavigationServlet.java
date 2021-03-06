package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;

/**
 * Servlet implementation class CourseNavigationServlet
 */
@WebServlet("/CourseNavigationServlet")
public class CourseNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseNavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/ViewAllCoursesServlet";
		
		CourseHelper dao = new CourseHelper();
		String act = request.getParameter("doThisToCourse");
		
		if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Course courseToDelete = dao.searchForEntryById(tempId);
				dao.deleteEntry(courseToDelete);
			}
			catch(NumberFormatException e){
				System.out.println("Must select a course to delete");
			}
		} 
		else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Course courseToEdit = dao.searchForEntryById(tempId);
				request.setAttribute("courseToEdit", courseToEdit);
				path = "/edit-course.jsp";
			}
			catch(NumberFormatException e) {
				System.out.println("Must select a course to edit");
			}
		} 
		else if (act.equals("home")){
			path = "/index.html";
		}
		
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
