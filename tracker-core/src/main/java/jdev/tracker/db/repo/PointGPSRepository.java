package jdev.tracker.db.repo;

import jdev.tracker.db.PointGPS;
import org.springframework.data.repository.CrudRepository;

// Репозиторий для таблицы PointDTO
public interface PointGPSRepository extends CrudRepository<PointGPS, Integer> {
}
