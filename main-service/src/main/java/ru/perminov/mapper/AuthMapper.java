package ru.perminov.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    TokenDto toTokenDto(Token token);
}