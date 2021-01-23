package tim73.isa_2020.service;

import tim73.isa_2020.model.OcenaDermatolog;

public interface OcenaDermatologService {

	OcenaDermatolog findByDermatologIdAndPacijentId(Long dermatologId, Long pacijentId);
	
	void save(OcenaDermatolog ocenaDermatolog);
}
