package org.springframework.samples.petclinic.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by techmojo on 29/9/16.
 */
@Entity
@Table(name="userroleperms")
public class UserRolePerms implements Serializable {

    @Id
    private Integer id;
    @ManyToOne
    private UserRole userrole;
    private String permission;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPermission() {
        return this.permission;
    }

}