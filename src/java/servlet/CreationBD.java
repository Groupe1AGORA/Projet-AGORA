package servlet;

import entity.Client;
import entity.Commande;
import entity.Devis;
import entity.Ouvrier;
import entity.Employe;
import entity.Facture;
import entity.Note;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.transaction.UserTransaction;

@WebServlet(name = "CreationBD", urlPatterns = {"/CreationBD"})
public class CreationBD extends HttpServlet {

    // Ce fichier sert à créer une base de données rapidement.
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
            Client client1 = new Client("TestClient1", "TestC1", "Flanders", "Ned", "06 00 00 00 00", "nflanders@gmail.com", "Rue du test");
            Client client2 = new Client("TestClient2", "TestC2", "Leonard", "Lenny", "06 00 00 00 01", "lleonard@gmail.com", "Avenue du test");
            Client client3 = new Client("TestClient3", "TestC3", "Carlton", "Carlson", "06 00 00 00 02", "ccarlton@gmail.com", "Boulevard du test");
            
            Ouvrier ouvrier1 = new Ouvrier("TestOuvrier1", "TestO1", "Simpson", "Homer", "07 00 00 00 00", "hsimpson@gmail.com", "Allée du test", "Toiture", 25);
            Ouvrier ouvrier2 = new Ouvrier("TestOuvrier2", "TestO2", "Simpson", "Bart", "07 00 00 00 01", "bsimpsons@gmail.com", "Place du test", "Plomberie", 18);
            Ouvrier ouvrier3 = new Ouvrier("TestOuvrier3", "TestO3", "Simpson", "Marge", "07 00 00 00 02", "msimpson@gmail.com", "Chemin du test", "Electricite", 30);
            
            Ouvrier ouvrier4 = new Ouvrier("TestOuvrier4", "TestO4", "Brockman", "Kent", "07 00 00 00 03", "kbrockman@gmail.com", "Voie du test", "Toiture", 17);
            Ouvrier ouvrier5 = new Ouvrier("TestOuvrier5", "TestO5", "Arney", "Pie", "07 00 00 00 04", "parney@gmail.com", "Sentier du test", "Plomberie", 24);
            Ouvrier ouvrier6 = new Ouvrier("TestOuvrier6", "TestO6", "Tiny", "Mr", "07 00 00 00 05", "mtiny@gmail.com", "Carrefour du test", "Electricite", 29);
            
            Employe employe1 = new Employe("TestEmploye1", "TestE1", "Bouvier", "Patty", "08 00 00 00 01", "pbouvier@gmail.com", "Pont du test", "Service Technique");
            Employe employe2 = new Employe("TestEmploye2", "TestE2", "Bouvier", "Selma", "08 00 00 00 02", "sbouvier@gmail.com", "Passage du test", "Agent Administratif");
            Employe employe3 = new Employe("TestEmploye3", "TestE3", "Nahasapeemapetilon", "Apu", "08 00 00 00 03", "anahasa@gmail.com", "Promenade du test", "Service Marketing");
            Employe employe4 = new Employe("TestEmploye4", "TestE4", "Naegle", "Lindsey", "08 00 00 00 04", "lnaegle@gmail.com", "Impasse du test", "Service Comptable");
            
            Devis devis1 = new Devis("Toiture", "Dommages vents violents", "Demande", client2);
            Devis devis2 = new Devis("Toiture", "Dégâts grêle", "gros dégâts", 7, "Saisi", client3);
            Devis devis3 = new Devis("Plomberie", "tuyau explosé", "Demande", client1);
            Devis devis4 = new Devis("Plomberie", "Problème fuite", "Intervention classique", 2, 36, "Ouvrier affecté", ouvrier2, client3);
            Devis devis5 = new Devis("Electricite", "impossible de re-armer disjoncteur", "Demande", client1);
            Devis devis6 = new Devis("Electricite", "problème lumière", "Câbles à changer", 5, 150, "Accepté", ouvrier3, client2);
            
            Devis devis7 = new Devis("Toiture", "problème toit", "charpente non-endommagé", 12, 300, "Accepté", ouvrier1, client2);
            Devis devis8 = new Devis("Plomberie", "problème tuyau", "fosse sceptique", 6, 108, "Accepté", ouvrier2, client2);
            Devis devis9 = new Devis("Electricite", "problème lampe", "opération simple", 4, 120, "Accepté", ouvrier3, client3);
            
            // Pour les commandes soldées
            Devis devis10 = new Devis("Toiture", "dégâts toit", "nombreuses ardoises cassées", 12, 300, "Accepté", ouvrier1, client2);
            Devis devis11 = new Devis("Plomberie", "tuyau explosé", "tuyau à changer", 6, 108, "Accepté", ouvrier2, client2);
            Devis devis12 = new Devis("Electricite", "problème alimentation", "opération complexe", 8, 240, "Accepté", ouvrier3, client3);
            
            // Pour les commandes facturés
            Devis devis13 = new Devis("Toiture", "toiture endommagé", "dégâts importants", 12, 300, "Accepté", ouvrier1, client2);
            Devis devis14 = new Devis("Plomberie", "problème toilettes", "fosse sceptique", 6, 108, "Accepté", ouvrier2, client2);
            Devis devis15 = new Devis("Plomberie", "problème tuyau", "opération simple", 4, 72, "Accepté", ouvrier2, client3);
            
            Commande commande1 = new Commande("Planifiée", 150, devis6);
            
            Commande commande2 = new Commande("Travaux terminés", 300, devis7);
            Commande commande3 = new Commande("Travaux terminés", 108, devis8);
            Commande commande4 = new Commande("Travaux terminés", 120, devis9);
            
            Commande commande5 = new Commande("Commande soldée", 300, devis10);
            Commande commande6 = new Commande("Commande soldée", 108, devis11);
            Commande commande7 = new Commande("Commande soldée", 240, devis12);
            
            Commande commande8 = new Commande("Commande facturée", 300, devis13);
            Commande commande9 = new Commande("Commande facturée", 108, devis14);
            Commande commande10 = new Commande("Commande facturée", 72, devis15);
            
            Facture facture1 = new Facture(162.0, ouvrier2);
            Facture facture2 = new Facture(270.0, ouvrier1);
            
            Note note1 = new Note(5, 6, 6, 7, 6);
            Note note2 = new Note(3, 6, 6, 5, 5);
            Note note3 = new Note(8, 9, 9, 10, 9);
            Note note4 = new Note(2, 2, 2, 2, 2);
            Note note5 = new Note(4, 4, 4, 4, 4);
            Note note6 = new Note(4, 5, 5, 6, 5);
            
            utx.begin();
            em = emf.createEntityManager();
            
            em.persist(client1);
            em.persist(client2);
            em.persist(client3);
            
            em.persist(ouvrier1);
            em.persist(ouvrier2);
            em.persist(ouvrier3);
            em.persist(ouvrier4);
            em.persist(ouvrier5);
            em.persist(ouvrier6);
            
            em.persist(employe1);
            em.persist(employe2);
            em.persist(employe3);
            em.persist(employe4);
            
            em.persist(devis1);
            em.persist(devis2);
            em.persist(devis3);
            em.persist(devis4);
            em.persist(devis5);
            em.persist(devis6);
            
            em.persist(devis7);
            em.persist(devis8);
            em.persist(devis9);
            
            em.persist(devis10);
            em.persist(devis11);
            em.persist(devis12);
            
            em.persist(devis13);
            em.persist(devis14);
            em.persist(devis15);
            
            em.persist(commande1);
            
            em.persist(commande2);
            em.persist(commande3);
            em.persist(commande4);
            
            em.persist(commande5);
            em.persist(commande6);
            em.persist(commande7);
            
            em.persist(commande8);
            em.persist(commande9);
            em.persist(commande10);
            
            devis6.setCommandeidCommande(commande1);
            
            devis7.setCommandeidCommande(commande2);
            devis8.setCommandeidCommande(commande3);
            devis9.setCommandeidCommande(commande4);
            
            devis10.setCommandeidCommande(commande5);
            devis11.setCommandeidCommande(commande6);
            devis12.setCommandeidCommande(commande7);
            
            devis13.setCommandeidCommande(commande8);
            devis14.setCommandeidCommande(commande9);
            devis15.setCommandeidCommande(commande10);
            
            em.persist(facture1);
            em.persist(facture2);
            
            em.persist(note1);
            em.persist(note2);
            em.persist(note3);
            em.persist(note4);
            em.persist(note5);
            em.persist(note6);
            
            commande5.setNoteidNote(note1);
            commande6.setNoteidNote(note2);
            commande7.setNoteidNote(note3);
            commande8.setNoteidNote(note4);
            commande9.setNoteidNote(note5);
            commande10.setNoteidNote(note6);
            commande5.setNotation(true);
            commande6.setNotation(true);
            commande7.setNotation(true);
            commande8.setNotation(true);
            commande9.setNotation(true);
            commande10.setNotation(true);
            
            ouvrier1.setMoyennefinale(4);
            ouvrier2.setMoyennefinale(5);
            ouvrier3.setMoyennefinale(9);
            
            utx.commit();
            
            request.getRequestDispatcher("Authentification.jsp").forward(request, response);
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
        return "CreationBD servlet";
    }
    // </editor-fold>
}
