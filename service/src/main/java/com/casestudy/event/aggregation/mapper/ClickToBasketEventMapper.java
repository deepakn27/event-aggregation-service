package com.casestudy.event.aggregation.mapper;

import com.casestudy.models.avro.clicktobasket.ClickToBasketEvent;
import com.casestudy.models.entity.clicktobasket.ClickToBasketEventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClickToBasketEventMapper {

    ClickToBasketEventMapper INSTANCE = Mappers.getMapper(ClickToBasketEventMapper.class);

    ClickToBasketEventEntity clickToBasketEventAvroModelToDBEntity(ClickToBasketEvent clickToBasketEvent);
}
