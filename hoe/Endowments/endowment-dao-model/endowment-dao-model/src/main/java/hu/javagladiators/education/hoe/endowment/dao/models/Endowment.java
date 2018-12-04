package hu.javagladiators.education.hoe.endowment.dao.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(
    name = "endowment", 
    indexes = {
        @Index(name = "id", columnList = "id", unique = true),
        @Index(name = "endowment_name", columnList = "name", unique = true),
        @Index(name = "endowment_desc", columnList = "desc", unique = true)     
    })
@NamedQueries({
    @NamedQuery(name = "Endowment.all", query = "SELECT u FROM Endowment u "),
    @NamedQuery(name = "Endowment.name", query = "SELECT e FROM Endowment e WHERE e.name=:name")
})
public class Endowment implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private long id;
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    
    @Column(name = "name")
    private String name;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    
    @Column(name = "type")
    private String type;
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
      
    @Column(name = "desc")
    private String desc;
    public String getDesc() {return desc;}
    public void setDesc(String desc) {this.desc = desc;}
     
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endowment)) {
            return false;
        }
        Endowment other = (Endowment) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hu.javagladiators.education.hoe.user.dao.model.User[ id=" + id + " ]";
    }
}
