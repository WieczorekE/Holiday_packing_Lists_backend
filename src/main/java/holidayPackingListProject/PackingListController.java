package holidayPackingListProject;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/packingList")
public class PackingListController {
	private PackingListRepository repository;

	public PackingListController(PackingListRepository rep) {
		repository = rep;
	}

	@GetMapping
	public List<PackingList> findAll() {
		System.out.println(repository.count());
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<?> findListById(@PathVariable long id) {
		try {
			return repository.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PackingList not found", e);
		}
	}
	
	@PostMapping
	public PackingList createPackingList(@RequestBody PackingList packingList) {
		return repository.save(packingList);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePackingList(@PathVariable long id) {

		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PackingList not found", e);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePackingList(@PathVariable Long id,
			@Valid @RequestBody PackingList packingList) {
		if (!repository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PackingList not found");
		}
		packingList.setId(id);
		repository.save(packingList);
		return ResponseEntity.ok().build();
	}

}
