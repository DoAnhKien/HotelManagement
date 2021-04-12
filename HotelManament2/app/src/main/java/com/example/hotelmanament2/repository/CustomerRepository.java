package com.example.hotelmanament2.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.hotelmanament2.db.CustomerDao;
import com.example.hotelmanament2.db.CustomerDatabase;
import com.example.hotelmanament2.model.Customer;

import java.util.List;

import javax.inject.Inject;

public class CustomerRepository {
    private CustomerDao customerDao;

    @Inject
    public CustomerRepository(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void insertCustomer(Customer customer) {
        new InsertCustomerTask(customerDao).execute(customer);
    }

    public void updateCustomer(Customer customer) {
        new UpdateCustomerTask(customerDao).execute(customer);
    }

    public void deleteCustomer(Customer customer) {
        new DeleteCustomerTask(customerDao).execute(customer);
    }

    private class InsertCustomerTask extends AsyncTask<Customer, Void, Void> {
        private CustomerDao customerDao;

        public InsertCustomerTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.insertCustomer(customers[0]);
            return null;
        }
    }

    private class UpdateCustomerTask extends AsyncTask<Customer, Void, Void> {
        private CustomerDao customerDao;

        public UpdateCustomerTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.updateCustomer(customers[0]);
            return null;
        }
    }

    private class DeleteCustomerTask extends AsyncTask<Customer, Void, Void> {
        private CustomerDao customerDao;

        public DeleteCustomerTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.deleteCustomer(customers[0]);
            return null;
        }
    }

    public LiveData<List<Customer>> getAllCustomer(int roomNumber) {
        return customerDao.getAllCustomer(roomNumber);
    }

    public LiveData<List<Customer>> getAllCustomerUsing() {
        return customerDao.getAllCustomerUsing();
    }
}
