package org.springframework.nanotrader.holding;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldingRepository extends JpaSpecificationExecutor<Holding>,
		JpaRepository<Holding, Long> {

	public List<Holding> findByAccountId(Integer accountId);

	@Query("SELECT count(h) FROM Holding h WHERE h.accountId = ?1")
	public Long findCountOfHoldings(Integer accountId);

	@Query("SELECT SUM(h.purchasePrice * h.quantity) as purchaseBasis FROM Holding h Where h.accountId =?1")
	public Float findPurchaseBasis(Integer accountId);
}
