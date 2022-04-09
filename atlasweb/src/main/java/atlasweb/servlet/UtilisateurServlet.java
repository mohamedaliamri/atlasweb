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
import javax.servlet.http.HttpSession;

import atlasweb.dao.RoleDao;
import atlasweb.dao.UtilisateurDao;
import atlasweb.model.Role;
import atlasweb.model.Utilisateur;

@WebServlet(name ="utilisateurServlet")
public class UtilisateurServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String url_app ="http://localhost:8080/atlasweb/material-dashboard-master/pages";
	private static final String url_login = url_app + "/sign-in.jsp";
	private static final String url_acceuil = url_app + "/dashboard.jsp";
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
    	//phoneNumber = "216".concat(phoneNumber);
    	String code = generateCode(phoneNumber);
    	String method = request.getParameter("method");
    	HttpSession session = request.getSession();
    	session.setAttribute("error_msg", null);
    	if("signup".equals(method)) {
    		// m�thode d'inscription
    		Role role = roleDao.getRole("ROLE_CLIENT");
    		Utilisateur utilisateur = new Utilisateur(phoneNumber, name, code, role);
    		userDao.saveUser(utilisateur);
    		response.sendRedirect(url_login);  
    	}else if("signin".equals(method)) {
    		//Authentification ok
    		Utilisateur utilisateur = userDao.getUser(phoneNumber);
    		String codeCnx = request.getParameter("codeCnx");
    		System.out.println(utilisateur.getCode());
    		System.out.println(codeCnx);
    		if(utilisateur != null && utilisateur.getCode().equals(codeCnx)) {
    			response.sendRedirect(url_acceuil);  
    		}else {
    			//Authentification
    			session.setAttribute("error_msg", "Code incorrect");
    			response.sendRedirect(url_login);  
    		}
    	}
    	
    }

    private String generateCode(String phoneNumber) {
    	String code = "";
		try {
			code = phoneNumber.substring(2,5);
			code = code + phoneNumber.substring(0,2) + code.substring(1,2);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
        List < Utilisateur > listUser = userDao.getAllUser();
        listUser.forEach(obj -> {
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