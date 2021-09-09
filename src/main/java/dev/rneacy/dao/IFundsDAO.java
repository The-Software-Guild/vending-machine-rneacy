package dev.rneacy.dao;

import dev.rneacy.dto.Change;

import java.math.BigDecimal;

public interface IFundsDAO {
    BigDecimal getFunds();
    BigDecimal addFunds(BigDecimal amount);
    Change spendFunds(BigDecimal amount);
}
