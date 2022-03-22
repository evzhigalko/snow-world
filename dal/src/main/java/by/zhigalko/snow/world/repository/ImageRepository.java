package by.zhigalko.snow.world.repository;

import by.zhigalko.snow.world.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ImageRepository extends CrudRepository<Image, UUID> {}
