package com.es.core.model.phone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context/applicationContext-core-test.xml")
public class PhoneResultSetExtractorTest {
    private final String QUERY_GET = "SELECT phones.*, colors.id as colorId, colors.code as colorCode FROM phones " +
            "INNER JOIN phone2color ON phone2color.phoneId = phones.id " +
            "INNER JOIN colors ON colors.id = phone2color.colorId " +
            "WHERE phones.id = ?";

    @Resource
    private JdbcTemplate jdbcTemplate;

    private PhoneResultSetExtractor phoneResultSetExtractor;

    private Set<Color> expectedColors;
    private Phone resultPhone;

    @Before
    public void setup() {
        phoneResultSetExtractor = new PhoneResultSetExtractor();
        expectedColors = new HashSet<>();
    }

    @Test
    public void testExtractDataWithColors() {
        expectedColors.add(new Color(1000L, "Black"));

        resultPhone = jdbcTemplate.query(QUERY_GET, phoneResultSetExtractor, 1000L);


        assertEquals("ARCHOS", resultPhone.getBrand());
        assertEquals(expectedColors.toString(), resultPhone.getColors().toString());
    }

    @Test
    public void testExtractDataNoColors() {
        resultPhone = jdbcTemplate.query(QUERY_GET, phoneResultSetExtractor, 1002L);

        assertEquals(expectedColors, resultPhone.getColors());
    }

}
