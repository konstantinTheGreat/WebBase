package org.example.webbase.dao;

import org.example.webbase.entity.AbstractEntity;
import org.example.webbase.exception.DaoException;

import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {
    public abstract boolean insert(T t) throws DaoException;
    public abstract boolean delete(T t) throws DaoException;
    public abstract T update(T t) throws DaoException;
    public abstract List<T> findAll() throws DaoException;
}
