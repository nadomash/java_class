package homework;

import java.util.ArrayDeque;

public class CustomerReverseOrder {

    ArrayDeque<Customer> clist = new ArrayDeque<>();

    public void add(Customer customer) {
        clist.addLast(customer);
    }

    public Customer take() {
        return clist.pollLast();
    }
}
