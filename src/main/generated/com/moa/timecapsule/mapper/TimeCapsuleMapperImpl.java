package com.moa.timecapsule.mapper;

import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleDto.TimeCapsuleDtoBuilder;
import com.moa.timecapsule.entity.Timecapsule;
import com.moa.timecapsule.entity.Timecapsule.TimecapsuleBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-13T22:55:54+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class TimeCapsuleMapperImpl implements TimeCapsuleMapper {

    @Override
    public TimeCapsuleDto toDto(Timecapsule timecapsule) {
        if ( timecapsule == null ) {
            return null;
        }

        TimeCapsuleDtoBuilder<?, ?> timeCapsuleDto = TimeCapsuleDto.builder();

        timeCapsuleDto.createdAt( timecapsule.getCreatedAt() );
        timeCapsuleDto.modifiedAt( timecapsule.getModifiedAt() );
        timeCapsuleDto.timeCapsuleId( timecapsule.getTimeCapsuleId() );
        timeCapsuleDto.title( timecapsule.getTitle() );
        timeCapsuleDto.closedAt( timecapsule.getClosedAt() );
        timeCapsuleDto.openedAt( timecapsule.getOpenedAt() );
        timeCapsuleDto.creator( timecapsule.getCreator() );

        return timeCapsuleDto.build();
    }

    @Override
    public Timecapsule toEntity(TimeCapsuleDto timeCapsuleDto) {
        if ( timeCapsuleDto == null ) {
            return null;
        }

        TimecapsuleBuilder<?, ?> timecapsule = Timecapsule.builder();

        timecapsule.createdAt( timeCapsuleDto.getCreatedAt() );
        timecapsule.modifiedAt( timeCapsuleDto.getModifiedAt() );
        timecapsule.timeCapsuleId( timeCapsuleDto.getTimeCapsuleId() );
        timecapsule.title( timeCapsuleDto.getTitle() );
        timecapsule.closedAt( timeCapsuleDto.getClosedAt() );
        timecapsule.openedAt( timeCapsuleDto.getOpenedAt() );
        timecapsule.creator( timeCapsuleDto.getCreator() );

        return timecapsule.build();
    }
}
