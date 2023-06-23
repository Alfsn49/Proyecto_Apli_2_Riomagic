package com.aliencode.rms_backend.Clase_generica;

import java.util.List;

public interface GenericClass<T> {
    List<T> findAll();
    T save(T entity);
    T findById(Integer id);
    void delete(Integer id);
}
