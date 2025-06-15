package com.casestudy.event.aggregation.mapper;

import com.casestudy.models.avro.cart.CartEvent;
import com.casestudy.models.entity.cart.CartEventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartEventMapper {

    CartEventMapper INSTANCE = Mappers.getMapper(CartEventMapper.class);

    CartEventEntity cartEventAvroModelToDBEntity(CartEvent cartEvent);
}
