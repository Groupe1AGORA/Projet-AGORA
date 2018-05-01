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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "note")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n")
    , @NamedQuery(name = "Note.findByIdNote", query = "SELECT n FROM Note n WHERE n.idNote = :idNote")
    , @NamedQuery(name = "Note.findByNotequalite", query = "SELECT n FROM Note n WHERE n.notequalite = :notequalite")
    , @NamedQuery(name = "Note.findByNoteproprete", query = "SELECT n FROM Note n WHERE n.noteproprete = :noteproprete")
    , @NamedQuery(name = "Note.findByNoterespect", query = "SELECT n FROM Note n WHERE n.noterespect = :noterespect")
    , @NamedQuery(name = "Note.findByNoteglobale", query = "SELECT n FROM Note n WHERE n.noteglobale = :noteglobale")
    , @NamedQuery(name = "Note.findByMoyenne", query = "SELECT n FROM Note n WHERE n.moyenne = :moyenne")})
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNote")
    private Integer idNote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Notequalite")
    private int notequalite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Noteproprete")
    private int noteproprete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "noterespect")
    private int noterespect;
    @Basic(optional = false)
    @NotNull
    @Column(name = "noteglobale")
    private int noteglobale;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Moyenne")
    private int moyenne;

    public Note() {
    }

    public Note(Integer idNote) {
        this.idNote = idNote;
    }

    public Note(Integer idNote, int notequalite, int noteproprete, int noterespect, int noteglobale, int moyenne) {
        this.idNote = idNote;
        this.notequalite = notequalite;
        this.noteproprete = noteproprete;
        this.noterespect = noterespect;
        this.noteglobale = noteglobale;
        this.moyenne = moyenne;
    }
    
    public Note(int notequalite, int noteproprete, int noterespect, int noteglobale, int moyenne) {
        this.notequalite = notequalite;
        this.noteproprete = noteproprete;
        this.noterespect = noterespect;
        this.noteglobale = noteglobale;
        this.moyenne = moyenne;
    }
    
    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer idNote) {
        this.idNote = idNote;
    }

    public int getNotequalite() {
        return notequalite;
    }

    public void setNotequalite(int notequalite) {
        this.notequalite = notequalite;
    }

    public int getNoteproprete() {
        return noteproprete;
    }

    public void setNoteproprete(int noteproprete) {
        this.noteproprete = noteproprete;
    }

    public int getNoterespect() {
        return noterespect;
    }

    public void setNoterespect(int noterespect) {
        this.noterespect = noterespect;
    }

    public int getNoteglobale() {
        return noteglobale;
    }

    public void setNoteglobale(int noteglobale) {
        this.noteglobale = noteglobale;
    }

    public int getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(int moyenne) {
        this.moyenne = moyenne;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNote != null ? idNote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.idNote == null && other.idNote != null) || (this.idNote != null && !this.idNote.equals(other.idNote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Note[ idNote=" + idNote + " ]";
    }
    
}
