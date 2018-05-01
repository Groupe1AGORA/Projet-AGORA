package servlet;

import entity.Commande;
import entity.Devis;
import entity.Note;
import entity.Ouvrier;
import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;

// The servlet class to insert Note into database
@WebServlet(name = "CreerNote", urlPatterns = {"/CreerNote"})
public class CreerNote extends HttpServlet {

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
            if (request.getParameter("idCommande") == null) {
                em = emf.createEntityManager();

                List devis = em.createQuery("select c from Commande c WHERE (c.notation = 0) AND (c.etat = 'Travaux terminés' OR c.etat = 'Commande soldée' OR c.etat = 'Commande facturée')").getResultList(); // Check of object not exist ?
                request.setAttribute("commandeListe", devis);
                request.getRequestDispatcher("CreerNote.jsp").forward(request, response);
            } else {

                int idCommande = Integer.parseInt(request.getParameter("idCommande"));
                int notequalite = Integer.parseInt(request.getParameter("notequalite"));
                int noteproprete = Integer.parseInt(request.getParameter("noteproprete"));
                int noterespect = Integer.parseInt(request.getParameter("noterespect"));
                int noteglobale = Integer.parseInt(request.getParameter("noteglobale"));
                int moyenne = (notequalite + noteproprete + noterespect + noteglobale) / 4;

                Note note = new Note(notequalite, noteproprete, noterespect, noteglobale, moyenne);

                utx.begin();
                em = emf.createEntityManager();
                
                em.persist(note);
                
                Commande commande = em.find(Commande.class, idCommande);
                commande.setNoteidNote(note);
                commande.setNotation(true);

                Ouvrier o = commande.getDevisidDevis().getOuvrieridOuvrier();
                
                Collection<Devis> c = o.getDevisCollection();
                Iterator<Devis> iterator = c.iterator();
                int divider = 0;
                int moyennetotale = 0;
                while (iterator.hasNext()) {
                    int test = iterator.next().getIdDevis();
                    Devis devis2 = em.find(Devis.class, test);
                    
                    if ("Ouvrier affecté".equals(devis2.getEtat()))
                    {
                        // Afin d'éliminer les devis sans commandes
                    }
                    else if (!devis2.getCommandeidCommande().getNotation()){
                        // Afin d'éliminer les commandes sans notes
                    } else {
                        int notedevis = devis2.getCommandeidCommande().getNoteidNote().getMoyenne();
                        if (notedevis > 0) {
                            moyennetotale = moyennetotale + notedevis;
                            divider++;
                        }
                    }

                }
                if (divider != 0) {
                    int moyennefinale = moyennetotale / divider;
                    o.setMoyennefinale(moyennefinale);
                }
            }
            
            List devis = em.createQuery("select c from Commande c WHERE (c.notation = 0) AND (c.etat = 'Travaux terminés' OR c.etat = 'Commande soldée' OR c.etat = 'Commande facturée')").getResultList(); // Check of object not exist ?
            request.setAttribute("commandeListe", devis);

            //commit transaction which will trigger the em to 
            //commit newly edited entity into database
            utx.commit();
            request.getRequestDispatcher("CreerNote.jsp").forward(request, response); // à changer

        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            //close the em to release any resources held up by the persistebce provider
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
        return "CreerOuvrier servlet";
    }
    // </editor-fold>
}
