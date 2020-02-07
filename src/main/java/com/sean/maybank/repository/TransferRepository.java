package com.sean.maybank.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sean.maybank.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Integer>{
	
	@Query(value="select * from transfers u", 
			countQuery="select count(*) from transfers u", 
			nativeQuery = true)
	Page<Transfer> findAllTransfers( Pageable pageableRequest );
	
	@Query(value="select t.* from transfers t, accounts a where t.account_id = a.id "
			+ "and a.account_number = :account_number", 
			countQuery="select count(*) from transfers t, accounts a where t.account_id = a.id "
			+ "and a.account_number = :account_number",
			nativeQuery = true)
	Page<Transfer> findTransfersByAccountNumber(
			Pageable pageableRequest,
			@Param("account_number") String accountNumber);
	
	@Query(value="select t.* from transfers t, accounts a, customers c where t.account_id = a.id "
			+ "and a.customer_id = c.id and c.id = :customer_id", 
			countQuery="select count(*) from transfers t, accounts a, customers c where t.account_id = a.id "
			+ "and a.customer_id = c.id and c.id = :customer_id",
			nativeQuery = true)
	Page<Transfer> findTransfersByCustomerId(
			Pageable pageableRequest,
			@Param("customer_id") int customerId);
	
	@Query(value="select t.* from transfers t where t.description = :description", 
			countQuery="select count(*) from transfers t where t.description = :description",
			nativeQuery = true)
	Page<Transfer> findTransfersByDescription(
			Pageable pageableRequest,
			@Param("description") String description);

}
