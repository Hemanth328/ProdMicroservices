package com.pbs.services.impl;

import com.pbs.dto.AccountsDto;
import com.pbs.dto.CardsDto;
import com.pbs.dto.CustomerDetailsDto;
import com.pbs.dto.LoansDto;
import com.pbs.entity.Accounts;
import com.pbs.entity.Customer;
import com.pbs.exception.ResourceNotFoundException;
import com.pbs.mapper.AccountsMapper;
import com.pbs.mapper.CustomerMapper;
import com.pbs.repo.AccountsRepository;
import com.pbs.repo.CustomerRepository;
import com.pbs.services.ICustomerService;
import com.pbs.services.client.CardsFeignClient;
import com.pbs.services.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return Customer Details based on the given mobile number
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(new AccountsDto(), accounts));

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.getCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.getLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}
