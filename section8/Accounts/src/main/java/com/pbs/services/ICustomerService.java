package com.pbs.services;

import com.pbs.dto.CustomerDetailsDto;

public interface ICustomerService {
    /**
     *
     * @param mobileNumber - Input mobile number
     * @return Customer Details based on the given mobile number
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
