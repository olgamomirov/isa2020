package tim73.isa_2020.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Pacijent;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{
	
	Korisnik findByEmailAndLozinka(String email, String lozinka);
	
	
	Korisnik findByEmail (String email);
	
		

}
