package com.yammer.litdemo;

import java.util.Hashtable;

public class UserDao {

    // Stupid but easy choice for a demo, ideally this should be a database
    Hashtable table = new Hashtable();

    public SampleProduct getById(long id) throws ProductDoesNotExistException {
        if (!table.containsKey(id)) {
            throw new ProductDoesNotExistException();
        }

        return (SampleProduct)table.get(id);
    }

    public void add(SampleProduct product) throws ProductExistsException {
        if (table.containsKey(product.getId())) {
            throw new ProductExistsException();
        }

        table.put(product.getId(), product);
    }

    public void deleteById(long id) throws ProductDoesNotExistException {
        if (!table.containsKey(id)) {
            throw new ProductDoesNotExistException();
        }

        table.remove(id);
    }

    public void update(SampleProduct product) throws ProductDoesNotExistException {
        deleteById(product.getId());
        try {
            add(product);
        } catch (ProductExistsException e) {
            // This shouldn't happen since we just delete the product
            e.printStackTrace();
        }
    }

    public class ProductDoesNotExistException extends Throwable {
    }

    public class ProductExistsException extends Throwable {
    }
}
