package com.pbs.services;

import com.pbs.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto object
     */
    void createAccounts(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - String data type
     * @return - using the param this method will return customer details with the existing details
     */
    CustomerDto fetchCustomer(String mobileNumber);

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return - boolean indicates if the update of Account details is successful or not
     */
    boolean updateCustomer(CustomerDto customerDto);

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return - boolean indicates if the Customer and Account are deleted or not
     */
    boolean deleteCustomer(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - by using this mobile number we need to delete the record
     * @return - boolean indicates if the Customer and Account are deleted or not
     */
    boolean deleteCustomer(String mobileNumber);
}
