package com.es.core.model.phone;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JdbcPhoneDao implements PhoneDao {
    private static final String QUERY_GET_PHONE_BY_KEY =
            "SELECT phones.*, colors.id as colorId, colors.code as colorCode FROM phones " +
                    "LEFT JOIN phone2color ON phone2color.phoneId = phones.id " +
                    "LEFT JOIN colors ON colors.id = phone2color.colorId " +
                    "WHERE phones.id = ?";

    private static final String QUERY_MATCH_PRONE_WITH_COLORS =
            "INSERT INTO phone2color (phoneId, colorId) values (?, ?);";

    private static final String QUERY_UPDATE_PHONE =
            "UPDATE phones SET price = ?, displaySizeInches = ?, weightGr = ?, lengthMm = ?, " +
                    "widthMm = ?, heightMm = ?, announced = ?, deviceType = ?, os = ?, displayResolution = ?, " +
                    "pixelDensity = ?, displayTechnology = ?, backCameraMegapixels = ?, frontCameraMegapixels = ?, " +
                    "ramGb = ?, internalStorageGb = ?, batteryCapacityMah = ?, talkTimeHours = ?, standByTimeHours = ?, " +
                    "bluetooth = ?, positioning = ?, imageUrl = ?, description = ? WHERE id = ?";

    private static final String QUERY_DELETE_PHONE_COLORS_BY_ID =
            "DELETE FROM phone2color WHERE phoneId = ?";

    private static final String QUERY_GET_COLORS_BY_PHONE_ID =
            "SELECT colors.id, colors.code FROM phone2color " +
                    "INNER JOIN colors ON phone2color.colorId = colors.id " +
                    "WHERE phone2color.phoneId = ?";

    private static final String QUERY_GET_PHONES_WITH_OFFSET_AND_LIMIT =
            "SELECT * FROM phones OFFSET ? LIMIT ?";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private BeanPropertyRowMapper<Phone> phoneBeanPropertyRowMapper;

    @Resource
    private BeanPropertyRowMapper<Color> colorBeanPropertyRowMapper;

    @Resource
    private SimpleJdbcInsert phoneSimpleJdbcInsert;

    public Optional<Phone> get(final Long key) {
        Objects.requireNonNull(key, "Key should not be null.");

        Phone phone = jdbcTemplate.query(QUERY_GET_PHONE_BY_KEY, new PhoneResultSetExtractor(), key);

        return phone.getId() == null ? Optional.empty() : Optional.of(phone);
    }

    public void save(final Phone phone) {
        Objects.requireNonNull(phone, "Phone to save should not be null.");

        if (phone.getId() == null) {
            addPhone(phone);
        } else {
            updatePhone(phone);
        }

        matchColors(phone);
    }

    private void updatePhone(final Phone phone) {
        jdbcTemplate.update(QUERY_UPDATE_PHONE, phone.getPrice(), phone.getDisplaySizeInches(),
                phone.getWeightGr(), phone.getLengthMm(), phone.getWidthMm(), phone.getHeightMm(), phone.getAnnounced(),
                phone.getDeviceType(), phone.getOs(), phone.getDisplayResolution(), phone.getPixelDensity(), phone.getDisplayTechnology(),
                phone.getBackCameraMegapixels(), phone.getFrontCameraMegapixels(), phone.getRamGb(), phone.getInternalStorageGb(),
                phone.getBatteryCapacityMah(), phone.getTalkTimeHours(), phone.getStandByTimeHours(), phone.getBluetooth(),
                phone.getPositioning(), phone.getImageUrl(), phone.getDescription(), phone.getId());
        jdbcTemplate.update(QUERY_DELETE_PHONE_COLORS_BY_ID, phone.getId());
    }

    private void addPhone(final Phone phone) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(phone);
        phone.setId(phoneSimpleJdbcInsert.executeAndReturnKey(params).longValue());
    }

    private void matchColors(final Phone phone) {
        Long phoneId = phone.getId();
        List<Color> colors = new ArrayList<>(phone.getColors());

        jdbcTemplate.batchUpdate(QUERY_MATCH_PRONE_WITH_COLORS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setLong(1, phoneId);
                preparedStatement.setLong(2, colors.get(i).getId());
            }

            @Override
            public int getBatchSize() {
                return colors.size();
            }
        });
    }

    public List<Phone> findAll(int offset, int limit) {
        List<Phone> phones = jdbcTemplate.query(QUERY_GET_PHONES_WITH_OFFSET_AND_LIMIT,
                phoneBeanPropertyRowMapper, offset, limit);

        phones.forEach(phone -> phone.setColors(new HashSet<>(
                jdbcTemplate.query(QUERY_GET_COLORS_BY_PHONE_ID, colorBeanPropertyRowMapper, phone.getId()))));

        return phones;
    }

    @Override
    public List<Phone> findAll(String query, int offset, int limit) {
        List<Phone> phones = jdbcTemplate.query(createQueryForSearch(query, offset, limit),
                phoneBeanPropertyRowMapper);

        phones.forEach(phone -> phone.setColors(new HashSet<>(
                jdbcTemplate.query(QUERY_GET_COLORS_BY_PHONE_ID, colorBeanPropertyRowMapper, phone.getId()))));

        return phones;
    }

    private String createQueryForSearch(String query, int offset, int limit) {
        StringBuilder sqlQuery = new StringBuilder(
                "SELECT phones.* FROM phones " +
                "INNER JOIN stocks ON phones.id = stocks.phoneId " +
                "WHERE stock > 0 ");

        if (query != null && !query.trim().isEmpty()) {
            String[] keywords = query.trim().split(" ");
            List<String> modelMatches = Arrays.stream(keywords)
                    .map(keyword -> "brand ILIKE '%" + keyword + "%' OR model ILIKE '%" + keyword + "%'")
                    .collect(Collectors.toList());
            sqlQuery.append("AND (").append(String.join(" OR ", modelMatches)).append(") ");
        }

        sqlQuery.append("OFFSET ").append(offset).append(" LIMIT ").append(limit);

        return sqlQuery.toString();
    }
}
