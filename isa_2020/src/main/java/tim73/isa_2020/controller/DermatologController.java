package tim73.isa_2020.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.service.DermatologService;

@RestController
@RequestMapping(value = "/")
public class DermatologController {

	@Autowired
	private DermatologService dermatologService;
	
	@GetMapping(value = "{id}")
	ResponseEntity<Dermatolog> findById(@PathVariable long id){
		return new ResponseEntity<>(dermatologService.findById(id), HttpStatus.OK);
	}
}
