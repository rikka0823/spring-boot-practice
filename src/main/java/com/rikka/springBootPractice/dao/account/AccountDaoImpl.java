package com.rikka.springBootPractice.dao.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    @Qualifier("practiceJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT EXISTS (SELECT 1 FROM account WHERE id = :id)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        return Boolean.TRUE.equals(namedParameterJdbcTemplate.queryForObject(sql, params, Boolean.class));
    }

    @Override
    public void decreaseMoney(Integer id, Integer money) {
        String sql = "UPDATE account SET balance = balance - :money WHERE id =:id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("money", money)
                .addValue("id", id);

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void addMoney(Integer id, Integer money) {
        String sql = "UPDATE account SET balance = balance + :money WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("money", money)
                .addValue("id", id);

        namedParameterJdbcTemplate.update(sql, params);
    }
}