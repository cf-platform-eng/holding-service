package org.springframework.nanotrader.holding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quoteService")
public class HoldingController {

	@Autowired
	HoldingRepository holdingRepository;

	@RequestMapping("/count")
	public long count() {
		return holdingRepository.count();
	}

	@RequestMapping("/count?accountId={accountId}")
	public long countByAccount(@Param(value = "accountId") Integer accountId) {
		return holdingRepository.findCountOfHoldings(accountId);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Holding findById(@PathVariable Integer id) {
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

	@RequestMapping("/find?accountId={accountId}")
	public List<Holding> findByAccountId(
			@Param(value = "accountId") Integer accountId) {
		return holdingRepository.findByAccountId(accountId);
	}
}
