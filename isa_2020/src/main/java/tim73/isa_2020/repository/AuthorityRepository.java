package tim73.isa_2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim73.isa_2020.model.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(String name);
}
