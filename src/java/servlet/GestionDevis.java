package servlet;

import entity.Client;
import entity.Commande;
import entity.Devis;
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
public class GestionDevis extends HttpServlet {

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
            String test = (String) session.getAttribute("sessionPoste");
            String test2 = (String) session.getAttribute("sessionUtilisateur");
            int id = (Integer) session.getAttribute("sessionID");
            switch (request.getServletPath()) {
                case "/CreerDevis": {

                    //Get the data from user's form
                    String prestation = (String) request.getParameter("prestation");
                    String commentaire = (String) request.getParameter("commentaire");

                    //Create a devis instance out of it
                    //begin a transaction
                    utx.begin();
                    //create an em. 
                    //Since the em is created inside a transaction, it is associsated with the transaction
                    em = emf.createEntityManager();
                    Client client = em.find(Client.class, id);

                    Devis devis = new Devis(prestation, commentaire, "Demande", client);
                    //persist the devis entity
                    em.persist(devis);
                    //commit transaction which will trigger the em to 
                    //commit newly created entity into database
                    utx.commit();

                    //Forward to ListeDevis servlet to list devis along with the newly created devis above
                    request.getRequestDispatcher("ListeDevis").forward(request, response);
                    break;
                }
                case "/ListeDevis": {
                    em = emf.createEntityManager();

                    if (null != test) //query for all the devis in database
                    {
                        switch (test) {
                            case "Ouvrier": {
                                TypedQuery<Devis> query = em.createQuery("SELECT d FROM Devis d WHERE d.ouvrieridOuvrier.idOuvrier = :test3", Devis.class);
                                query.setParameter("test3", id);
                                List devis = query.getResultList();
                                request.setAttribute("devisListe", devis);

                                TypedQuery<Commande> query2 = em.createQuery("SELECT c FROM Commande c WHERE c.devisidDevis.ouvrieridOuvrier.idOuvrier = :test3", Commande.class);
                                query2.setParameter("test3", id);
                                List commande = query2.getResultList();
                                request.setAttribute("commandeListe", commande);

                                TypedQuery<Commande> query3 = em.createQuery("SELECT c FROM Commande c WHERE c.devisidDevis.ouvrieridOuvrier.idOuvrier = :test3 AND c.etat = 'Planifiée'", Commande.class);
                                query3.setParameter("test3", id);
                                List commande2 = query3.getResultList();
                                request.setAttribute("commandeListe2", commande2);
                                
                                TypedQuery<Commande> query4 = em.createQuery("SELECT f FROM Facture f WHERE f.ouvrieridOuvrier.idOuvrier = :test3", Commande.class);
                                query4.setParameter("test3", id);
                                List facture = query4.getResultList();
                                request.setAttribute("factureListe", facture);

                                request.getRequestDispatcher("InterfaceO.jsp").forward(request, response);
                                break;
                            }
                            case "Client": {
                                TypedQuery<Devis> query = em.createQuery("SELECT d FROM Devis d WHERE d.clientidClient.idClient = :test3", Devis.class);
                                query.setParameter("test3", id);
                                List devis = query.getResultList();
                                request.setAttribute("devisListe", devis);

                                TypedQuery<Commande> query2 = em.createQuery("SELECT c FROM Commande c WHERE c.devisidDevis.clientidClient.idClient = :test3", Commande.class);
                                query2.setParameter("test3", id);
                                List commande = query2.getResultList();
                                request.setAttribute("commandeListe", commande);

                                request.getRequestDispatcher("InterfaceC.jsp").forward(request, response);
                                break;
                            }
                            case "Service Technique": {
                                
                                List devis = em.createQuery("select d from Devis d").getResultList();
                                request.setAttribute("devisListe", devis);
                                
                                List devis1 = em.createQuery("select d from Devis d WHERE d.etat = 'Demande'").getResultList();
                                request.setAttribute("devisListe1", devis1);

                                List devis2 = em.createQuery("select d from Devis d WHERE d.etat = 'Saisi'").getResultList(); // 
                                request.setAttribute("devisListe2", devis2);

                                List devis3 = em.createQuery("select d from Devis d WHERE d.etat = 'Ouvrier affecté'").getResultList();
                                request.setAttribute("devisListe3", devis3);                               
                                
                                // TEST
                                List commande = em.createQuery("select c from Commande c").getResultList();
                                request.setAttribute("commandeListe", commande);
                                // FIN TEST

                                request.getRequestDispatcher("ListeDevis.jsp").forward(request, response);
                                break;
                            }
                            default:
                                request.getRequestDispatcher("Erreur.jsp").forward(request, response);
                                break;
                        }
                    }

                    //Forward to the jsp page for rendering
                    //request.getRequestDispatcher("ListeDevis.jsp").forward(request, response);
                    break;
                }
                case "/ModifierDevis": {
                    if (request.getParameter("commentaire") == null) {
                        em = emf.createEntityManager();
                        
                         int idDevis = Integer.parseInt(request.getParameter("idDevis"));
                        //query for all the employe in database
                        TypedQuery<Devis> query = em.createQuery("SELECT d FROM Devis d WHERE d.idDevis = :id", Devis.class);
                        query.setParameter("id", idDevis);
                        //query for all the devis in database
                        List devis = query.getResultList();
                        request.setAttribute("devisListe", devis);

                        //Forward to the jsp page for rendering
                        request.getRequestDispatcher("ModifierDevis.jsp").forward(request, response);
                    } else {
                        //Get the data from user's form
                        int idDevis = Integer.parseInt(request.getParameter("idDevis"));
                        String prestation = (String) request.getParameter("prestation");
                        String commentaire = (String) request.getParameter("commentaire");
                        String avis = (String) request.getParameter("avis");
                        int nbheures = Integer.parseInt(request.getParameter("nbheures"));

                        //begin a transaction
                        utx.begin();
                        //create an em. 
                        //Since the em is created inside a transaction, it is associsated with the transaction
                        em = emf.createEntityManager();
                        //find the devis entity
                        Devis devis = em.find(Devis.class, idDevis);

                        //if(!"".equals(nom)){devis.setNom(nom);}
                        if (!"".equals(prestation)) {
                            devis.setPrestation(prestation);
                        }
                        if (!"".equals(commentaire)) {
                            devis.setCommentaire(commentaire);
                        }
                        if (!"".equals(avis)) {
                            devis.setAvis(avis);
                        }
                        if (!"".equals(nbheures)) {
                            devis.setNbheures(nbheures);
                        }

                        if (!"".equals(devis.getAvis())) {
                            devis.setEtat("Saisi");
                        }

                        //commit transaction which will trigger the em to 
                        //commit newly edited entity into database
                        utx.commit();
                        request.getRequestDispatcher("ListeDevis").forward(request, response);

                    }
                    break;
                }

                case "/SupprimerDevis": {
                    //Get the data from user's form
                    int idDevis = Integer.parseInt(request.getParameter("idDevis"));

                    //begin a transaction
                    utx.begin();
                    //create an em. 
                    //Since the em is created inside a transaction, it is associsated with the transaction
                    em = emf.createEntityManager();
                    //persist the devis entity
                    Devis devis = em.find(Devis.class, idDevis);
                    em.remove(devis);
                    //commit transaction which will trigger the em to 
                    //commit newly created entity into database
                    utx.commit();

                    //Forward to ListeDevis servlet to list devis along with the newly created devis above
                    request.getRequestDispatcher("ListeDevis").forward(request, response);
                    break;
                }

                case "/ModifierEtat": {
                    //Get the data from user's form
                    int idDevis = Integer.parseInt(request.getParameter("idDevis"));

                    //begin a transaction
                    utx.begin();
                    //create an em. 
                    //Since the em is created inside a transaction, it is associsated with the transaction
                    em = emf.createEntityManager();
                    //persist the devis entity
                    Devis devis = em.find(Devis.class, idDevis);
                    devis.setEtat("Accepté");


                    Commande commande = new Commande("Planifiée", devis.getPrix(), devis);

                    em.persist(commande);

                    devis.setCommandeidCommande(commande);

                    //commit transaction which will trigger the em to 
                    //commit newly created entity into database
                    utx.commit();

                    //Forward to ListeDevis servlet to list devis along with the newly created devis above
                    request.getRequestDispatcher("ListeDevis").forward(request, response);
                    break;
                }

                case "/ModifierEtatC": {
                    int idCommande = Integer.parseInt(request.getParameter("idCommande"));

                    utx.begin();
                    em = emf.createEntityManager();

                    Commande commande = em.find(Commande.class, idCommande);
                    commande.setEtat("Travaux terminés");

                    utx.commit();

                    request.getRequestDispatcher("ListeDevis").forward(request, response);
                    break;
                }

                case "/ModifierEtatR": {
                    int idDevis = Integer.parseInt(request.getParameter("idDevis"));

                    utx.begin();
                    em = emf.createEntityManager();

                    Devis devis = em.find(Devis.class, idDevis);
                    devis.setEtat("Refusé");

                    utx.commit();

                    request.getRequestDispatcher("ListeDevis").forward(request, response);
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
