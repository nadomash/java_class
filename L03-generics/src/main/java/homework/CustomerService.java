package homework;

import java.util.*;

public class CustomerService {

    private NavigableMap<Customer, String> navMap = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        return new AbstractMap.SimpleEntry<>(
                new Customer(navMap.firstEntry().getKey()), navMap.firstEntry().getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var result = navMap.tailMap(customer, false).firstEntry();
        return result == null ? null : new AbstractMap.SimpleEntry<>(new Customer(result.getKey()), result.getValue());
    }

    public void add(Customer customer, String data) {
        navMap.put(customer, data);
    }
}
