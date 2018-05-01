package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "devis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Devis.findAll", query = "SELECT d FROM Devis d")
    , @NamedQuery(name = "Devis.findByIdDevis", query = "SELECT d FROM Devis d WHERE d.idDevis = :idDevis")
    , @NamedQuery(name = "Devis.findByPrestation", query = "SELECT d FROM Devis d WHERE d.prestation = :prestation")
    , @NamedQuery(name = "Devis.findByCommentaire", query = "SELECT d FROM Devis d WHERE d.commentaire = :commentaire")
    , @NamedQuery(name = "Devis.findByAvis", query = "SELECT d FROM Devis d WHERE d.avis = :avis")
    , @NamedQuery(name = "Devis.findByEtat", query = "SELECT d FROM Devis d WHERE d.etat = :etat")})
public class Devis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDevis")
    private Integer idDevis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Prestation")
    private String prestation;
    @Size(max = 45)
    @Column(name = "Commentaire")
    private String commentaire;
    @Size(max = 45)
    @Column(name = "Avis")
    private String avis;
    @Column(name = "nbheures")
    private int nbheures;
    @Column(name = "prix")
    private int prix;
    @Size(max = 45)
    @Column(name = "Etat")
    private String etat;
    @JoinColumn(name = "Ouvrier_idOuvrier", referencedColumnName = "idOuvrier")
    @ManyToOne(optional = false)
    private Ouvrier ouvrieridOuvrier;
    
    @OneToOne
    private Commande commandeidCommande;
    
    @ManyToOne
    private Client clientidClient;
    
    public Devis() {
    }

    public Devis(Integer idDevis) {
        this.idDevis = idDevis;
    }

    public Devis(Integer idDevis, String prestation) {
        this.idDevis = idDevis;
        this.prestation = prestation;
    }

    public Devis(String prestation, String commentaire) {
        this.prestation = prestation;
        this.commentaire = commentaire;
    }
    
    public Devis(String prestation, String commentaire, String etat, Client clientidClient) {
        this.prestation = prestation;
        this.commentaire = commentaire;
        this.etat = etat;
        this.clientidClient = clientidClient;
    }
    
    // Constructeurs pour CreationBD
    
    // Saisi
    public Devis(String prestation, String commentaire, String avis, int nbheures, String etat, Client clientidClient) {
        this.prestation = prestation;
        this.commentaire = commentaire;
        this.avis = avis;
        this.nbheures = nbheures;
        this.etat = etat;
        this.clientidClient = clientidClient;
    }
    
    // Ouvrier affecté & Accepté
    public Devis(String prestation, String commentaire, String avis, int nbheures, int prix, String etat, Ouvrier ouvrieridOuvrier, Client clientidClient) {
        this.prestation = prestation;
        this.commentaire = commentaire;
        this.avis = avis;
        this.nbheures = nbheures;
        this.prix = prix;
        this.etat = etat;
        this.ouvrieridOuvrier = ouvrieridOuvrier;
        this.clientidClient = clientidClient;
    }
    
    // FIN

    public Integer getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(Integer idDevis) {
        this.idDevis = idDevis;
    }

    public String getPrestation() {
        return prestation;
    }

    public void setPrestation(String prestation) {
        this.prestation = prestation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }
    
    public int getNbheures() {
        return nbheures;
    }

    public void setNbheures(int nbheures) {
        this.nbheures = nbheures;
    }
    
    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Ouvrier getOuvrieridOuvrier() {
        return ouvrieridOuvrier;
    }

    public void setOuvrieridOuvrier(Ouvrier ouvrieridOuvrier) {
        this.ouvrieridOuvrier = ouvrieridOuvrier;
    }
    
    public Commande getCommandeidCommande() {
        return commandeidCommande;
    }

    public void setCommandeidCommande(Commande commandeidCommande) {
        this.commandeidCommande = commandeidCommande;
    } 
    
    public Client getClientidClient() {
        return clientidClient;
    }

    public void setClientidClient(Client clientidClient) {
        this.clientidClient = clientidClient;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDevis != null ? idDevis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Devis)) {
            return false;
        }
        Devis other = (Devis) object;
        if ((this.idDevis == null && other.idDevis != null) || (this.idDevis != null && !this.idDevis.equals(other.idDevis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Devis[ idDevis=" + idDevis + " ]";
    }
    
}
