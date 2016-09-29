package org.springframework.samples.petclinic.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="userrole")
public class UserRole implements Serializable {

    @Id
    private Integer id;
    @ManyToOne
    private Users users;
    private String roleName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<UserRolePerms> getUserRolePerms() {
		return userRolePerms;
	}

	public void setUserRolePerms(Set<UserRolePerms> userRolePerms) {
		this.userRolePerms = userRolePerms;
	}

	@OneToMany(mappedBy = "userrole")
	@Fetch(FetchMode.JOIN)
	private Set<UserRolePerms> userRolePerms;

	public Set<UserRolePerms>  getPermissions()
	{
		return userRolePerms;
	}

}