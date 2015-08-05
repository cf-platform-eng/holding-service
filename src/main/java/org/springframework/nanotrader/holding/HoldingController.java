package org.springframework.nanotrader.holding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holdings")
public class HoldingController {

	@Autowired
	HoldingRepository holdingRepository;

	@RequestMapping("/count")
	public long count() {
		return holdingRepository.count();
	}

	@RequestMapping("/account/{accountId}/count")
	public long countByAccount(@PathVariable Integer accountId) {
		return holdingRepository.findCountOfHoldings(accountId);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Holding findById(@PathVariable Long id) {
		return holdingRepository.findOne(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Holding save(@RequestBody Holding holding) {
		return holdingRepository.save(holding);
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public void delete(@RequestBody Holding holding) {
		holdingRepository.delete(holding);
	}

	@RequestMapping("/account/{accountId}")
	public List<Holding> findByAccountId(@PathVariable Integer accountId) {
		return holdingRepository.findByAccountId(accountId);
	}

	@RequestMapping("/account/{accountId}/purchaseBasis")
	public Float findPurchaseBasisByAccountId(@PathVariable Integer accountId) {
		Float f = holdingRepository.findPurchaseBasis(accountId);
		if (f == null) {
			return new Float(0.0);
		}
		return f;
	}
}
