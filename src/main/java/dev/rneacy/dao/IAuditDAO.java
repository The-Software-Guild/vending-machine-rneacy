package dev.rneacy.dao;

import dev.rneacy.exception.VendingException;

public interface IAuditDAO {
    void write(String entry) throws VendingException;
}
