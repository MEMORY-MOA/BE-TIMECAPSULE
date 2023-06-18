package com.moa.timecapsule.mapper;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleContentRequest;
import com.moa.timecapsule.controller.response.TimeCapsuleTextResponse;
import com.moa.timecapsule.controller.response.TimeCapsuleTextResponse.TimeCapsuleTextResponseBuilder;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto.TimeCapsuleTextDtoBuilder;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-13T22:55:54+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class TimeCapsuleContentDtoMapperImpl implements TimeCapsuleContentDtoMapper {

    @Override
    public TimeCapsuleTextDto fromGenerateTimeCapsuleTextRequest(UUID memberId, UUID timeCapsuleId, GenerateTimeCapsuleContentRequest generateTimeCapsuleContentRequest) {
        if ( memberId == null && timeCapsuleId == null && generateTimeCapsuleContentRequest == null ) {
            return null;
        }

        TimeCapsuleTextDtoBuilder<?, ?> timeCapsuleTextDto = TimeCapsuleTextDto.builder();

        if ( memberId != null ) {
            timeCapsuleTextDto.memberId( memberId );
        }
        if ( timeCapsuleId != null ) {
            timeCapsuleTextDto.timeCapsuleId( timeCapsuleId );
        }
        if ( generateTimeCapsuleContentRequest != null ) {
            timeCapsuleTextDto.text( generateTimeCapsuleContentRequest.getText() );
        }

        return timeCapsuleTextDto.build();
    }

    @Override
    public TimeCapsuleTextResponse toTimeCapsuleTextResponse(TimeCapsuleTextDto timeCapsuleTextDto) {
        if ( timeCapsuleTextDto == null ) {
            return null;
        }

        TimeCapsuleTextResponseBuilder timeCapsuleTextResponse = TimeCapsuleTextResponse.builder();

        timeCapsuleTextResponse.timeCapsuleTextId( timeCapsuleTextDto.getTimeCapsuleTextId() );
        timeCapsuleTextResponse.text( timeCapsuleTextDto.getText() );

        return timeCapsuleTextResponse.build();
    }
}
