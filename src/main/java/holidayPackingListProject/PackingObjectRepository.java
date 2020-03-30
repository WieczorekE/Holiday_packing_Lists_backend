package holidayPackingListProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackingObjectRepository extends JpaRepository<PackingObject, Long> {

}
