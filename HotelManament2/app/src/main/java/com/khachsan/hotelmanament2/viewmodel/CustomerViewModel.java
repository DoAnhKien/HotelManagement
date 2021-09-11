package com.khachsan.hotelmanament2.viewmodel;

import android.text.TextUtils;
import android.widget.RadioButton;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.hotelmanament2.R;
import com.khachsan.hotelmanament2.aenum.CustomerStatus;
import com.khachsan.hotelmanament2.model.Customer;
import com.khachsan.hotelmanament2.model.HotelRoom;
import com.khachsan.hotelmanament2.repository.CustomerRepository;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class CustomerViewModel extends ViewModel {
    private CustomerRepository customerRepository;
    private LiveData<List<Customer>> allCustomer;
    private LiveData<List<Customer>> allCustomerUsing;
    public MutableLiveData<CustomerStatus> customerStatus = new MutableLiveData<>();

    @ViewModelInject
    public CustomerViewModel(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    public void checkToUpdateCustomer(Customer customer, TextInputEditText tiedtCustomerName, TextInputEditText tiedCustomerIndentityCard, TextInputEditText tiedtDateOfBirth, RadioButton radioMale, RadioButton radioFemale) {
        if (!TextUtils.isEmpty(tiedtCustomerName.getText().toString()) && !TextUtils.isEmpty(tiedCustomerIndentityCard.getText().toString()) && !TextUtils.isEmpty(tiedtDateOfBirth.getText().toString())) {
            String customerName = tiedtCustomerName.getText().toString();
            String customerIdentityCard = tiedCustomerIndentityCard.getText().toString();
            String customerDateOfBirth = tiedtDateOfBirth.getText().toString();

            if (radioFemale.isChecked()) {
                customer.setAvatarCustomer(R.drawable.ic_female_user);
            }
            if (radioMale.isChecked()) {
                customer.setAvatarCustomer(R.drawable.ic_male_user);
            }
            customer.setCustomerName(customerName);
            customer.setDateOfBirth(customerDateOfBirth);
            customer.setIdentityCard(customerIdentityCard);
            customerRepository.updateCustomer(customer);
            customerStatus.setValue(CustomerStatus.UPDATE_CUSTOMER_SUCCESS);
        } else {
            customerStatus.setValue(CustomerStatus.UPDATE_CUSTOMER_FAIL);
        }

    }

    public boolean savaDataCustomer(TextInputLayout tiedCustomerName, TextInputLayout tiedtCustomerDateOfBirth, TextInputLayout tiedtCustomerIdentityCard, RadioButton radioFemale, RadioButton radioMale, HotelRoom hotelRoom) {
        String customerName = tiedCustomerName.getEditText().getText().toString().trim();
        String customerDateOfBirth = tiedtCustomerDateOfBirth.getEditText().getText().toString().trim();
        String customerIdentityCard = tiedtCustomerIdentityCard.getEditText().getText().toString().trim();
        if (!TextUtils.isEmpty(customerName) && !TextUtils.isEmpty(customerDateOfBirth) && !TextUtils.isEmpty(customerIdentityCard)) {
            if (radioFemale.isChecked()) {
                Customer customer = new Customer(customerName, customerIdentityCard, customerDateOfBirth, hotelRoom.getRoomNumber(), R.drawable.ic_female_user);
                insertCustomer(customer);
                return true;
            } else if (radioMale.isChecked()) {
                Customer customer = new Customer(customerName, customerIdentityCard, customerDateOfBirth, hotelRoom.getRoomNumber(), R.drawable.ic_male_user);
                insertCustomer(customer);
                return true;
            }
        } else {
            tiedCustomerName.setError("Không được để trống thông tin");
            tiedtCustomerDateOfBirth.setError("Không được để trống thông tin");
            tiedtCustomerIdentityCard.setError("Không được để trống thông tin");
        }
        return false;
    }

    public boolean checkToAddCustomer(int maxCustomer, int currentNumberCustomer) {
        if (maxCustomer > currentNumberCustomer) {
            return true;
        } else if (maxCustomer < currentNumberCustomer) {
            return false;
        }
        return false;
    }

    public void insertCustomer(Customer customer) {
        customerRepository.insertCustomer(customer);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.deleteCustomer(customer);
    }

    public LiveData<List<Customer>> getGetAllCustomer(int roomNumber) {
        return allCustomer = customerRepository.getAllCustomer(roomNumber);
    }

    public LiveData<List<Customer>> getAllCustomerUsing() {
        return customerRepository.getAllCustomerUsing();
    }

}
