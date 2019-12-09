package com.es.core.model.cart;

import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.core.cart.HttpSessionCartService;
import com.es.core.model.phone.JdbcPhoneDao;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context/applicationContext-core-test.xml")
public class HttpSessionCartTest {

    PhoneDao phoneDao;

    Cart cart;

    HttpSessionCartService cartService;

    @Before
    public void init() {
        cart = new Cart();
        cartService = new HttpSessionCartService();
        phoneDao = mock(JdbcPhoneDao.class);

        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        Phone phone3 = new Phone();

        phone1.setId(1L);
        phone2.setId(2L);
        phone3.setId(3L);

        phone1.setPrice(new BigDecimal(111));
        phone2.setPrice(new BigDecimal(222));
        phone3.setPrice(new BigDecimal(333));

        when(phoneDao.get(1L)).thenReturn(Optional.of(phone1));
        when(phoneDao.get(2L)).thenReturn(Optional.of(phone2));
        when(phoneDao.get(3L)).thenReturn(Optional.of(phone3));

        cartService.setCart(cart);
        cartService.setPhoneDao(phoneDao);

        cartService.addPhone(1L, 10);
        cartService.addPhone(2L, 3);
        cartService.addPhone(3L, 5);
    }

    @Test
    public void testTotalQuantity() {
        assertEquals(18, (int) cart.getTotalQuantity());
    }

    @Test
    public void testTotalPrice() {
        assertEquals(new BigDecimal(3441), cart.getTotalPrice());
    }
}
