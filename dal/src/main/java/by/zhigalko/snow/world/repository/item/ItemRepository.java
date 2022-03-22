package by.zhigalko.snow.world.repository.item;

import by.zhigalko.snow.world.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository<T extends Item> extends JpaRepository<T, UUID> {
}
