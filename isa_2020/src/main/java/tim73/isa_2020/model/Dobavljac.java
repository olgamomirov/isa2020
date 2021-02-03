package tim73.isa_2020.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Dobavljac extends Korisnik{

	@OneToMany(mappedBy = "dobavljac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Ponuda> ponude = new HashSet<Ponuda>();

	public Set<Ponuda> getPonude() {
		return ponude;
	}

	public void setPonude(Set<Ponuda> ponude) {
		this.ponude = ponude;
	}
	
}
