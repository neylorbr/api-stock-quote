package br.com.api.stockquotemanager.dto;

import br.com.api.stockquotemanager.model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StockDtoMapper {

    @Mappings({})
    StockDto modelToDto(Stock model);

    @Mapping(target = "id", ignore = true)
    void dtoToModel(StockDto dto, @MappingTarget Stock model);
}
