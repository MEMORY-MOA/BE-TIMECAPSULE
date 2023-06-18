package com.moa.timecapsule.mapper;

import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto.TimeCapsuleTextDtoBuilder;
import com.moa.timecapsule.entity.TimeCapsuleText;
import com.moa.timecapsule.entity.TimeCapsuleText.TimeCapsuleTextBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-13T22:55:54+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class TimeCapsuleContentMapperImpl implements TimeCapsuleContentMapper {

    @Override
    public TimeCapsuleTextDto toDto(TimeCapsuleText timeCapsuleText) {
        if ( timeCapsuleText == null ) {
            return null;
        }

        TimeCapsuleTextDtoBuilder<?, ?> timeCapsuleTextDto = TimeCapsuleTextDto.builder();

        timeCapsuleTextDto.createdAt( timeCapsuleText.getCreatedAt() );
        timeCapsuleTextDto.modifiedAt( timeCapsuleText.getModifiedAt() );
        timeCapsuleTextDto.timeCapsuleTextId( timeCapsuleText.getTimeCapsuleTextId() );
        timeCapsuleTextDto.timeCapsuleId( timeCapsuleText.getTimeCapsuleId() );
        timeCapsuleTextDto.memberId( timeCapsuleText.getMemberId() );
        timeCapsuleTextDto.text( timeCapsuleText.getText() );

        return timeCapsuleTextDto.build();
    }

    @Override
    public TimeCapsuleText toEntity(TimeCapsuleTextDto timeCapsuleTextDto) {
        if ( timeCapsuleTextDto == null ) {
            return null;
        }

        TimeCapsuleTextBuilder<?, ?> timeCapsuleText = TimeCapsuleText.builder();

        timeCapsuleText.createdAt( timeCapsuleTextDto.getCreatedAt() );
        timeCapsuleText.modifiedAt( timeCapsuleTextDto.getModifiedAt() );
        timeCapsuleText.timeCapsuleTextId( timeCapsuleTextDto.getTimeCapsuleTextId() );
        timeCapsuleText.timeCapsuleId( timeCapsuleTextDto.getTimeCapsuleId() );
        timeCapsuleText.memberId( timeCapsuleTextDto.getMemberId() );
        timeCapsuleText.text( timeCapsuleTextDto.getText() );

        return timeCapsuleText.build();
    }

    @Override
    public List<TimeCapsuleTextDto> toDto(List<TimeCapsuleText> timeCapsuleTextList) {
        if ( timeCapsuleTextList == null ) {
            return null;
        }

        List<TimeCapsuleTextDto> list = new ArrayList<TimeCapsuleTextDto>( timeCapsuleTextList.size() );
        for ( TimeCapsuleText timeCapsuleText : timeCapsuleTextList ) {
            list.add( toDto( timeCapsuleText ) );
        }

        return list;
    }
}
