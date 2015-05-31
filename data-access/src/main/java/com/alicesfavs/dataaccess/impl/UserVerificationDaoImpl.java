package com.alicesfavs.dataaccess.impl;

import com.alicesfavs.dataaccess.UserVerificationDao;
import com.alicesfavs.dataaccess.util.DateTimeUtils;
import com.alicesfavs.datamodel.ModelBase;
import com.alicesfavs.datamodel.UserVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;

@Repository
public class UserVerificationDaoImpl implements UserVerificationDao
{

    private static final String INSERT = "INSERT INTO USER_VERIFICATION (USER_ID, EMAIL_ADDRESS_HASH, "
        + "VERIFICATION_CODE, VERIFIED, EXPIRATION_DATE, EMAIL_SENT_DATE) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE USER_VERIFICATION SET USER_ID = ?, EMAIL_ADDRESS_HASH = ?, "
        + "VERIFICATION_CODE = ?, VERIFIED = ?, EXPIRATION_DATE = ?, EMAIL_SENT_DATE = ? WHERE ID = ?";

    private static final String SELECT_BY_HASH = "SELECT ID, USER_ID, EMAIL_ADDRESS_HASH, VERIFICATION_CODE, "
        + "VERIFIED, EXPIRATION_DATE, EMAIL_SENT_DATE, CREATED_DATE, UPDATED_DATE FROM USER_VERIFICATION "
        + "WHERE EMAIL_ADDRESS_HASH = ?";

    private static final int[] INSERT_PARAM_TYPES =
        { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP };

    private static final int[] UPDATE_PARAM_TYPES =
        { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP, Types.BIGINT };

    private static final int[] SELECT_PARAM_TYPES =
        { Types.VARCHAR };

    @Autowired
    private DaoSupport<UserVerification> daoSupport;

    @Override public UserVerification insertUserVerification(long userId, String emailAddressHash,
        String verificationCode, boolean verified, LocalDateTime expirationDate, LocalDateTime emailSentDate)
    {
        final Object[] params =
            { userId, emailAddressHash, verificationCode, verified ? 1 : 0, DateTimeUtils.toTimestamp(expirationDate),
                DateTimeUtils.toTimestamp(emailSentDate) };
        final ModelBase modelBase = daoSupport.insert(INSERT, INSERT_PARAM_TYPES, params);

        final UserVerification userVerification = new UserVerification(modelBase);
        userVerification.userId = userId;
        userVerification.emailAddressHash = emailAddressHash;
        userVerification.verificationCode = verificationCode;
        userVerification.verified = verified;
        userVerification.expirationDate = expirationDate;
        userVerification.emailSentDate = emailSentDate;

        return userVerification;
    }

    @Override public UserVerification updateUserVerification(UserVerification userVerification)
    {
        final Object[] params =
            { userVerification.userId, userVerification.emailAddressHash, userVerification.verificationCode,
                userVerification.verified, DateTimeUtils.toTimestamp(userVerification.expirationDate),
                DateTimeUtils.toTimestamp(userVerification.emailSentDate), userVerification.id };
        userVerification.updatedDate = daoSupport.update(UPDATE, UPDATE_PARAM_TYPES, params);

        return userVerification;
    }

    @Override public UserVerification selectUserVerificationByHash(String emailAddressHash)
    {
        final Object[] params =
            { emailAddressHash };
        return daoSupport
            .selectObject(SELECT_BY_HASH, SELECT_PARAM_TYPES, params, new UserVerificationRowMapper());
    }

    private class UserVerificationRowMapper implements RowMapper<UserVerification>
    {
        public UserVerification mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final ModelBase modelBase = RowMapperUtils.mapRowToModelBase(rs, rowNum);
            final UserVerification userVerification = new UserVerification(modelBase);
            userVerification.userId = rs.getLong("USER_ID");
            userVerification.emailAddressHash = rs.getString("EMAIL_ADDRESS_HASH");
            userVerification.verificationCode = rs.getString("VERIFICATION_CODE");
            userVerification.verified = rs.getInt("VERIFIED") == 1 ? true : false;
            userVerification.expirationDate = DateTimeUtils.toLocalDateTime(rs.getTimestamp("EXPIRATION_DATE"));
            userVerification.emailSentDate = DateTimeUtils.toLocalDateTime(rs.getTimestamp("EMAIL_SENT_DATE"));

            return userVerification;
        }
    }

}
