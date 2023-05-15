package com.mjc.school.service.mapping;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper()
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mapping(target = "id", expression = "java(new java.util.Random().nextLong())")
    @Mapping(target = "createDate", expression = "java(LocalDateTime.now())", dateFormat = "yyyy-MM-dd'T'HH:mm'Z'")
    @Mapping(target = "lastUpdateDate", expression = "java(LocalDateTime.now())", dateFormat = "yyyy-MM-dd'T'HH:mm'Z'")
    NewsModel mapRequestToNews(NewsRequestDto dto);

    NewsResponseDto mapNewsToResponse(NewsModel news);

    List<NewsResponseDto> mapNewsToResponseDtoList(Collection<NewsModel> newsCollection);
}

