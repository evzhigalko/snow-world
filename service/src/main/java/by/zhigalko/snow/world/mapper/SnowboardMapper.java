package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.SnowboardDto;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public abstract class SnowboardMapper extends ItemMapper {

    @Mapping(expression = "java(UUID.fromString(snowboardDto.getId()))", target = "id")
    @Mapping(expression = "java(UUID.randomUUID())", target = "image.id")
    public abstract Snowboard snowboardDtoToSnowboard(SnowboardDto snowboardDto);

    @Mapping(expression = "java(String.valueOf(snowboard.getId()))", target = "id")
    public abstract SnowboardDto snowboardToSnowboardDto(Snowboard snowboard);
}
