/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojekpws.finalprojekpws;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "pwsuas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pwsuas.findAll", query = "SELECT p FROM Pwsuas p"),
    @NamedQuery(name = "Pwsuas.findById", query = "SELECT p FROM Pwsuas p WHERE p.id = :id"),
    @NamedQuery(name = "Pwsuas.findByNama", query = "SELECT p FROM Pwsuas p WHERE p.nama = :nama"),
    @NamedQuery(name = "Pwsuas.findByNik", query = "SELECT p FROM Pwsuas p WHERE p.nik = :nik"),
    @NamedQuery(name = "Pwsuas.findByAddress", query = "SELECT p FROM Pwsuas p WHERE p.address = :address"),
    @NamedQuery(name = "Pwsuas.findByPhoto", query = "SELECT p FROM Pwsuas p WHERE p.photo = :photo")})
public class Pwsuas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nama")
    private String nama;
    @Column(name = "nik")
    private String nik;
    @Column(name = "address")
    private String address;
    @Column(name = "photo")
    private Boolean photo;

    public Pwsuas() {
    }

    public Pwsuas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getPhoto() {
        return photo;
    }

    public void setPhoto(Boolean photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pwsuas)) {
            return false;
        }
        Pwsuas other = (Pwsuas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finalprojekpws.finalprojekpws.Pwsuas[ id=" + id + " ]";
    }
    
}
