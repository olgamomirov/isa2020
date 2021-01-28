package tim73.isa_2020.service;

import org.springframework.beans.factory.annotation.Autowired;

import tim73.isa_2020.model.AkcijaPromocija;
import tim73.isa_2020.repository.AkcijaPromocijaRepository;

public interface AkcijaPromocijaService {

	void save(AkcijaPromocija akcijaPromocija);
}
