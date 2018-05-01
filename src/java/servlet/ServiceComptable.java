package servlet;

import entity.Client;
import entity.Commande;
import entity.Devis;
import entity.Facture;
import entity.Ouvrier;
import java.io.*;
import java.util.Collection;
import java.util.Iterator;
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
public class ServiceComptable extends HttpServlet {

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
                case "/RelancerClient": {
                    em = emf.createEntityManager();
                    TypedQuery<Commande> query = em.createQuery("SELECT c FROM Commande c WHERE c.etat = 'Travaux terminés' ORDER BY c.devisidDevis.clientidClient.nom", Commande.class);
                    List commande = query.getResultList();
                    request.setAttribute("commandeListe", commande);

                    request.getRequestDispatcher("RelancerClient.jsp").forward(request, response);
                    break;
                }
                case "/RelancerCommande": {
                    em = emf.createEntityManager();

                    int idCommande = Integer.parseInt(request.getParameter("idCommande"));

                    Commande commande = em.find(Commande.class, idCommande);
                    Client client = commande.getDevisidDevis().getClientidClient();

                    // Manque de temps, système de relancer à implémenter ici
                    
                    request.getRequestDispatcher("RelancerClient.jsp").forward(request, response);
                    break;
                }
                case "/ListeFacture": {
                    em = emf.createEntityManager();
                    TypedQuery<Commande> query = em.createQuery("SELECT c FROM Commande c WHERE c.etat = 'Commande soldée'", Commande.class);
                    List commande = query.getResultList();
                    request.setAttribute("commandeListe", commande);

                    request.getRequestDispatcher("CreerFacture.jsp").forward(request, response);
                    break;
                }
                case "/CreerFacture": {
                    utx.begin(); // Début de la transaction
                    em = emf.createEntityManager(); // Créé un entity manager, comme il est créé dans la transaction, il est associé à cette dernière

                    int idOuvrier = Integer.parseInt(request.getParameter("idOuvrier")); // Récupère idOuvrier 
                    
                    Ouvrier ouvrier = em.find(Ouvrier.class, idOuvrier); // Recupère l'ouvrier "ouvrier" à partir de idOuvrier
                    
                    TypedQuery<Commande> query = em.createQuery("SELECT c FROM Commande c WHERE c.devisidDevis.ouvrieridOuvrier.idOuvrier = :id "
                            + "AND c.etat = 'Commande soldée'", Commande.class);
                    // Requête pour les commandes à facturer de l'ouvrier (ç-à-d toutes les commandes soldées)
                    query.setParameter("id", idOuvrier); // Set du paramètre de requête :id
                    List commande = query.getResultList(); // Récupération du résultat de la requête
                    
                    Collection<Commande> c = commande; // Assigne la liste de commandes "commande" à la collection c
                    Iterator<Commande> iterator = c.iterator(); // Création d'un itérateur pour la collection C
                    double montantfacture = 0; // Initialisation du double "montantfacture"
                    while (iterator.hasNext()) { // Boucle d'itération
                        int idCommande = iterator.next().getIdCommande(); // Récupère idCommande
                        Commande commande1 = em.find(Commande.class, idCommande); // Recupère la commande "commande1" à partir de idCommande
                        commande1.setEtat("Commande facturée"); // Set l'état "Commande facturée" à la commande "commande1"
                        montantfacture = montantfacture + commande1.getCout(); // Prise en compte du coût de la commande dans le montant de la facture
                    }
                    
                    montantfacture = montantfacture * 0.9; // Retranche 10% de frais de gestion et de marketing
                    Facture facture = new Facture(montantfacture, ouvrier); // Création de la facture
                    em.persist(facture); // Persiste la facture
                   
                    utx.commit(); // Commit

                    request.getRequestDispatcher("ListeFacture").forward(request, response); // Retourne sur /ListeFacture qui enverra 
                    // à son tour l'utilisateur vers CreerFacture.jsp où le changement sera affiché (les commandes venant d'être facturées ne seront plus visibles)
                    break;
                }
                case "/ListeCommande": {

                    em = emf.createEntityManager();

                    List commande2 = em.createQuery("select c from Commande c WHERE c.etat = 'Travaux terminés'").getResultList();
                    request.setAttribute("commandeListe", commande2);

                    request.getRequestDispatcher("SolderCommande.jsp").forward(request, response);

                    break;
                }
                case "/SolderCommande": {

                    int idCommande = Integer.parseInt(request.getParameter("idCommande"));

                    utx.begin();
                    em = emf.createEntityManager();
                    Commande commande = em.find(Commande.class, idCommande);
                    commande.setEtat("Commande soldée");

                    utx.commit();

                    request.getRequestDispatcher("ListeCommande").forward(request, response);
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
