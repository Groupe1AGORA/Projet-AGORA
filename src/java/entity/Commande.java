package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")
    , @NamedQuery(name = "Commande.findByIdCommande", query = "SELECT c FROM Commande c WHERE c.idCommande = :idCommande")
    , @NamedQuery(name = "Commande.findByEtat", query = "SELECT c FROM Commande c WHERE c.etat = :etat")})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCommande")
    private Integer idCommande;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Etat")
    private String etat;   
    @Column(name = "cout")
    private int cout;
    @Column(name = "notation")
    private boolean notation;
    @OneToOne
    private Note noteidNote;
    @OneToOne
    private Devis devisidDevis;
    
    public Commande() {
    }

    public Commande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public Commande(Integer idCommande, String etat) {
        this.idCommande = idCommande;
        this.etat = etat;
    }

    public Commande(String etat, int cout, Devis devisidDevis) {
        this.etat = etat;
        this.cout = cout;
        this.devisidDevis = devisidDevis;
        this.notation = false;
        
    }
    
    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }
    
    public void setNotation(boolean notation) {
        this.notation = notation;
    }
    
     public boolean getNotation() {
        return notation;
    }
    
     public Note getNoteidNote() {
        return noteidNote;
    }
     
    public void setNoteidNote(Note noteidNote) {
        this.noteidNote = noteidNote;
    }
    
    public Devis getDevisidDevis() {
        return devisidDevis;
    }

    public void setDevisidDevis(Devis devisidDevis) {
        this.devisidDevis = devisidDevis;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCommande != null ? idCommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.idCommande == null && other.idCommande != null) || (this.idCommande != null && !this.idCommande.equals(other.idCommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Commande[ idCommande=" + idCommande + " ]";
    }
    
}
