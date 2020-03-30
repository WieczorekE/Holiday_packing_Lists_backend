package holidayPackingListProject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner{
	
	private final PackingObjectRepository repository;
	public Bootstrap (PackingObjectRepository repository, PackingListRepository listrepository) {
		this.repository=repository;
		this.listRepository=listrepository;
	}	
	private final PackingListRepository listRepository;

	@Override
	public void run (String... args) throws Exception{
		PackingObject packingObject= new PackingObject();
		PackingList camping = new PackingList();
		PackingList hotel = new PackingList();
		camping.setName("camping");
		listRepository.save(camping);
		packingObject.setTitle("Bücher");
		packingObject.setPacked(false);
		packingObject.setAddToShoppingList(false);
		packingObject.setPackingList(camping);
		repository.save(packingObject);
		PackingObject packingObject2= new PackingObject();
		packingObject2.setTitle("Zahnpasta");
		packingObject2.setPackingList(camping);
		repository.save(packingObject2);
		
		hotel.setName("hotel");
		listRepository.save(hotel);
		PackingObject packingObject3= new PackingObject();
		packingObject3.setTitle("Kleider");
		packingObject3.setPacked(false);
		packingObject3.setAddToShoppingList(false);
		packingObject3.setPackingList(hotel);
		repository.save(packingObject3);
		PackingObject packingObject4= new PackingObject();
		packingObject4.setTitle("Tablet");
		packingObject4.setPackingList(hotel);
		repository.save(packingObject4);
		
		/*
		PackingList packingList = new PackingList();
		packingList.add(packingObject);
		*/
		System.out.println(repository.count());
	}
}
