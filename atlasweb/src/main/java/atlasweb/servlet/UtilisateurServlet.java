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

@WebServlet(name = "utilisateurServlet")
public class UtilisateurServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String url_app = "http://localhost:8080/atlasweb/material-dashboard-master/pages";
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
		String method = request.getParameter("method");
		HttpSession session = request.getSession();
		session.setAttribute("error_msg", null);
		if ("signup".equals(method)) {
			// m�thode d'inscription
			String name = request.getParameter("name");
			String phoneNumber = request.getParameter("phonenumber");
			// phoneNumber = "216".concat(phoneNumber);
			String code = generateCode(phoneNumber);
			Role role = roleDao.getRole("ROLE_CLIENT");
			Utilisateur utilisateur = new Utilisateur(phoneNumber, name, code, role);
			userDao.saveUser(utilisateur);
			response.sendRedirect(url_login);
		} else if ("signin".equals(method)) {
			// Authentification ok
			String phoneNumber = request.getParameter("phonenumber");
			Utilisateur utilisateur = userDao.getUser(phoneNumber);
			String codeCnx = request.getParameter("codeCnx");
			System.out.println(utilisateur.getCode());
			System.out.println(codeCnx);
			if (utilisateur != null && utilisateur.getCode().equals(codeCnx)) {
				response.sendRedirect(url_acceuil);
			} else {
				// Authentification
				session.setAttribute("error_msg", "Code incorrect");
				response.sendRedirect(url_login);
			}
		} else if ("acheterBracelet".equals(method)) {
			String prenom = request.getParameter("prenom");
			String dateDeNaissance = request.getParameter("dateDeNaissance");
			String moins1metre = request.getParameter("flexRadioDefault");
			String plus1metre = request.getParameter("flexRadioDefault2");
			if (prenom != null && dateDeNaissance != null) {
				System.out.println("prenom" + prenom);
				System.out.println("dateDeNaissance" + dateDeNaissance);
				int i = 0;
				double prix = 0;
				if (moins1metre != null) {
					System.out.println("moins1metre " + moins1metre);
					String bleu1 = request.getParameter("bleu1");
					if (bleu1 != null) {
						System.out.println("bleu1" + bleu1);
						i++;
					}
					String vert1 = request.getParameter("vert1");
					if (vert1 != null) {
						System.out.println("vert1 " + vert1);
						i++;
					}
					String jaune1 = request.getParameter("jaune1");
					if (jaune1 != null) {
						System.out.println("jaune1 " + jaune1);
						i++;
					}
					String chaussete = request.getParameter("flexRadioDefaultT");
					if (chaussete != null) {
						System.out.println("achetere chaussette");
					}
					if (i == 1) {
						if (chaussete != null) {
							System.out.println("prix = 15 DINAR");
							prix = 15;
						} else {
							System.out.println("prix = 10 DINAR");
							prix = 10;
						}
					} else if (i == 3) {
						if (chaussete != null) {
							System.out.println("prix = 29 DINAR");
							prix = 29;
						} else {
							System.out.println("prix = 24 DINAR");
							prix = 24;
						}
					} else if (i == 2) {
						if (chaussete != null) {
							System.out.println("prix = 22 DINAR");
							prix = 22;
						} else {
							System.out.println("prix = 17 DINAR");
							prix = 17;
						}
					}

				} else if (plus1metre != null) {
					System.out.println("plus1metre" + plus1metre);
					String bleu2 = request.getParameter("bleu2");
					int j = 0;
					if (bleu2 != null) {
						System.out.println("bleu2" + bleu2);
						j++;
					}
					String vert2 = request.getParameter("vert2");
					if (vert2 != null) {
						System.out.println("vert2" + vert2);
						j++;
					}
					String jaune2 = request.getParameter("jaune2");
					if (jaune2 != null) {
						System.out.println("jaune2 " + jaune2);
						j++;
					}
					String rouge2 = request.getParameter("rouge2");
					if (rouge2 != null) {
						System.out.println("rouge2" + rouge2);
						j++;
					}
					String orange2 = request.getParameter("orange2");
					if (orange2 != null) {
						System.out.println("orange2 " + orange2);
						j++;
					}
					String chaussete = request.getParameter("flexRadioDefaultT");
					if (chaussete != null) {
						System.out.println("achetere chaussette");
					}
					if (j == 1) {
						if (bleu2 != null) {
							prix = 10;
						}

						if (vert2 != null) {
							prix = 14;
						}
						if (jaune2 != null) {
							prix = 14;
						}
						if (rouge2 != null) {
							prix = 20;
						}
						if (orange2 != null) {
							prix = 10;
						}
						if (chaussete != null) {
							prix = prix + 5;
						}
					} else if (i == 5) {
						prix = 40;
						if (chaussete != null && rouge2 != null) {
							prix = prix + 5;
						}
					} else if (i == 4 && rouge2 == null) {
						prix = 30;
						if (chaussete != null) {
							prix = prix + 5;
						}
					} else if (i == 3 && rouge2 == null) {
						if (bleu2 != null && orange2 != null && vert2 != null) {
							prix = 25;
						} else if (bleu2 != null && orange2 != null && jaune2 != null) {
							prix = 25;
						} else if (orange2 != null && jaune2 != null && vert2 != null) {
							prix = 28;
						} else if (bleu2 != null && vert2 != null && jaune2 != null) {
							prix = 25;
						}
						if (chaussete != null) {
							prix = prix + 5;
						}
					} else if (i == 2 && rouge2 == null) {
						if (bleu2 != null && orange2 != null) {
							prix = 17;
						} else if (bleu2 != null && vert2 != null) {
							prix = 20;
						} else if (bleu2 != null && jaune2 != null) {
							prix = 20;
						} else if (orange2 != null && vert2 != null) {
							prix = 20;
						} else if (orange2 != null && jaune2 != null) {
							prix = 20;
						} else if (vert2 != null && jaune2 != null) {
							prix = 24;
						}
						if (chaussete != null) {
							prix = prix + 5;
						}
					}
				}
				response.sendRedirect(url_acceuil);
			}

		}

	}

	private String generateCode(String phoneNumber) {
		String code = "";
		try {
			code = phoneNumber.substring(2, 5);
			code = code + phoneNumber.substring(0, 2) + code.substring(1, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		List<Utilisateur> listUser = userDao.getAllUser();
		listUser.forEach(obj -> {
			System.out.println(obj.getRole());
		});
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Utilisateur> listUser = userDao.getAllUser();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}



}