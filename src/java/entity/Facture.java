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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "facture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facture.findAll", query = "SELECT f FROM Facture f")
    , @NamedQuery(name = "Facture.findByIdFacture", query = "SELECT f FROM Facture f WHERE f.idFacture = :idFacture")})
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFacture")
    private Integer idFacture;
    @Basic(optional = false)
    @Column(name = "montantFacture")
    private Double montantFacture;
    @JoinColumn(name = "Ouvrier_idOuvrier", referencedColumnName = "idOuvrier")
    @ManyToOne(optional = false)
    private Ouvrier ouvrieridOuvrier;

    public Facture() {
    }

    public Facture(Integer idFacture) {
        this.idFacture = idFacture;
    }
    
    public Facture(Double montantFacture, Ouvrier ouvrieridOuvrier) {
        this.montantFacture = montantFacture;
        this.ouvrieridOuvrier = ouvrieridOuvrier;
    }

    public Integer getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Integer idFacture) {
        this.idFacture = idFacture;
    }
    
    public Double getmontantFacture() {
        return montantFacture;
    }

    public void setIdFacture(Double montantFacture) {
        this.montantFacture = montantFacture;
    }

    public Ouvrier getOuvrieridOuvrier() {
        return ouvrieridOuvrier;
    }

    public void setOuvrieridOuvrier(Ouvrier ouvrieridOuvrier) {
        this.ouvrieridOuvrier = ouvrieridOuvrier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacture != null ? idFacture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.idFacture == null && other.idFacture != null) || (this.idFacture != null && !this.idFacture.equals(other.idFacture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Facture[ idFacture=" + idFacture + " ]";
    }
    
}
