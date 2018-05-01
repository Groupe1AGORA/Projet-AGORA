package servlet;

import entity.Client;
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
public class GestionClient extends HttpServlet {

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
                case "/CreerClient": {
                    //Get the data from user's form
                    String pseudo = (String) request.getParameter("pseudo");
                    String motdepasse = (String) request.getParameter("motdepasse");
                    String nom = (String) request.getParameter("nom");
                    String prenom = (String) request.getParameter("prenom");
                    String telephone = (String) request.getParameter("telephone");
                    String mail = (String) request.getParameter("mail");
                    String adresse = (String) request.getParameter("adresse");
                    
                    if (!mail.contains("@"))
                    {
                        request.setAttribute("messageErreur", "Le mail est incorrect");
                        request.getRequestDispatcher("Erreur.jsp").forward(request, response);
                    }
                    
                    //Create a client instance out of it
                    Client client = new Client(pseudo, motdepasse, nom, prenom, telephone, mail, adresse);

                    //begin a transaction
                    utx.begin();
                    //create an em. 
                    //Since the em is created inside a transaction, it is associsated with the transaction
                    em = emf.createEntityManager();
                    //persist the devis entity

                    em.persist(client);

                    //commit transaction which will trigger the em to 
                    //commit newly created entity into database
                    utx.commit();
                    HttpSession session = request.getSession();
                    if (session.getAttribute("sessionUtilisateur") == null) {
                        request.setAttribute("message", "Votre compte a été créé. Vous pouvez maintenant vous authentifier.");
                        request.getRequestDispatcher("Authentification.jsp").forward(request, response);
                    }
                    //Forward to Listeclient servlet to list client along with the newly created client above
                    request.getRequestDispatcher("ListeClient").forward(request, response);
                    break;
                }
                case "/ListeClient": {

                    em = emf.createEntityManager();

                    //query for all the employe in database
                    List employe = em.createQuery("select c from Client c").getResultList();
                    request.setAttribute("clientListe", employe);

                    //Forward to the jsp page for rendering
                    request.getRequestDispatcher("ListeClient.jsp").forward(request, response);
                    break;
                }
                case "/ModifierClient": {
                    if (request.getParameter("pseudo") == null) {
                        em = emf.createEntityManager();
                        int idClient = Integer.parseInt(request.getParameter("idClient"));
                        //query for all the client in database
                        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.idClient = :id", Client.class);
                        query.setParameter("id", idClient);

                        List client = query.getResultList();
                        request.setAttribute("clientListe", client);

                        //Forward to the jsp page for rendering
                        request.getRequestDispatcher("ModifierClient.jsp").forward(request, response);
                    } else {

                        //Get the data from user's form
                        int idClient = Integer.parseInt(request.getParameter("idClient"));
                        String pseudo = (String) request.getParameter("pseudo");
                        String motdepasse = (String) request.getParameter("motdepasse");
                        String nom = (String) request.getParameter("nom");
                        String prenom = (String) request.getParameter("prenom");
                        String telephone = (String) request.getParameter("telephone");
                        String mail = (String) request.getParameter("mail");
                        String adresse = (String) request.getParameter("adresse");

                        //begin a transaction
                        utx.begin();
                        //create an em. 
                        //Since the em is created inside a transaction, it is associsated with the transaction
                        em = emf.createEntityManager();
                        //find the client entity
                        Client client = em.find(Client.class, idClient);
                        if (!"".equals(pseudo)) {
                            client.setPseudo(pseudo);
                        }
                        if (!"".equals(motdepasse)) {
                            client.setMotdePasse(motdepasse);
                        }
                        if (!"".equals(nom)) {
                            client.setNom(nom);
                        }
                        if (!"".equals(prenom)) {
                            client.setPrenom(prenom);
                        }
                        if (!"".equals(telephone)) {
                            client.setTelephone(telephone);
                        }
                        if (!"".equals(mail)) {
                            client.setMail(mail);
                        }
                        if (!"".equals(adresse)) {
                            client.setAdresse(adresse);
                        }

                        //commit transaction which will trigger the em to 
                        //commit newly edited entity into database
                        utx.commit();
                        request.getRequestDispatcher("ListeClient").forward(request, response);
                    }
                    break;
                }

                case "/SupprimerClient": {
                    //Get the data from user's form
                    int idClient = Integer.parseInt(request.getParameter("idClient"));

                    //begin a transaction
                    utx.begin();
                    //create an em. 
                    //Since the em is created inside a transaction, it is associsated with the transaction
                    em = emf.createEntityManager();
                    //find and remove the devis entity
                    Client client = em.find(Client.class, idClient);
                    em.remove(client);
                    //commit transaction which will trigger the em to 
                    //commit newly deleted entity from database
                    utx.commit();

                    //Forward to Listeclient servlet to list client along with the newly deleted devis above
                    request.getRequestDispatcher("ListeClient").forward(request, response);
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
