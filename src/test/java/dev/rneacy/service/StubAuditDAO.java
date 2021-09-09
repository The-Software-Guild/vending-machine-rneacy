package dev.rneacy.service;

import dev.rneacy.dao.IAuditDAO;
import dev.rneacy.exception.VendingException;

public class StubAuditDAO implements IAuditDAO {
    public void write(String entry) throws VendingException {

    }
}
