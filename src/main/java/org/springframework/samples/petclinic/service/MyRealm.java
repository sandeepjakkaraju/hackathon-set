package org.springframework.samples.petclinic.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Repository;
import org.springframework.samples.petclinic.model.*;

@Repository("myRealm")
public class MyRealm extends AuthorizingRealm {

	@PersistenceContext
	private EntityManager em;

	public MyRealm() {
		setName("myrealm");
	}

	@Override
	@Transactional
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		String userName = pc.getPrimaryPrincipal() + "";

		List<Users> l = em.createQuery("SELECT n FROM Users n WHERE n.username = :name").setParameter("name", userName)
				.getResultList();
		if (!l.isEmpty()) {
			Users user = l.get(0);
			System.out.println(user.getUsername());
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			System.out.println(user.getUserRoles().size());
			
			for (UserRole role : user.getUserRoles()) {
				System.out.println(role.getRoleName());

				info.addRole(role.getRoleName());
				for (UserRolePerms perm: role.getPermissions()) {
					info.addStringPermission(perm.getPermission());
				}
			}
			return info;
		}
		return null;
	}

	@Override
	@Transactional
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
		at = (UsernamePasswordToken) at;
		
		List<Users> l = em.createQuery("SELECT n FROM Users n where username='"+at.getPrincipal().toString()+"'").getResultList();
		if (!l.isEmpty()) {
			Users user = l.get(0);
			return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
		}
		return null;
	}
}