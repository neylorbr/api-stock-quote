package br.com.api.stockquotemanager.dto;

import br.com.api.stockquotemanager.model.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface QuoteDtoMapper {

    @Mappings({})
    QuoteDto modelToDto(Quote model);

    @Mapping(target = "id", ignore = true)
    void dtoToModel(QuoteDto dto, @MappingTarget Quote model);
}
