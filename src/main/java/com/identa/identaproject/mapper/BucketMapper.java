package com.identa.identaproject.mapper;

import com.identa.identaproject.dto.BucketDTO;
import com.identa.identaproject.entities.Bucket;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BucketMapper {
    BucketMapper MAPPER = Mappers.getMapper(BucketMapper.class);

    Bucket toBucket(BucketDTO bucketDTO);

    @InheritInverseConfiguration
    BucketDTO fromBucket(Bucket bucket);

    List<Bucket> toBucketList(List<BucketDTO> bucketDTOS);

    List<BucketDTO> fromBucketList(List<Bucket> buckets);
}
