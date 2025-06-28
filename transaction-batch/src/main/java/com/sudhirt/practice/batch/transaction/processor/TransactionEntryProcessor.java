package com.sudhirt.practice.batch.transaction.processor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.sudhirt.practice.batch.transaction.constants.TransactionType;
import com.sudhirt.practice.batch.transaction.entity.Account;
import com.sudhirt.practice.batch.transaction.entity.Transaction;
import com.sudhirt.practice.batch.transaction.exception.AccountNotFoundException;
import com.sudhirt.practice.batch.transaction.service.AccountService;
import com.sudhirt.practice.batch.transaction.entity.TransactionEntry;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionEntryProcessor implements ItemProcessor<TransactionEntry, Transaction> {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private AccountService accountService;

	@Override
	public Transaction process(TransactionEntry item) throws Exception {
		try {
			accountService.get(item.getAccountNo());
		}
		catch (AccountNotFoundException ex) {
			return null;
		}
		return Transaction.builder()
				.account(Account.builder().accountNumber(item.getAccountNo()).build())
				.transactionAmount(Double.valueOf(item.getAmount()))
				.transactionType(TransactionType.valueOf(item.getTransactionType()))
				.transactionDate(LocalDate.parse(item.getTransactionDate(), formatter)).build();
	}

}