package servlet;

import entity.Devis;
import entity.Ouvrier;
import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.annotation.Resource;
import javax.persistence.TypedQuery;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@WebServlet(name = "AffecterOuvrier", urlPatterns = {"/AffecterOuvrier"})
public class AffecterOuvrier extends HttpServlet {

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
            throws ServletException, NotSupportedException, SystemException, IOException {
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        try {
            if (request.getParameter("idOuvrier") == null) { 
            // Si /AffecterOuvrier est appelé et que idOuvrier est null, c'est que l'utilisateur n'était pas sur AffecterOuvrier.jsp 
            // Le programme fait alors une simple opération pour récupérer le devis auquel on veut affecter un ouvrier, ainsi 
            // qu'une requête pour les ouvriers qui peuvent le réaliser.
                em = emf.createEntityManager();

                int id = Integer.parseInt(request.getParameter("idDevis")); // Récupère idDevis

                Devis d = em.find(Devis.class, id); // Recupère le devis "d" à partir de idDevis
                request.setAttribute("d", d); // Set le devis "d" en attribut de requête

                TypedQuery<Ouvrier> query = em.createQuery("SELECT o FROM Ouvrier o WHERE o.prestation = :presta "
                        + "ORDER BY o.moyennefinale DESC", Ouvrier.class); // Requête pour les ouvriers disponibles
                query.setParameter("presta", d.getPrestation()); // Set du paramètre :presta
                List ouvrier = query.getResultList(); // Récupération du résultat de la requête
                request.setAttribute("ouvrierListe", ouvrier); // Set la liste d'ouvriers "ouvrier" en attribut de requête

                request.getRequestDispatcher("AffecterOuvrier.jsp").forward(request, response); // La requête est envoyé vers 
                // AffecterOuvrier.jsp, afin de permettre l'affectation de l'ouvrier
            } else { // Sinon, l'utilisateur était déjà sur AffecterOuvrier.jsp, le programme affecte alors l'ouvrier au devis
                int idDevis = Integer.parseInt(request.getParameter("idDevis")); // Récupère idDevis
                int idOuvrier = Integer.parseInt(request.getParameter("idOuvrier")); // Récupère idOuvrier

                utx.begin(); // Début de la transaction
                em = emf.createEntityManager();
                
                Devis devis = em.find(Devis.class, idDevis); // Recupère le devis "devis" à partir de idDevis
                Ouvrier o = em.find(Ouvrier.class, idOuvrier); // Recupère l'ouvrier "o" à partir de idOuvrier
                
                devis.setOuvrieridOuvrier(o); // Set l'ouvrier "o" au devis "devis"
                devis.setEtat("Ouvrier affecté"); // Set l'état "Ouvrier affecté" au devis "devis"
                devis.setPrix(o.getTarif()*devis.getNbheures()); // Set le prix calculé au devis "devis"
                Collection<Devis> c = o.getDevisCollection(); // Récupère la collection de devis "c" de l'ouvrier "o"
                c.add(devis); // Ajoute à la collection de devis "c" le devis "devis"
                o.setDevisCollection(c); // Set la collection de devis "c" à l'ouvrier "o"
                
                utx.commit(); // Fin de la transaction
                
                request.getRequestDispatcher("ListeDevis").forward(request, response); // Retourne sur /ListeDevis qui enverra 
                // à son tour l'utilisateur vers ListeDevis.jsp où le changement sera affiché
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
        try {
            processRequest(request, response);
        } catch (NotSupportedException ex) {
            Logger.getLogger(AffecterOuvrier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(AffecterOuvrier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NotSupportedException ex) {
            Logger.getLogger(AffecterOuvrier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(AffecterOuvrier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "AffecterOuvrier servlet";
    }
    // </editor-fold>
}
