package com.vfreiman.spring.springexamples.jdbcteamplate;

import com.vfreiman.spring.springexamples.jdbcteamplate.data.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class BannerDAO implements DAO<Banner> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "banner";

    private final String SQL_FIND_PERSON_BY_ID = "SELECT * FROM " + TABLE + " WHERE id = ?";
    private final String SQL_DELETE_PERSON = "DELETE FROM " + TABLE + " WHERE id = ?";
    private final String SQL_UPDATE_PERSON = "UPDATE " + TABLE + " set imgSrc = ?, width = ?, height = ?, targetUrl = ?, langId = ? where id = ?";
    private final String SQL_GET_ALL = "SELECT * FROM " + TABLE;
    private final String SQL_INSERT_PERSON = "INSERT INTO " + TABLE + " (imgSrc, width, height, targetUrl, langId) values(?,?,?,?,?)";

    @Override
    public Banner getById(Long id) {
        Banner res;
        try {
            res = jdbcTemplate.queryForObject(SQL_FIND_PERSON_BY_ID, new Object[] {id}, new BannerMapper());
        } catch (Exception e) {
            res = null;
        }
        return res;
    }

    @Override
    public List<Banner> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new BannerMapper());
    }

    @Override
    public boolean remove(Long id) {
        return jdbcTemplate.update(SQL_DELETE_PERSON, id) > 0;
    }

    @Override
    public boolean update(Long id, Banner o) {
        return jdbcTemplate.update(SQL_UPDATE_PERSON,
                o.getImgSrc(), o.getWidth(), o.getHeight(), o.getTargetUrl(), o.getLangId(), id) > 0;
    }

    @Override
    public long add(Banner o) {
        final PreparedStatementCreator psc = connection -> {
            final PreparedStatement ps = connection.prepareStatement(SQL_INSERT_PERSON, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, o.getImgSrc());
            ps.setInt(2, o.getWidth());
            ps.setInt(3, o.getHeight());
            ps.setString(4, o.getTargetUrl());
            ps.setLong(5, o.getLangId());
            return ps;
        };

        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(psc, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private static class BannerMapper implements RowMapper<Banner> {
        public Banner mapRow(ResultSet resultSet, int i) throws SQLException {
            Banner banner = new Banner();
            banner.setId(resultSet.getLong("id"));
            banner.setImgSrc(resultSet.getString("imgSrc"));
            banner.setWidth(resultSet.getInt("width"));
            banner.setHeight(resultSet.getInt("height"));
            banner.setTargetUrl(resultSet.getString("targetUrl"));
            banner.setLangId(resultSet.getInt("langId"));
            return banner;
        }
    }
}