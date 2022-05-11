package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.entity.OrderDetails;
import by.zhigalko.snowworld.repository.OrderDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderDetailsServiceImplTest {
    private OrderDetails orderDetails;

    @Mock
    private OrderDetailsRepository orderDetailsRepository;

    @InjectMocks
    private OrderDetailsServiceImpl orderDetailsService;

    @BeforeEach
    void setUp() {
        orderDetails = new OrderDetails();
        orderDetails.setId(UUID.randomUUID());
        orderDetails.setEmail("test@test.com");
        orderDetails.setFirstname("Alex");
        orderDetails.setLastname("Smith");
        orderDetails.setPhoneNumber("+123456789");
    }

    @Test
    void saveIfOrderDetailsIfNotNullTest() {
        doReturn(orderDetails).when(orderDetailsRepository).save(orderDetails);

        OrderDetails actual = orderDetailsService.save(orderDetails);

        assertThat(actual).isEqualTo(orderDetails);

        verify(orderDetailsRepository).save(orderDetails);
    }

    @Test
    void saveIfOrderDetailsIsNullTest() {
        doThrow(NullPointerException.class).when(orderDetailsRepository).save(null);

        assertThatNullPointerException().isThrownBy(() -> orderDetailsService.save(null));

        verify(orderDetailsRepository).save(null);
    }
}
