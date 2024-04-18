package com.lucaskalita.airlines.utilities;
public interface Mapper<E, D> {

    D fromEntityToDto(E entity);

    E fromDtoToEntity(D dto);
}
