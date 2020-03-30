package holidayPackingListProject;

import javax.persistence.*;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class PackingObject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private boolean packed=false;
	private boolean addToShoppingList=false;

	/*Wie Id hier erstellen?- wird beim Speichern automatisch in der Datenbank generiert s.o.
	public PackingObject(String title) {
		this.title = title;
		this.packed= false;
		this.addToShoppingList=false;
	}
	public PackingObject (){}; oder @NoArgsConstructor
	*/
	
	@ManyToOne
	@JoinColumn(name="packingList_id", nullable=false)
	@JsonBackReference
	public PackingList packingList; 
	
}
