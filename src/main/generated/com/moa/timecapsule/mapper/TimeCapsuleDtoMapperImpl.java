package com.moa.timecapsule.mapper;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleRequest;
import com.moa.timecapsule.controller.response.TimeCapsuleResponse;
import com.moa.timecapsule.controller.response.TimeCapsuleResponse.TimeCapsuleResponseBuilder;
import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleDto.TimeCapsuleDtoBuilder;
import com.moa.timecapsule.dto.TimeCapsuleIdsDto;
import com.moa.timecapsule.dto.TimeCapsuleIdsDto.TimeCapsuleIdsDtoBuilder;
import com.moa.timecapsule.dto.TimeCapsuleMemberDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-13T22:55:54+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class TimeCapsuleDtoMapperImpl implements TimeCapsuleDtoMapper {

    @Override
    public TimeCapsuleDto fromGenerateTimeCapsuleRequest(UUID memberId, GenerateTimeCapsuleRequest generateTimeCapsuleRequest) {
        if ( memberId == null && generateTimeCapsuleRequest == null ) {
            return null;
        }

        TimeCapsuleDtoBuilder<?, ?> timeCapsuleDto = TimeCapsuleDto.builder();

        if ( memberId != null ) {
            timeCapsuleDto.creator( memberId );
        }
        if ( generateTimeCapsuleRequest != null ) {
            timeCapsuleDto.title( generateTimeCapsuleRequest.getTitle() );
            timeCapsuleDto.closedAt( generateTimeCapsuleRequest.getClosedAt() );
            timeCapsuleDto.openedAt( generateTimeCapsuleRequest.getOpenedAt() );
        }
        timeCapsuleDto.friends( uuidToTimeCapsuleDto(generateTimeCapsuleRequest.getFriends()) );

        return timeCapsuleDto.build();
    }

    @Override
    public TimeCapsuleIdsDto toTimeCapsuleIdsDto(UUID memberId, UUID timeCapsuleId) {
        if ( memberId == null && timeCapsuleId == null ) {
            return null;
        }

        TimeCapsuleIdsDtoBuilder<?, ?> timeCapsuleIdsDto = TimeCapsuleIdsDto.builder();

        if ( memberId != null ) {
            timeCapsuleIdsDto.memberId( memberId );
        }
        if ( timeCapsuleId != null ) {
            timeCapsuleIdsDto.timeCapsuleId( timeCapsuleId );
        }

        return timeCapsuleIdsDto.build();
    }

    @Override
    public TimeCapsuleResponse toTimeCapsuleResponse(TimeCapsuleDto timeCapsuleDto) {
        if ( timeCapsuleDto == null ) {
            return null;
        }

        TimeCapsuleResponseBuilder timeCapsuleResponse = TimeCapsuleResponse.builder();

        timeCapsuleResponse.timeCapsuleId( timeCapsuleDto.getTimeCapsuleId() );
        timeCapsuleResponse.title( timeCapsuleDto.getTitle() );
        timeCapsuleResponse.closedAt( timeCapsuleDto.getClosedAt() );
        timeCapsuleResponse.openedAt( timeCapsuleDto.getOpenedAt() );
        timeCapsuleResponse.creator( timeCapsuleDto.getCreator() );
        List<TimeCapsuleMemberDto> list = timeCapsuleDto.getFriends();
        if ( list != null ) {
            timeCapsuleResponse.friends( new ArrayList<TimeCapsuleMemberDto>( list ) );
        }

        return timeCapsuleResponse.build();
    }
}
