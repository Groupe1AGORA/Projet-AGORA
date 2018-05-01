package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ouvrier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ouvrier.findAll", query = "SELECT o FROM Ouvrier o")
    , @NamedQuery(name = "Ouvrier.findByIdOuvrier", query = "SELECT o FROM Ouvrier o WHERE o.idOuvrier = :idOuvrier")
    , @NamedQuery(name = "Ouvrier.findByPseudo", query = "SELECT o FROM Ouvrier o WHERE o.pseudo = :pseudo")
    , @NamedQuery(name = "Ouvrier.findByMotdepasse", query = "SELECT o FROM Ouvrier o WHERE o.motdepasse = :motdepasse")
    , @NamedQuery(name = "Ouvrier.findByNom", query = "SELECT o FROM Ouvrier o WHERE o.nom = :nom")
    , @NamedQuery(name = "Ouvrier.findByPrenom", query = "SELECT o FROM Ouvrier o WHERE o.prenom = :prenom")
    , @NamedQuery(name = "Ouvrier.findByTelephone", query = "SELECT o FROM Ouvrier o WHERE o.telephone = :telephone")
    , @NamedQuery(name = "Ouvrier.findByMail", query = "SELECT o FROM Ouvrier o WHERE o.mail = :mail")
    , @NamedQuery(name = "Ouvrier.findByAdresse", query = "SELECT o FROM Ouvrier o WHERE o.adresse = :adresse")
    , @NamedQuery(name = "Ouvrier.findByPrestation", query = "SELECT o FROM Ouvrier o WHERE o.prestation = :prestation")})
public class Ouvrier implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ouvrieridOuvrier")
    private Collection<Facture> factureCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOuvrier")
    private Integer idOuvrier;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Pseudo")
    private String pseudo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Motdepasse")
    private String motdepasse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Telephone")
    private String telephone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Mail")
    @Pattern(regexp="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message="Format de mail invalide")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Adresse")
    private String adresse;
    @Size(max = 45)
    @Column(name = "Prestation")
    private String prestation;
    @Column(name = "Tarif")
    private int tarif;
    @Column(name = "Moyennefinale")
    private int moyennefinale;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ouvrieridOuvrier")
    private Collection<Devis> devisCollection;

    public Ouvrier() {
    }

    public Ouvrier(Integer idOuvrier) {
        this.idOuvrier = idOuvrier;
    }

    public Ouvrier(Integer idOuvrier, String pseudo, String motdepasse, String nom, String prenom, String telephone, String mail, String adresse) {
        this.idOuvrier = idOuvrier;
        this.pseudo = pseudo;
        this.motdepasse = motdepasse;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail = mail;
        this.adresse = adresse;
    }

    public Ouvrier(String pseudo, String motdepasse, String nom, String prenom, String telephone, String mail, String adresse, String prestation) {
        this.pseudo = pseudo;
        this.motdepasse = motdepasse;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail = mail;
        this.adresse = adresse;
        this.prestation = prestation;
    }
    
    // Creation BD
    public Ouvrier(String pseudo, String motdepasse, String nom, String prenom, String telephone, String mail, String adresse, String prestation, int tarif) {
        this.pseudo = pseudo;
        this.motdepasse = motdepasse;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail = mail;
        this.adresse = adresse;
        this.prestation = prestation;
        this.tarif = tarif;
    }
    // FIN

    public Integer getIdOuvrier() {
        return idOuvrier;
    }

    public void setIdOuvrier(Integer idOuvrier) {
        this.idOuvrier = idOuvrier;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPrestation() {
        return prestation;
    }

    public void setPrestation(String prestation) {
        this.prestation = prestation;
    }
    
    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }
    
    public int getMoyennefinale() {
        return moyennefinale;
    }

    public void setMoyennefinale(int moyennefinale) {
        this.moyennefinale = moyennefinale;
    }

    @XmlTransient
    public Collection<Devis> getDevisCollection() {
        return devisCollection;
    }

    public void setDevisCollection(Collection<Devis> devisCollection) {
        this.devisCollection = devisCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOuvrier != null ? idOuvrier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ouvrier)) {
            return false;
        }
        Ouvrier other = (Ouvrier) object;
        if ((this.idOuvrier == null && other.idOuvrier != null) || (this.idOuvrier != null && !this.idOuvrier.equals(other.idOuvrier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ouvrier[ idOuvrier=" + idOuvrier + " ]";
    }

    @XmlTransient
    public Collection<Facture> getFactureCollection() {
        return factureCollection;
    }

    public void setFactureCollection(Collection<Facture> factureCollection) {
        this.factureCollection = factureCollection;
    }


}
