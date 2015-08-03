package org.springframework.nanotrader.holding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(value = "server.port=9876")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("testData.xml")
public class HoldingControllerTest {

	@Autowired
	HoldingController holdingController;

	@Test
	public void testCount() {
		assertEquals(3, holdingController.count());
	}

	@Test
	public void testCountByAccount() {
		assertEquals(2, holdingController.countByAccount(new Integer(5)));
	}

	@Test
	public void testFind() {
		Integer id = new Integer(3);
		Holding h = holdingController.findById(id);
		assertNotNull(h);
		assertEquals(id, h.getId());
	}

	@Test
	public void testSave() {
		Holding h = new Holding();
		h.setAccountId(new Integer(123));
		h.setPurchaseDate(new Date());
		h.setPurchasePrice(new Float(234.56));
		h.setQuantity(new Integer(345));
		h.setQuoteSymbol("FOO");

		Holding h2 = holdingController.save(h);
		assertNotNull(h2);
		assertNotNull(h2.getId());
		assertNotNull(h2.getAccountId());
		assertNotNull(h2.getPurchaseDate());
		assertNotNull(h2.getPurchasePrice());
		assertNotNull(h2.getQuantity());
		assertNotNull(h2.getQuoteSymbol());

		holdingController.delete(h2);
	}

	@Test
	public void testDelete() {
		Holding h = new Holding();
		Holding h2 = holdingController.save(h);
		assertNotNull(h2);
		Integer id = h2.getId();
		holdingController.delete(h2);
		Holding h3 = holdingController.findById(id);
		assertNull(h3);
	}

	@Test
	public void testFindByAccountId() {
		List<Holding> h = holdingController.findByAccountId(new Integer(5));
		assertNotNull(h);
		assertEquals(2, h.size());
	}
}
