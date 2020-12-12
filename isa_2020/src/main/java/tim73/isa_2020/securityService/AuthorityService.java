package tim73.isa_2020.securityService;

import java.util.List;

import tim73.isa_2020.model.Authority;


public interface AuthorityService {
	List<Authority> findById(Long id);
	List<Authority> findByname(String name);
}
