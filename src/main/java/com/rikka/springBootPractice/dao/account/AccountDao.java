package com.rikka.springBootPractice.dao.account;

public interface AccountDao {

    boolean existsById(Integer id);

    void decreaseMoney(Integer id, Integer money);

    void addMoney(Integer id, Integer money);
}
