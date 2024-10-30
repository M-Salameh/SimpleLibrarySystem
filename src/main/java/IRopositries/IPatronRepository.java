package IRopositries;

import Entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatronRepository extends JpaRepository<Patron, Long>
{

}
