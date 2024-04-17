package com.lucaskalita.airlines;
public interface Mapper<E, D> {

    D fromEntityToDto(E entity);

    E fromDtoToEntity(D dto);
}
