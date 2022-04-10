package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.config.ApplicationConfig;
import by.zhigalko.snow.world.dto.OrderDetailsDto;
import by.zhigalko.snow.world.entity.Order;
import by.zhigalko.snow.world.entity.OrderDetails;
import by.zhigalko.snow.world.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {ApplicationConfig.class, BCryptPasswordEncoder.class})
@Transactional
class OrderDetailsMapperTest {
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Test
    void orderDetailsDtoToOrderDetailsTest() {
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
        orderDetailsDto.setOrderId(String.valueOf((UUID.randomUUID())));
        orderDetailsDto.setEmail("test@mail.ru");
        orderDetailsDto.setFirstname("Alex");
        orderDetailsDto.setLastname("Smith");
        orderDetailsDto.setPhoneNumber("+3751234567");

        OrderDetails orderDetails = orderDetailsMapper.orderDetailsDtoToOrderDetails(orderDetailsDto);
        Order order = new Order();
        UUID id = UUID.fromString("1221f2f3-f37e-445e-ab76-30adef9be35e");
        User user = new User();
        user.setId(id);
        order.setUser(user);
        order.setId(UUID.randomUUID());
        order.setOrderDetails(orderDetails);
        orderDetails.addOrder(order);

        assertNotNull(orderDetails);
        assertEquals(orderDetailsDto.getEmail(), orderDetails.getEmail());
        assertEquals(orderDetailsDto.getLastname(), orderDetails.getLastname());
        assertEquals(1, orderDetails.getOrders().size());
        System.out.println(orderDetailsDto);
        System.out.println(orderDetails);
    }
}
