package com.es.core.model.phone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context/applicationContext-core-test.xml")
public class JdbcPhoneDaoIntTest {
    @Resource
    private PhoneDao phoneDao;

    private Phone phone1;
    private Phone phone2;
    private Set<Color> colors;
    private static int startDbSize = 3;
    private static int saveNewCounter;

    @Before
    public void setup() {
        phone1 = new Phone();
        phone1.setModel("S3");
        phone1.setBrand("Samsung");

        phone2 = new Phone();
        phone2.setModel("Iphone 11");
        phone2.setBrand("Apple");

        colors = new HashSet<>();
    }

    @After
    public void clear() {
        colors.clear();
    }

    @Test(expected = NullPointerException.class)
    public void testSaveExceptionIfKeyNull() {
        phoneDao.save(null);
    }

    @Test
    public void testSaveNewPhone() {
        int size = phoneDao.findAll(null, 0, 1000).size();

        phoneDao.save(phone1);
        saveNewCounter++;

        assertEquals(size, phoneDao.findAll(null, 0, 1000).size());
        assertEquals(phone1.getDescription(), phoneDao.get(1003L).get().getDescription());
    }

    @Test
    public void testSaveUpdatePhone() {
        phone1.setId(1002L);
        phone1.setDescription("test");

        phoneDao.save(phone1);
        Phone phone = phoneDao.get(1002L).get();

        assertNotEquals(phone1.getBrand(), phone.getBrand());
        assertNotEquals(phone1.getModel(), phone.getModel());
        assertEquals(phone1.getDeviceType(), phone.getDeviceType());
        assertEquals(phone1.getDescription(), phone.getDescription());
    }

    @Test
    public void testSaveMathColors() {
        colors.add(new Color(1000L, "Black"));
        colors.add(new Color(1001L, "White"));
        phone2.setColors(colors);

        phoneDao.save(phone2);
        saveNewCounter++;
        List<Phone> phones = phoneDao.findAll(null, 0, 10);
        Phone phone = phones.get(phones.size() - 1);

        assertEquals(phone2.getColors().size(), phone.getColors().size());
    }

    @Test(expected = NullPointerException.class)
    public void testGetExceptionIfKeyNull() {
        phoneDao.get(null);
    }

    @Test
    public void testGetNoResult() {
        assertFalse(phoneDao.get(1L).isPresent());
    }

    @Test
    public void testGetWithResult() {
        assertTrue(phoneDao.get(1000L).isPresent());
    }

    @Test
    public void testGetPhoneStockNull() {
        int stock = phoneDao.getPhoneStock(1000L);

        assertEquals(0, stock);
    }

    @Test
    public void testGetPhoneStockSuccess() {
        int stock = phoneDao.getPhoneStock(1001L);

        assertEquals(11, stock);
    }
}
