package com.tuneup.tuneup.regions;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring" )
public interface RegionMapper {

    @Mapping(target = "parentRegionName", expression = "java(region.getParentRegion() != null ? region.getParentRegion().getName() : null)")
    RegionDto toRegionDto(Region region);

    Region toRegion(RegionDto regionDto);
}