package com.rikka.springBootPractice.model.dto.account;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AccountDTO {

    @NotNull
    private Integer fromAccountId;

    @NotNull
    private Integer toAccountId;

    @NotNull
    @Min(value = 1)
    private Integer money;

    public AccountDTO() {
    }

    public AccountDTO(Integer fromAccountId, Integer toAccountId, Integer money) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.money = money;
    }

    public Integer getFromAccountId() {
        return fromAccountId;
    }

    public Integer getToAccountId() {
        return toAccountId;
    }

    public Integer getMoney() {
        return money;
    }
}
