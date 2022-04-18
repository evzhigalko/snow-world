package by.zhigalko.snowworld.repository;

import by.zhigalko.snowworld.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ImageRepository extends CrudRepository<Image, UUID> {}
