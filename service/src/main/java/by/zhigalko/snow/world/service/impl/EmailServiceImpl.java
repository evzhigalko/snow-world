package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.dto.OrderDetailsDto;
import by.zhigalko.snow.world.dto.response.OrderResponse;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.service.EmailService;
import by.zhigalko.snow.world.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;
    public final SimpleMailMessage template;
    private final OrderService orderService;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, SimpleMailMessage template, OrderService orderService) {
        this.emailSender = emailSender;
        this.template = template;
        this.orderService = orderService;
    }

    @Override
    public void sendMessage(OrderDetailsDto orderDetailsDto) {
        SimpleMailMessage message = createEmail(orderDetailsDto);
        OrderResponse order = orderService.findById(UUID.fromString(orderDetailsDto.getOrderId()));
        StringBuilder contacts = new StringBuilder();
                contacts
                        .append("Контактная информация заказчика: \n")
                        .append("Фамилия: ").append(orderDetailsDto.getLastname()).append(".\n")
                        .append("Имя: ").append(orderDetailsDto.getFirstname()).append(".\n")
                        .append("Номер телефона: ").append(orderDetailsDto.getPhoneNumber()).append(".\n")
                        .append( "Электронный адрес: ").append(orderDetailsDto.getEmail()).append(".\n");
        String format = String.format("Поступил новый заказ № %s.\nДата начала бронирования: %s.\n" +
                        "Дней бронирования: %s.\nОбщая сумма заказа: %s\n",
                order.getOrderId(), order.getStartReservationDate(), order.getReservationDayNumber(), order.getTotalSum());
        String itemDetailsHeader = "\nЗаказанная экипировка: \n";
        StringBuilder itemDetails = new StringBuilder();
        for (Item item : order.getItems()) {
            itemDetails
                    .append("\n")
                    .append("Товар: ")
                    .append(item.getProductName().getName()).append(".\n")
                    .append("Идентификатор: ").append(item.getId()).append(".\n")
                    .append("Производитель: ").append(item.getMaker()).append(".\n")
                    .append("Размер: ").append(item.getEquipmentSize().getEquipmentSizeId()).append(".\n");
        }
        message.setText(contacts + format + itemDetailsHeader + itemDetails);
        emailSender.send(message);
    }

    private SimpleMailMessage createEmail(OrderDetailsDto orderDetailsDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("НОВЫЙ ЗАКАЗ: №" + orderDetailsDto.getOrderId() + ", дата: " + LocalDateTime.now());
        message.setTo(Objects.requireNonNull(template.getTo())[0]);
        return message;
    }
}
