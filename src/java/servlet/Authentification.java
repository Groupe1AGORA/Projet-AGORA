package servlet;

import entity.Client;
import entity.Employe;
import entity.Ouvrier;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

// The servlet class to login the user
@WebServlet(name = "Authentification", urlPatterns = {"/Authentification"})
public class Authentification extends HttpServlet {

    @PersistenceUnit
    private EntityManagerFactory emf;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            String pseudo = (String) request.getParameter("pseudo");
            String motdepasse = (String) request.getParameter("motdepasse");
            String type = (String) request.getParameter("type");

            HttpSession session = request.getSession();

            switch (type) {
                case "Employe": {
                    TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.pseudo = :id AND e.motdePasse = :pass", Employe.class);
                    query.setParameter("id", pseudo);
                    query.setParameter("pass", motdepasse);
                    
                    Employe e = query.getSingleResult();
                    
                    session.setAttribute("sessionUtilisateur", e.getPrenom());
                    session.setAttribute("sessionPoste", e.getPoste());
                    session.setAttribute("sessionID", e.getIdEmploye());

                    if (null != e.getPoste())
                    {
                        switch (e.getPoste()) {
                            case "Agent Administratif": {
                                request.getRequestDispatcher("InterfaceAA.jsp").forward(request, response);
                                break;
                            }
                            case "Service Marketing": {
                                request.getRequestDispatcher("CreerNote").forward(request, response);
                                break;
                            }
                            case "Service Technique": {
                                request.getRequestDispatcher("ListeDevis").forward(request, response);
                                break;
                            }
                            case "Service Comptable": {
                                request.getRequestDispatcher("ListeFacture").forward(request, response);
                                break;
                            }
                            default: {
                                request.getRequestDispatcher("Erreur.jsp").forward(request, response);
                                break;
                            }
                        }
                    }
                }
                case "Ouvrier": {
                    TypedQuery<Ouvrier> query = em.createQuery("SELECT o FROM Ouvrier o WHERE o.pseudo = :id AND o.motdepasse = :pass", Ouvrier.class);
                    query.setParameter("id", pseudo);
                    query.setParameter("pass", motdepasse);
                    Ouvrier o = query.getSingleResult();

                    session.setAttribute("sessionID", o.getIdOuvrier());
                    session.setAttribute("sessionUtilisateur", o.getPrenom());
                    session.setAttribute("sessionPoste", "Ouvrier");

                    //Forward to the jsp page for rendering
                    request.getRequestDispatcher("ListeDevis").forward(request, response);
                    break;
                }
                case "Client":
                    TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.pseudo = :id AND c.motdePasse = :pass", Client.class);
                    query.setParameter("id", pseudo);
                    query.setParameter("pass", motdepasse);
                    Client c = query.getSingleResult();

                    session.setAttribute("sessionID", c.getIdClient());
                    session.setAttribute("sessionUtilisateur", c.getPrenom());
                    session.setAttribute("sessionPoste", "Client");

                    request.getRequestDispatcher("ListeDevis").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("Erreur.jsp").forward(request, response);
                    break;
            }

        } catch (Exception ex) {
             request.setAttribute("messageErreur", "Votre pseudo, votre mot de passe ou votre type est invalide.");
             request.getRequestDispatcher("Erreur.jsp").forward(request, response);
        } finally {
            //close the em to release any resources held up by the persistence provider
            if (em != null) {
                em.close();
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Authentification servlet";
    }
    // </editor-fold>
}
