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
@RequestMapping("/packingObjects")
public class PackingObjectController {
	private PackingObjectRepository repository;

	public PackingObjectController(PackingObjectRepository rep) {
		repository = rep;
	}

	@GetMapping
	public List<PackingObject> findAll() {
		System.out.println(repository.count());
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<?> findById(@PathVariable long id) {
		try {
			return repository.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PackingObject not found", e);
		}
	}
	
	@PostMapping
	public PackingObject createPackingObject(@RequestBody PackingObject packingObject) {
		return repository.save(packingObject);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePackingObject(@PathVariable long id) {

		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			/* ResponseEntity.ok().build(); */
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PackingObject not found", e);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePackingObject(@PathVariable Long id,
			@Valid @RequestBody PackingObject packingObject) {
		if (!repository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PackingObject not found");
		}
		packingObject.setId(id);
		repository.save(packingObject);
		return ResponseEntity.ok().build();

		/*
		 * try { return repository.findById(id) .map(updatedPackingObject -> {
		 * updatedPackingObject.setTitle(packingObjectRequest.getTitle());
		 * updatedPackingObject.setPacked(packingObjectRequest.isPacked());
		 * updatedPackingObject.setAddToShoppingList(packingObjectRequest.
		 * isAddToShoppingList());
		 * updatedPackingObject.setPackingList(packingObjectRequest.getPackingList());
		 * return repository.save(updatedPackingObject);});
		 * 
		 * } catch(Exception e) { throw new ResponseStatusException(
		 * HttpStatus.NOT_FOUND, "PackingObject not found", e); }
		 */
	}

}
