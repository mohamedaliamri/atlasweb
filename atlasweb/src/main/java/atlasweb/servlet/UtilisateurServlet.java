package atlasweb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atlasweb.dao.RoleDao;
import atlasweb.dao.UtilisateurDao;
import atlasweb.model.Role;
import atlasweb.model.Utilisateur;

@WebServlet(name ="abc")
public class UtilisateurServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UtilisateurDao userDao;
	private RoleDao roleDao;

    public void init() {
        userDao = new UtilisateurDao();
        roleDao = new RoleDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	String name = request.getParameter("name");
    	String phoneNumber = request.getParameter("phonenumber");
    	String method = request.getParameter("signup");
    	if("true".equals(method)) {
    		// m�thode d'inscription
    		System.out.println(">> "+name+"/ "+phoneNumber+"/ "+method);
    		Role role = roleDao.getRole("ROLE_CLIENT");
    		Utilisateur utilisateur = new Utilisateur(phoneNumber, name, "AB"+phoneNumber+"CD", role);
    		userDao.saveUser(utilisateur);
    	}
    	response.sendRedirect("http://localhost:8080/atlasweb/material-dashboard-master/pages/sign-in.html");  
   //     doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println("Action = "+action);
        List < Utilisateur > listUser = userDao.getAllUser();
        listUser.forEach(obj -> {
        	System.out.println(obj.getPhoneNumber());
        	System.out.println(obj.getName());
        	System.out.println(obj.getRole());});
        /*     try {
            switch (action) {
                case "/new":
                showNewForm(request, response);
                    break;
                         case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break; 
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }*/
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Utilisateur > listUser = userDao.getAllUser();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//    throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Utilisateur existingUser = userDao.getUser(id);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//        request.setAttribute("user", existingUser);
//        dispatcher.forward(request, response);
//
//    }
    /*
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        Utilisateur newUser = new Utilisateur(name, email, country);
        userDao.saveUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        Utilisateur user = new Utilisateur(id, name, email, country);
        userDao.updateUser(user);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.deleteUser(id);
        response.sendRedirect("list");}*/
    
}