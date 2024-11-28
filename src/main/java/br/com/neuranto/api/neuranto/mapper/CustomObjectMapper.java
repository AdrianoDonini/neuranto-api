package br.com.neuranto.api.neuranto.mapper;

import java.util.List;
import org.mapstruct.Mapper;


public interface CustomObjectMapper<T, R> {

    R converterParaDto(T entity);

    T converterParaEntidade(R dto);

    List<R> converterParaListaDeDtos(List<T> entityList);

}
