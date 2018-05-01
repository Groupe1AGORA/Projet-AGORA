package servlet;

import entity.Ouvrier;
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
public class GestionOuvrier extends HttpServlet {

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
            HttpSession session = request.getSession();
            String poste = (String) session.getAttribute("sessionPoste");

            switch (request.getServletPath()) {

                case "/CreerOuvrier": {
                    //Get the data from user's form
                    String pseudo = (String) request.getParameter("pseudo");
                    String motdepasse = (String) request.getParameter("motdepasse");
                    String nom = (String) request.getParameter("nom");
                    String prenom = (String) request.getParameter("prenom");
                    String telephone = (String) request.getParameter("telephone");
                    String mail = (String) request.getParameter("mail");
                    String adresse = (String) request.getParameter("adresse");
                    String prestation = (String) request.getParameter("prestation");
                    
                    if (!mail.contains("@"))
                    {
                        request.setAttribute("messageErreur", "Le mail est incorrect");
                        request.getRequestDispatcher("Erreur.jsp").forward(request, response);
                    }

                    //Create a ouvrier instance out of it
                    Ouvrier ouvrier = new Ouvrier(pseudo, motdepasse, nom, prenom, telephone, mail, adresse, prestation);

                    //begin a transaction
                    utx.begin();
                    //create an em. 
                    //Since the em is created inside a transaction, it is associsated with the transaction
                    em = emf.createEntityManager();
                    //persist the devis entity
                    em.persist(ouvrier);
                    //commit transaction which will trigger the em to 
                    //commit newly created entity into database
                    utx.commit();

                    //Forward to ListeOuvrier servlet to list ouvrier along with the newly created ouvrier above
                    request.getRequestDispatcher("ListeOuvrier").forward(request, response); //ModifierOuvrier ?
                    break;
                }
                case "/ListeOuvrier": {

                    em = emf.createEntityManager();

                    //query for all the ouvrier in database
                    List ouvrier = em.createQuery("select o from Ouvrier o").getResultList();
                    request.setAttribute("ouvrierListe", ouvrier);

                    //Forward to the jsp page for rendering
                    if ("Ouvrier".equals(poste)) {
                        request.getRequestDispatcher("InterfaceO.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("ListeOuvrier.jsp").forward(request, response);
                    }
                    break;
                }
                case "/ModifierOuvrier": {
                    if (request.getParameter("telephone") == null) {
                        em = emf.createEntityManager();

                        TypedQuery<Ouvrier> query = em.createQuery("SELECT o FROM Ouvrier o WHERE o.idOuvrier = :id", Ouvrier.class);
                        if ("Ouvrier".equals(poste)) {
                            int idOuvrier = (Integer) session.getAttribute("sessionID");
                            query.setParameter("id", idOuvrier);
                        } else {
                            int idOuvrier = Integer.parseInt(request.getParameter("idOuvrier"));
                            query.setParameter("id", idOuvrier);
                        }

                        //query for all the employe in database
                        List ouvrier = query.getResultList();
                        request.setAttribute("ouvrierListe", ouvrier);
                        //Forward to the jsp page for rendering

                        if ("Ouvrier".equals(poste)) {
                            request.getRequestDispatcher("ModifierO.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("ModifierOuvrier.jsp").forward(request, response);
                        }
                    } else {
                        //Get the data from user's form
                        int idOuvrier = Integer.parseInt(request.getParameter("idOuvrier"));
                        String pseudo = (String) request.getParameter("pseudo");
                        String motdepasse = (String) request.getParameter("motdepasse");
                        String nom = (String) request.getParameter("nom");
                        String prenom = (String) request.getParameter("prenom");
                        String telephone = (String) request.getParameter("telephone");
                        String mail = (String) request.getParameter("mail");
                        String adresse = (String) request.getParameter("adresse");
                        String prestation = (String) request.getParameter("prestation");
                        int tarif = Integer.parseInt(request.getParameter("tarif"));

                        //begin a transaction
                        utx.begin();
                        //create an em. 
                        //Since the em is created inside a transaction, it is associsated with the transaction
                        em = emf.createEntityManager();
                        //find the ouvrier entity
                        Ouvrier ouvrier = em.find(Ouvrier.class, idOuvrier);
                        if (!"".equals(pseudo)) {
                            ouvrier.setPseudo(pseudo);
                        }
                        if (!"".equals(motdepasse)) {
                            ouvrier.setMotdepasse(motdepasse);
                        }
                        if (!"".equals(nom)) {
                            ouvrier.setNom(nom);
                        }
                        if (!"".equals(prenom)) {
                            ouvrier.setPrenom(prenom);
                        }
                        if (!"".equals(telephone)) {
                            ouvrier.setTelephone(telephone);
                        }
                        if (!"".equals(mail)) {
                            ouvrier.setMail(mail);
                        }
                        if (!"".equals(adresse)) {
                            ouvrier.setAdresse(adresse);
                        }
                        if (!"".equals(prestation)) {
                            ouvrier.setPrestation(prestation);
                        }
                        if (!"".equals(tarif)) {
                            ouvrier.setTarif(tarif);
                        }
                        //commit transaction which will trigger the em to 
                        //commit newly edited entity into database
                        utx.commit();
                        
                         if ("Ouvrier".equals(poste)) {
                            request.getRequestDispatcher("ListeDevis").forward(request, response);
                        } else {
                            request.getRequestDispatcher("ListeOuvrier").forward(request, response);
                        }
                    }
                    break;
                }

                case "/SupprimerOuvrier": {
                    //Get the data from user's form
                    int idOuvrier = Integer.parseInt(request.getParameter("idOuvrier"));

                    //begin a transaction
                    utx.begin();
                    //create an em. 
                    //Since the em is created inside a transaction, it is associsated with the transaction
                    em = emf.createEntityManager();
                    //find and remove the devis entity
                    Ouvrier ouvrier = em.find(Ouvrier.class, idOuvrier);
                    em.remove(ouvrier);
                    //commit transaction which will trigger the em to 
                    //commit newly deleted entity from database
                    utx.commit();

                    //Forward to ListeOuvrier servlet to list ouvrier along with the newly deleted devis above
                    request.getRequestDispatcher("ListeOuvrier").forward(request, response);
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
