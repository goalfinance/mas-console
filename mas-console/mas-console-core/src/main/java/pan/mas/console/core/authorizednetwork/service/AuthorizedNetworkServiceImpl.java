/**
 * 
 */
package pan.mas.console.core.authorizednetwork.service;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import pan.mas.console.core.authorizednetwork.domain.AuthorizedNetwork;
import pan.mas.console.core.authorizednetwork.repository.AuthorizedNetworkRepository;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;

/**
 * @author panqingrong
 *
 */
@Service(version = "1.0.0")
@Component
public class AuthorizedNetworkServiceImpl implements AuthorizedNetworkService {

	@Autowired
	private AuthorizedNetworkRepository authorizedNetworkRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pan.mas.console.core.authorizednetwork.service.AuthorizedNetworkService#
	 * save(pan.mas.console.core.authorizednetwork.domain.AuthorizedNetwork)
	 */
	public void save(AuthorizedNetwork authorizedNetwork) {
		authorizedNetworkRepository.save(authorizedNetwork);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pan.mas.console.core.authorizednetwork.service.AuthorizedNetworkService#
	 * delete(pan.mas.console.core.authorizednetwork.domain.AuthorizedNetwork)
	 */
	public void delete(AuthorizedNetwork authorizedNetwork) {
		authorizedNetworkRepository.delete(authorizedNetwork);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pan.mas.console.core.authorizednetwork.service.AuthorizedNetworkService#
	 * findOne(java.lang.String)
	 */
	public AuthorizedNetwork findOne(Long sId) throws AppBizException{
		AuthorizedNetwork authorizedNetwork = authorizedNetworkRepository.findOne(sId);
		if (authorizedNetwork == null){
			Object[] args = new Object[1];
			args[0] = sId;
			throw new AppBizException(AppExceptionCodes.AUTHNET_DOES_NOT_EXIST[0],
					AppExceptionCodes.AUTHNET_DOES_NOT_EXIST[1], args);
		}else{
			return authorizedNetwork;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pan.mas.console.core.authorizednetwork.service.AuthorizedNetworkService#
	 * findAll(org.springframework.data.domain.Pageable)
	 */
	public Page<AuthorizedNetwork> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pan.mas.console.core.authorizednetwork.service.AuthorizedNetworkService#
	 * findAll(org.springframework.data.domain.Sort)
	 */
	public Iterable<AuthorizedNetwork> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pan.mas.console.core.authorizednetwork.service.AuthorizedNetworkService#
	 * count()
	 */
	public Long count() {
		return authorizedNetworkRepository.count();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pan.mas.console.core.authorizednetwork.service.AuthorizedNetworkService#
	 * exists(java.lang.String)
	 */
	public boolean exists(Long sId) {
		return authorizedNetworkRepository.exists(sId);
	}

	public AuthorizedNetwork findByAuthNetId(String authNetId) {
		return authorizedNetworkRepository.findByAuthNetId(authNetId);
	}

	public Page<AuthorizedNetwork> findByAuthNetIdAndNameEndsWithAndAuthNetType(String authNetId, String name,
			String authNetType, Pageable pageable) {
		
		Page<AuthorizedNetwork> anPagination = authorizedNetworkRepository.findAll(getWhereClause(authNetId, name, authNetType), pageable);

		return anPagination;
	}
	
	private Specification<AuthorizedNetwork> getWhereClause(final String authNetId, final String name, final String authNetType){
		return new Specification<AuthorizedNetwork>(){

			public Predicate toPredicate(Root<AuthorizedNetwork> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (authNetId != null && !authNetId.equals("")){
					predicate.getExpressions().add(cb.equal(root.<String>get("authNetId"), authNetId));
				}
				
				if (name != null && !name.equals("")){
					predicate.getExpressions().add(cb.like(root.<String>get("name"), "%" + name));
				}
				
				if (authNetType != null && !authNetType.equals("")){
					predicate.getExpressions().add(cb.equal(root.<String>get("authNetType"), authNetType));
				}
				return predicate;
			}
			
		};
	}

}
