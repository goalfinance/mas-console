/**
 * 
 */
package pan.mas.console.core.outpost.web.security.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;

import pan.mas.console.core.outpost.web.security.domain.SecurityResource;
import pan.mas.console.core.outpost.web.security.domain.SecurityRole;
import pan.mas.console.core.outpost.web.security.domain.SecurityRolePermission;
import pan.mas.console.core.outpost.web.security.domain.SecurityUser;
import pan.mas.console.core.outpost.web.security.domain.SecurityUserPermission;
import pan.mas.console.core.outpost.web.security.repository.SecurityResourceRepository;
import pan.mas.console.core.outpost.web.security.repository.SecurityRolePermissionRepository;
import pan.mas.console.core.outpost.web.security.repository.SecurityRoleRepository;
import pan.mas.console.core.outpost.web.security.repository.SecurityUserPermissionRepository;
import pan.mas.console.core.outpost.web.security.repository.SecurityUserRepository;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.security.shiro.CredentialsService;

/**
 * @author panqingrong
 *
 */
@Component
@Service(version = "1.0.0")
public class OutpostWebSecurityServiceImpl implements OutpostWebSecurityService {

	@Autowired
	private SecurityUserRepository securityUserRepository;

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private SecurityRoleRepository securityRoleRepository;

	@Autowired
	private SecurityUserPermissionRepository securityUserPermissionRepository;

	@Autowired
	private SecurityRolePermissionRepository securityRolePermissionRepository;

	@Autowired
	private SecurityResourceRepository securityResourceReponsitory;

	private void encryptPassword(SecurityUser securityUser) throws AppBizException {
		securityUser.setSalt(credentialsService.generateSalt());
		securityUser
				.setPasswd(credentialsService.encryptPassword(securityUser.getPlainPasswd(), securityUser.getSalt()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pan.mas.console.core.outpost.web.security.service.
	 * OutpostWebSecurityService#findSecurityUserInfoByUserId(java.lang.String)
	 */
	public SecurityUser findSecurityUserInfoByUserId(String userId) throws AppBizException {
		SecurityUser su = securityUserRepository.findByUserId(userId);
		if (su == null) {
			Object[] args = new Object[1];
			args[0] = userId;
			throw new AppBizException(AppExceptionCodes.SEC_USER_DOES_NOT_EXIST[0],
					AppExceptionCodes.SEC_USER_DOES_NOT_EXIST[1], args);
		} else {
			return su;
		}
	}

	public List<SecurityUserPermission> findPermisionByUserSid(Long userSid) throws AppBizException {
		return securityUserPermissionRepository.findByUserSid(userSid);
	}

	public List<SecurityRolePermission> findPermissionByRoleSid(Long roleSid) throws AppBizException {
		return securityRolePermissionRepository.findByRoleSid(roleSid);
	}

	public Page<SecurityUser> findSecurityUserByUserId(String userId, int pageNumber, int pageSize) {
		if (userId == null) {
			userId = "";
		}
		return securityUserRepository.findByUserIdLike(userId, new PageRequest(pageNumber, pageSize));
	}

	public void registerSecurityUser(SecurityUser securityUser) throws AppBizException {
		encryptPassword(securityUser);
		securityUserRepository.save(securityUser);
	}

	public SecurityUser findSecurityUserBySid(Long sid) throws AppBizException {
		SecurityUser securityUser = securityUserRepository.findOne(sid);
		if (securityUser == null) {
			Object[] args = new Object[1];
			args[0] = sid;
			throw new AppBizException(AppExceptionCodes.SEC_USER_DOES_NOT_EXIST[0],
					AppExceptionCodes.SEC_USER_DOES_NOT_EXIST[1], args);
		} else {
			return securityUser;
		}
	}

	public List<SecurityRole> findAllSecurityRoles() {
		Iterator<SecurityRole> securityRoleIterator = securityRoleRepository.findAll().iterator();
		List<SecurityRole> securityRoles = new ArrayList<SecurityRole>();
		while (securityRoleIterator.hasNext()) {
			SecurityRole securityRole = securityRoleIterator.next();
			securityRoles.add(securityRole);
		}
		return securityRoles;
	}

	public List<SecurityRole> findAllActiveSecurityRoles() {
		Iterator<SecurityRole> securityRoleIterator = securityRoleRepository.findAll().iterator();
		List<SecurityRole> securityRoles = new ArrayList<SecurityRole>();
		while (securityRoleIterator.hasNext()) {
			SecurityRole securityRole = securityRoleIterator.next();
			if (securityRole.getStatus().equals(SecurityRole.STATUS_ACTIVE)) {
				securityRoles.add(securityRole);
			}
		}
		return securityRoles;
	}

	public void deleteSecurityUser(SecurityUser su) {
		securityUserRepository.delete(su);
	}

	public void saveSecurityUser(SecurityUser su) throws AppBizException {
		securityUserRepository.save(su);
	}

	public void changePassword(Long sid, String oldOne, String newOne) throws AppBizException {
		// First of all, check if the old password can be matched.
		SecurityUser su = this.findSecurityUserBySid(sid);
		String oldPwdHashed = credentialsService.encryptPassword(oldOne, su.getSalt());
		if (!oldPwdHashed.equalsIgnoreCase(su.getPasswd())) {
			throw new AppBizException(AppExceptionCodes.SEC_NOT_MATCH_OLDPASSWORD[0],
					AppExceptionCodes.SEC_NOT_MATCH_OLDPASSWORD[1]);
		}

		// And second, update the new one's info.
		su.setSalt(credentialsService.generateSalt());
		su.setPasswd(credentialsService.encryptPassword(newOne, su.getSalt()));
		su.setPwdChangeTime(new Date());

		securityUserRepository.save(su);
	}

	public void saveSecurityRole(SecurityRole securityRole) throws AppBizException {
		securityRoleRepository.save(securityRole);
	}

	public void deleteSecurityRole(SecurityRole securityRole) {
		securityRoleRepository.delete(securityRole);
	}

	public SecurityRole findSecurityRoleBySid(Long sid) throws AppBizException {
		return securityRoleRepository.findOne(sid);
	}

	@Transactional(rollbackFor = AppBizException.class)
	public void saveSecurityRolePermissions(Long roleSid, Set<Long> resourcesPermitted, String permittedAction)
			throws AppBizException {
		securityRolePermissionRepository.deleteRolesPermission(roleSid);
		for (Long resourcePermittedId : resourcesPermitted) {
			SecurityRolePermission srp = new SecurityRolePermission();
			SecurityResource securityResource = securityResourceReponsitory.findOne(resourcePermittedId);
			srp.setRoleSid(roleSid);
			srp.setResourceGroupSid(securityResource.getGroupSid());
			srp.setPermission(resourcePermittedId + ":" + permittedAction);
			srp.setResourceSid(resourcePermittedId);
			srp.setStatus(SecurityRolePermission.STATUS_ACTIVE);
			srp.setCreatetime(new Date());
			securityRolePermissionRepository.save(srp);
		}
	}

	public void deleteSecurityRolePermission(SecurityRolePermission securityRolePermission) {
		securityRolePermissionRepository.delete(securityRolePermission);
	}

	public SecurityRolePermission findSecurityRolePermissionBySid(Long sid) throws AppBizException {
		return securityRolePermissionRepository.findOne(sid);
	}

}
