package com.alicesfavs.dataaccess.impl;

import com.alicesfavs.dataaccess.UserDao;
import com.alicesfavs.datamodel.ModelBase;
import com.alicesfavs.datamodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Repository
public class UserDaoImpl implements UserDao
{

    private static final String INSERT_USER = "INSERT INTO USER (EMAIL_ADDRESS, PASSWORD, NAME, FAV_LIMIT, "
        + "STATUS) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_USER = "UPDATE USER SET EMAIL_ADDRESS = ?, PASSWORD = ?, NAME = ?, "
        + "FAV_LIMIT = ?, STATUS = ? WHERE ID = ?";

    private static final String SELECT_BY =
        "SELECT ID, EMAIL_ADDRESS, PASSWORD, NAME, FAV_LIMIT, STATUS, CREATED_DATE, UPDATED_DATE FROM USER ";

    private static final String SELECT_BY_ID = SELECT_BY + "WHERE ID = ?";

    private static final String SELECT_BY_EMAIL_ADDRESS = SELECT_BY + "WHERE EMAIL_ADDRESS = ?";

    private static final int[] INSERT_PARAM_TYPES =
        { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER };

    private static final int[] UPDATE_PARAM_TYPES =
        { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.BIGINT };

    private static final int[] SELECT_PARAM_TYPES_BY_ID =
        { Types.BIGINT };

    private static final int[] SELECT_PARAM_TYPES_BY_EMAIL_ADDRESS =
        { Types.VARCHAR };

    @Autowired
    private DaoSupport<User> daoSupport;

    @Override public User insertUser(String emailAddress, String password, String name, Integer favLimit,
        User.Status status)
    {
        final Object[] params =
            { emailAddress, password, name, favLimit, status.getCode() };
        final ModelBase modelBase = daoSupport.insert(INSERT_USER, INSERT_PARAM_TYPES, params);

        final User user = new User(modelBase);
        user.emailAddress = emailAddress;
        user.password = password;
        user.name = name;
        user.favLimit = favLimit;
        user.status = status;

        return user;
    }

    @Override public User updateUser(User user)
    {
        final Object[] params =
            { user.emailAddress, user.password, user.name, user.favLimit, user.status.getCode(), user.id };
        user.updatedDate = daoSupport.update(UPDATE_USER, UPDATE_PARAM_TYPES, params);

        return user;
    }

    @Override public User selectUserById(long id)
    {
        final Object[] params =
            { id };
        return daoSupport.selectObject(SELECT_BY_ID, SELECT_PARAM_TYPES_BY_ID, params, new UserRowMapper());
    }

    @Override public User selectUserByEmailAddress(String emailAddress)
    {
        final Object[] params =
            { emailAddress };
        return daoSupport
            .selectObject(SELECT_BY_EMAIL_ADDRESS, SELECT_PARAM_TYPES_BY_EMAIL_ADDRESS, params, new UserRowMapper());
    }

    private class UserRowMapper implements RowMapper<User>
    {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final ModelBase modelBase = RowMapperUtils.mapRowToModelBase(rs, rowNum);
            final String emailAddress = rs.getString("EMAIL_ADDRESS");
            final String password = rs.getString("PASSWORD");
            final String name = rs.getString("NAME");
            final Integer favLimit = rs.getInt("FAV_LIMIT");
            final User.Status status = User.Status.fromCode(rs.getInt("STATUS"));

            final User user = new User(modelBase);
            user.emailAddress = emailAddress;
            user.password = password;
            user.name = name;
            user.favLimit = favLimit;
            user.status = status;

            return user;
        }
    }

}
