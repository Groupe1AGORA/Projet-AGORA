package servlet;

import entity.Employe;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.annotation.Resource;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

// The servlet class to detete employe from database
public class GestionEmploye extends HttpServlet {

    @PersistenceUnit
    //The emf corresponding to 
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        try {

            switch (request.getServletPath()) {
                case "/CreerEmploye": {
                    //Get the data from user's form
                    String pseudo = (String) request.getParameter("pseudo");
                    String motdepasse = (String) request.getParameter("motdepasse");
                    String nom = (String) request.getParameter("nom");
                    String prenom = (String) request.getParameter("prenom");
                    String telephone = (String) request.getParameter("telephone");
                    String mail = (String) request.getParameter("mail");
                    String adresse = (String) request.getParameter("adresse");
                    String poste = (String) request.getParameter("poste");
                    
                    if (!mail.contains("@"))
                    {
                        request.setAttribute("messageErreur", "Le mail est incorrect");
                        request.getRequestDispatcher("Erreur.jsp").forward(request, response);
                    }

                    //Create a employe instance out of it
                    Employe employe = new Employe(pseudo, motdepasse, nom, prenom, telephone, mail, adresse, poste);

                    //begin a transaction
                    utx.begin();
                    //create an em. 
                    //Since the em is created inside a transaction, it is associsated with the transaction
                    em = emf.createEntityManager();
                    //persist the devis entity

                    em.persist(employe);

                    //commit transaction which will trigger the em to 
                    //commit newly created entity into database
                    utx.commit();

                    //Forward to Listeemploye servlet to list employe along with the newly created employe above
                    request.getRequestDispatcher("ListeEmploye").forward(request, response);
                    break;
                }
                case "/ListeEmploye": {

                    em = emf.createEntityManager();
                    //query for all the employe in database
                    List employe = em.createQuery("select e from Employe e").getResultList();
                    request.setAttribute("employeListe", employe);

                    //Forward to the jsp page for rendering
                    request.getRequestDispatcher("ListeEmploye.jsp").forward(request, response);
                    break;
                }
                case "/ModifierEmploye": {
                    if (request.getParameter("poste") == null) {
                        em = emf.createEntityManager();
                        int idEmploye = Integer.parseInt(request.getParameter("idEmploye"));
                        //query for all the employe in database
                        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.idEmploye = :id", Employe.class);
                        query.setParameter("id", idEmploye);

                        List employe = query.getResultList();
                        request.setAttribute("employeListe", employe);

                        //Forward to the jsp page for rendering
                        request.getRequestDispatcher("ModifierEmploye.jsp").forward(request, response);
                    } else {

                        //Get the data from user's form
                        int idEmploye = Integer.parseInt(request.getParameter("idEmploye"));
                        String pseudo = (String) request.getParameter("pseudo");
                        String motdepasse = (String) request.getParameter("motdepasse");
                        String nom = (String) request.getParameter("nom");
                        String prenom = (String) request.getParameter("prenom");
                        String telephone = (String) request.getParameter("telephone");
                        String mail = (String) request.getParameter("mail");
                        String adresse = (String) request.getParameter("adresse");
                        String poste = (String) request.getParameter("poste");

                        //begin a transaction
                        utx.begin();
                        //create an em. 
                        //Since the em is created inside a transaction, it is associsated with the transaction
                        em = emf.createEntityManager();
                        //find the employe entity

                        Employe employe = em.find(Employe.class, idEmploye);

                        employe.setPseudo(pseudo);
                        employe.setMotdePasse(motdepasse);
                        employe.setNom(nom);
                        employe.setPrenom(prenom);
                        employe.setTelephone(telephone);
                        employe.setMail(mail);
                        employe.setAdresse(adresse);
                        employe.setPoste(poste);

                        //commit transaction which will trigger the em to 
                        //commit newly edited entity into database
                        utx.commit();
                        request.getRequestDispatcher("ListeEmploye").forward(request, response);

                    }
                    break;
                }

                case "/SupprimerEmploye": {
                    //Get the data from user's form
                    int idEmploye = Integer.parseInt(request.getParameter("idEmploye"));

                    //begin a transaction
                    utx.begin();
                    //create an em. 
                    //Since the em is created inside a transaction, it is associsated with the transaction
                    em = emf.createEntityManager();
                    //find and remove the devis entity
                    Employe employe = em.find(Employe.class, idEmploye);
                    em.remove(employe);
                    //commit transaction which will trigger the em to 
                    //commit newly deleted entity from database
                    utx.commit();

                    //Forward to Listeemploye servlet to list employe along with the newly deleted devis above
                    request.getRequestDispatcher("ListeEmploye").forward(request, response);
                    break;
                }
                default: {

                    break;
                }
            }

        } catch (Exception ex) {
            throw new ServletException(ex);
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
        return "CreerDevis servlet";
    }
    // </editor-fold>
}
