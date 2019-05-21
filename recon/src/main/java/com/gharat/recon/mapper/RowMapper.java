package com.gharat.recon.mapper;

/**
 * RowMapper provides interface for mapping raw data(String)
 * to specified format.
 * The implementor should provide appropriate implementation for
 * mapping String to T.
 */
@FunctionalInterface
public interface RowMapper<T> {

    T mapRow(String line);
}
