package com.casestudy.event.aggregation.mapper;

import com.casestudy.models.avro.ad.AdEvent;
import com.casestudy.models.entity.ad.AdEventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdEventMapper {

    AdEventMapper INSTANCE = Mappers.getMapper(AdEventMapper.class);

    AdEventEntity adEventAvroModelToDBEntity(AdEvent adEvent);
}
