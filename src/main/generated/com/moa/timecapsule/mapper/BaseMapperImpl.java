package com.moa.timecapsule.mapper;

import com.moa.timecapsule.dto.BaseDto;
import com.moa.timecapsule.dto.BaseDto.BaseDtoBuilder;
import com.moa.timecapsule.entity.BaseEntity;
import com.moa.timecapsule.entity.BaseEntity.BaseEntityBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-13T22:55:54+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class BaseMapperImpl implements BaseMapper {

    @Override
    public BaseEntity dtoToEntity(BaseDto baseDto) {
        if ( baseDto == null ) {
            return null;
        }

        BaseEntityBuilder<?, ?> baseEntity = BaseEntity.builder();

        baseEntity.createdAt( baseDto.getCreatedAt() );
        baseEntity.modifiedAt( baseDto.getModifiedAt() );

        return baseEntity.build();
    }

    @Override
    public BaseDto entityToDto(BaseEntity baseEntity) {
        if ( baseEntity == null ) {
            return null;
        }

        BaseDtoBuilder<?, ?> baseDto = BaseDto.builder();

        baseDto.createdAt( baseEntity.getCreatedAt() );
        baseDto.modifiedAt( baseEntity.getModifiedAt() );

        return baseDto.build();
    }
}
