package dev.rneacy.service;

import dev.rneacy.dao.IFundsDAO;
import dev.rneacy.dto.Change;
import dev.rneacy.main.Util;

import java.math.BigDecimal;

public class StubFundsDAO implements IFundsDAO {
    private BigDecimal funds;

    public StubFundsDAO() {
        funds = Util.toFormat("10");
    }

    public StubFundsDAO(BigDecimal funds) {
        this.funds = funds;
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public BigDecimal addFunds(BigDecimal amount) {
        return funds.add(amount);
    }

    public Change spendFunds(BigDecimal amount) {
        return new Change(funds.subtract(amount));
    }
}
