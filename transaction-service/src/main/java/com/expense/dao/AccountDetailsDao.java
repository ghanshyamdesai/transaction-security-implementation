package com.expense.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.entity.AccountDetails;


@Repository
public interface AccountDetailsDao extends JpaRepository<AccountDetails, Long>{

}
