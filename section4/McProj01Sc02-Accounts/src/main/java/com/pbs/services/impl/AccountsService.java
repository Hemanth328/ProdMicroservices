package com.pbs.services.impl;

import com.pbs.constants.AccountsConstants;
import com.pbs.dto.AccountsDto;
import com.pbs.dto.CustomerDto;
import com.pbs.entity.Accounts;
import com.pbs.entity.Customer;
import com.pbs.exception.CustomerAlreadyExistsException;
import com.pbs.exception.ResourceNotFoundException;
import com.pbs.mapper.AccountsMapper;
import com.pbs.mapper.CustomerMapper;
import com.pbs.repo.AccountsRepository;
import com.pbs.repo.CustomerRepository;
import com.pbs.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service @AllArgsConstructor
public class AccountsService implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     *
     * @param customerDto - CustomerDto object
     */
    @Override
    public void createAccounts(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with the given number "+customerDto.getMobileNumber());
        }

        Customer customer1 = customerRepository.save(customer);

        accountsRepository.save(createNewAccount(customer1));
    }

    private Accounts createNewAccount(Customer customer) {

        Accounts accounts = new Accounts();

        accounts.setCustomerId(customer.getCustomerId());

        Long randomAccNumber = 1000000000L+ new Random().nextInt(900000000);

        accounts.setAccountNumber(randomAccNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);

        return accounts;

    }

    /**
     *
     * @param mobileNumber - String data type
     * @return - using the param this method will return customer details with the existing details
     */
    @Override
    public CustomerDto fetchCustomer(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customer Id", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(new CustomerDto(), customer);
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(new AccountsDto(), accounts));

        return customerDto;
    }

    /**
     * @param customerDto - CustomerDto Object
     * @return - boolean indicates if the update of Account details is successful or not
     */
    @Override
    public boolean updateCustomer(CustomerDto customerDto) {

        boolean isUpdated = false;

        AccountsDto accountsDto = customerDto.getAccountsDto();

        if(accountsDto != null) {

            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Accounts", "Account Number", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accounts, accountsDto);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);

            isUpdated = true;
        }

        return isUpdated;
    }

    /**
     * @param customerDto - CustomerDto Object
     * @return - boolean indicates if the Customer and Account are deleted or not
     */
    @Override
    public boolean deleteCustomer(CustomerDto customerDto) {

        boolean isDelete = false;

        AccountsDto accountsDto = customerDto.getAccountsDto();

        if(accountsDto != null) {

            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "Account Number", accountsDto.getAccountNumber().toString())
            );
            accountsRepository.delete(accounts);


            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );

            customerRepository.delete(customer);

            isDelete = true;
        }
        return isDelete;
    }

    /**
     * @param mobileNumber - by using this mobile number we need to delete the record
     * @return - boolean indicates if the Customer and Account are deleted or not
     */
    @Override
    public boolean deleteCustomer(String mobileNumber) {

        boolean isDelete = false;

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );

        if(customer != null) {
            accountsRepository.deleteByCustomerId(customer.getCustomerId());
            customerRepository.deleteById(customer.getCustomerId());

            isDelete = true;
        }

        return isDelete;
    }


}
