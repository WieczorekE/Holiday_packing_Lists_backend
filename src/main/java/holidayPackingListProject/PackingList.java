package holidayPackingListProject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import holidayPackingListProject.PackingObject;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class PackingList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	
	@OneToMany(mappedBy = "packingList", cascade = CascadeType.ALL)
	private List<PackingObject> packingList = new ArrayList<>();
	/*private List<PackingObject> packingObjectList;*/
	
	
	
}
