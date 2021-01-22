package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.OcenaSifrarnikLekova;

public interface OcenaSifrarnikLekovaService {

	OcenaSifrarnikLekova findBySifrarnikLekovaIdAndPacijentId(Long sifrarnikLekovaId, Long pacijentId);
	
	List<OcenaSifrarnikLekova> findBySifrarnikLekovaId(Long sifrarnikLekovaId);
	
	void save(OcenaSifrarnikLekova ocenaSifrarnikLekova);
}
