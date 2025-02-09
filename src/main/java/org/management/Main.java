package org.management;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final InventoryDAO inventoryDAO = new InventoryDAO();
    private static final ItemDAO itemDAO = new ItemDAO();
    private static final OrderDAO orderDAO = new OrderDAO();
    private static final TaskDAO taskDAO = new TaskDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Customers Management");
            System.out.println("2. Employee Management");
            System.out.println("3. Inventory Management");
            System.out.println("4. Items Management");
            System.out.println("5. Orders Management");
            System.out.println("6. Tasks Management");
            System.out.println("7. Exit");
            
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    handleCustomersMenu(scanner);
                    break;
                case 2:
                    handleEmployeeMenu(scanner);
                    break;
                case 3:
                    handleInventoryMenu(scanner);
                    break;
                case 4:
                    handleItemsMenu(scanner);
                    break;
                case 5:
                    handleOrderMenu(scanner);
                    break;
                case 6:
                    handleTaskMenu(scanner);
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // Customers Management
    private static void handleCustomersMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nCustomers Menu:");
            System.out.println("1. Add new customer");
            System.out.println("2. View all customers");
            System.out.println("3. Search customer by ID");
            System.out.println("4. Search customer by name");
            System.out.println("5. Update customer information");
            System.out.println("6. Delete customer");
            System.out.println("7. View customer orders");
            System.out.println("8. Return to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewCustomer(scanner);
                    break;
                case 2:
                    displayAllCustomers();
                    break;
                case 3:
                    searchCustomerById(scanner);
                    break;
                case 4:
                    searchCustomerByName(scanner);
                    break;
                case 5:
                    updateCustomer(scanner);
                    break;
                case 6:
                    deleteCustomer(scanner);
                    break;
                case 7:
                    viewCustomerOrders(scanner);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void addNewCustomer(Scanner scanner) {
        try {
            Customers newCustomer = new Customers();
            
            System.out.print("Enter first name: ");
            newCustomer.setFirstName(scanner.nextLine());
            
            System.out.print("Enter last name: ");
            newCustomer.setLastName(scanner.nextLine());
            
            System.out.print("Enter email: ");
            newCustomer.setEmail(scanner.nextLine());
            
            System.out.print("Enter phone: ");
            newCustomer.setPhone(scanner.nextLine());
            
            System.out.print("Enter address: ");
            newCustomer.setAddress(scanner.nextLine());
            
            System.out.print("Enter city: ");
            newCustomer.setCity(scanner.nextLine());
            
            customerDAO.addCustomer(newCustomer);
            System.out.println("Customer added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    private static void displayAllCustomers() {
        try {
            ArrayList<Customers> customers = customerDAO.getAllCustomers();
            if (customers.isEmpty()) {
                System.out.println("No customers found.");
                return;
            }
            
            for (Customers customer : customers) {
                System.out.println(customer);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving customers: " + e.getMessage());
        }
    }

    private static void searchCustomerById(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        int id = scanner.nextInt();
        
        try {
            Customers customer = customerDAO.getCustomerById(id);
            if (customer != null) {
                System.out.println(customer);
            } else {
                System.out.println("Customer not found.");
            }
        } catch (Exception e) {
            System.out.println("Error searching customer: " + e.getMessage());
        }
    }

    private static void searchCustomerByName(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        
        try {
            ArrayList<Customers> customers = customerDAO.searchCustomersByName(name);
            if (customers.isEmpty()) {
                System.out.println("No customers found.");
                return;
            }
            
            for (Customers customer : customers) {
                System.out.println(customer);
            }
        } catch (Exception e) {
            System.out.println("Error searching customers: " + e.getMessage());
        }
    }

    private static void updateCustomer(Scanner scanner) {
        try {
            System.out.print("Enter customer ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            Customers customer = customerDAO.getCustomerById(id);
            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }

            System.out.println("Current customer details:");
            System.out.println(customer);
            System.out.println("\nEnter new details (press Enter to keep current value):");
            
            System.out.print("New first name: ");
            String input = scanner.nextLine();
            if (!input.isEmpty()) customer.setFirstName(input);
            
            System.out.print("New last name: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) customer.setLastName(input);
            
            System.out.print("New email: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) customer.setEmail(input);
            
            System.out.print("New phone: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) customer.setPhone(input);
            
            System.out.print("New address: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) customer.setAddress(input);
            
            System.out.print("New city: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) customer.setCity(input);
            
            customerDAO.updateCustomer(customer);
            System.out.println("Customer updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    private static void deleteCustomer(Scanner scanner) {
        System.out.print("Enter customer ID to delete: ");
        int id = scanner.nextInt();
        
        try {
            customerDAO.deleteCustomer(id);
            System.out.println("Customer deleted successfully!");
        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

    private static void viewCustomerOrders(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        
        try {
            ArrayList<Order> orders = orderDAO.getOrdersByCustomerId(customerId);
            if (orders.isEmpty()) {
                System.out.println("No orders found for this customer.");
                return;
            }
            
            System.out.println("Orders for customer ID " + customerId + ":");
            for (Order order : orders) {
                System.out.println(order);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving customer orders: " + e.getMessage());
        }
        }
        // Employee Management
    private static void handleEmployeeMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. Add new employee");
            System.out.println("2. View all employees");
            System.out.println("3. Search employee by ID");
            System.out.println("4. Search employee by name");
            System.out.println("5. Update employee information");
            System.out.println("6. Delete employee");
            System.out.println("7. View employee tasks");
            System.out.println("8. Return to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewEmployee(scanner);
                    break;
                case 2:
                    displayAllEmployees();
                    break;
                case 3:
                    searchEmployeeById(scanner);
                    break;
                case 4:
                    searchEmployeeByName(scanner);
                    break;
                case 5:
                    updateEmployee(scanner);
                    break;
                case 6:
                    deleteEmployee(scanner);
                    break;
                case 7:
                    viewEmployeeTasks(scanner);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void addNewEmployee(Scanner scanner) {
        try {
            Employee newEmployee = new Employee();
            
            System.out.print("Enter first name: ");
            newEmployee.setFirstName(scanner.nextLine());
            
            System.out.print("Enter last name: ");
            newEmployee.setLastName(scanner.nextLine());
            
            System.out.print("Enter position: ");
            newEmployee.setPosition(scanner.nextLine());
            
            System.out.print("Enter salary: ");
            newEmployee.setSalary(scanner.nextDouble());
            scanner.nextLine();
            
            System.out.print("Enter department: ");
            newEmployee.setDepartment(scanner.nextLine());
            
            System.out.print("Enter hire date (YYYY-MM-DD): ");
            newEmployee.setHireDate(scanner.nextLine());
            
            employeeDAO.addEmployee(newEmployee);
            System.out.println("Employee added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    private static void displayAllEmployees() {
        try {
            ArrayList<Employee> employees = employeeDAO.getAllEmployees();
            if (employees.isEmpty()) {
                System.out.println("No employees found.");
                return;
            }
            
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving employees: " + e.getMessage());
        }
    }

    private static void searchEmployeeById(Scanner scanner) {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        
        try {
            Employee employee = employeeDAO.getEmployeeById(id);
            if (employee != null) {
                System.out.println(employee);
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            System.out.println("Error searching employee: " + e.getMessage());
        }
    }

    private static void searchEmployeeByName(Scanner scanner) {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        
        try {
            ArrayList<Employee> employees = employeeDAO.searchEmployeesByName(name);
            if (employees.isEmpty()) {
                System.out.println("No employees found.");
                return;
            }
            
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } catch (Exception e) {
            System.out.println("Error searching employees: " + e.getMessage());
        }
    }

    private static void updateEmployee(Scanner scanner) {
        try {
            System.out.print("Enter employee ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            Employee employee = employeeDAO.getEmployeeById(id);
            if (employee == null) {
                System.out.println("Employee not found.");
                return;
            }

            System.out.println("Current employee details:");
            System.out.println(employee);
            System.out.println("\nEnter new details (press Enter to keep current value):");
            
            System.out.print("New first name: ");
            String input = scanner.nextLine();
            if (!input.isEmpty()) employee.setFirstName(input);
            
            System.out.print("New last name: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) employee.setLastName(input);
            
            System.out.print("New position: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) employee.setPosition(input);
            
            System.out.print("New salary (press Enter to skip): ");
            input = scanner.nextLine();
            if (!input.isEmpty()) employee.setSalary(Double.parseDouble(input));
            
            System.out.print("New department: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) employee.setDepartment(input);
            
            employeeDAO.updateEmployee(employee);
            System.out.println("Employee updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        
        try {
            employeeDAO.deleteEmployee(id);
            System.out.println("Employee deleted successfully!");
        } catch (Exception e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    private static void viewEmployeeTasks(Scanner scanner) {
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        
        try {
            ArrayList<Task> tasks = taskDAO.getTasksByEmployeeId(employeeId);
            if (tasks.isEmpty()) {
                System.out.println("No tasks found for this employee.");
                return;
            }
            
            System.out.println("Tasks for employee ID " + employeeId + ":");
            for (Task task : tasks) {
                System.out.println(task);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving employee tasks: " + e.getMessage());
        }
    }
    // Inventory Management
    private static void handleInventoryMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nInventory Menu:");
            System.out.println("1. View current inventory");
            System.out.println("2. Add stock");
            System.out.println("3. Remove stock");
            System.out.println("4. Check low stock items");
            System.out.println("5. Update stock levels");
            System.out.println("6. Generate inventory report");
            System.out.println("7. Return to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewCurrentInventory();
                    break;
                case 2:
                    addStock(scanner);
                    break;
                case 3:
                    removeStock(scanner);
                    break;
                case 4:
                    checkLowStock();
                    break;
                case 5:
                    updateStockLevels(scanner);
                    break;
                case 6:
                    generateInventoryReport();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void viewCurrentInventory() {
        try {
            ArrayList<Inventory> inventoryList = inventoryDAO.getAllInventory();
            if (inventoryList.isEmpty()) {
                System.out.println("No inventory items found.");
                return;
            }
            
            for (Inventory item : inventoryList) {
                Items product = itemDAO.getItemById(item.getItemId());
                System.out.println("Item: " + product.getName() +
                                 ", Quantity: " + item.getQuantity() +
                                 ", Location: " + item.getLocation());
            }
        } catch (Exception e) {
            System.out.println("Error viewing inventory: " + e.getMessage());
        }
    }

    private static void addStock(Scanner scanner) {
        try {
            System.out.print("Enter item ID: ");
            int itemId = scanner.nextInt();
            
            System.out.print("Enter quantity to add: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Enter location: ");
            String location = scanner.nextLine();
            
            Inventory inventory = inventoryDAO.getInventoryByItemId(itemId);
            if (inventory != null) {
                inventory.setQuantity(inventory.getQuantity() + quantity);
                inventoryDAO.updateInventory(inventory);
            } else {
                inventory = new Inventory();
                inventory.setItemId(itemId);
                inventory.setQuantity(quantity);
                inventory.setLocation(location);
                inventory.setLastUpdated(java.time.LocalDate.now().toString());
                inventory.setMinimumStock(10); // Default minimum stock
                inventory.setMaximumStock(100); // Default maximum stock
                inventoryDAO.addInventory(inventory);
            }
            System.out.println("Stock added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding stock: " + e.getMessage());
        }
    }

    private static void removeStock(Scanner scanner) {
        try {
            System.out.print("Enter item ID: ");
            int itemId = scanner.nextInt();
            
            System.out.print("Enter quantity to remove: ");
            int quantity = scanner.nextInt();
            
            Inventory inventory = inventoryDAO.getInventoryByItemId(itemId);
            if (inventory == null) {
                System.out.println("Item not found in inventory.");
                return;
            }
            
            if (inventory.getQuantity() < quantity) {
                System.out.println("Error: Not enough stock available!");
                return;
            }
            
            inventory.setQuantity(inventory.getQuantity() - quantity);
            inventoryDAO.updateInventory(inventory);
            System.out.println("Stock removed successfully!");
        } catch (Exception e) {
            System.out.println("Error removing stock: " + e.getMessage());
        }
    }

    private static void checkLowStock() {
        try {
            ArrayList<Inventory> lowStockItems = inventoryDAO.getLowStockItems();
            if (lowStockItems.isEmpty()) {
                System.out.println("No items are low in stock.");
                return;
            }
            
            System.out.println("\nLow Stock Items:");
            for (Inventory item : lowStockItems) {
                Items product = itemDAO.getItemById(item.getItemId());
                System.out.println("Item: " + product.getName() +
                                 ", Current Quantity: " + item.getQuantity() +
                                 ", Minimum Stock: " + item.getMinimumStock());
            }
        } catch (Exception e) {
            System.out.println("Error checking low stock: " + e.getMessage());
        }
    }

    private static void updateStockLevels(Scanner scanner) {
        try {
            System.out.print("Enter item ID: ");
            int itemId = scanner.nextInt();
            
            Inventory inventory = inventoryDAO.getInventoryByItemId(itemId);
            if (inventory == null) {
                System.out.println("Item not found in inventory.");
                return;
            }
            
            System.out.println("Current stock level: " + inventory.getQuantity());
            System.out.print("Enter new stock level: ");
            inventory.setQuantity(scanner.nextInt());
            
            inventoryDAO.updateInventory(inventory);
            System.out.println("Stock level updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating stock levels: " + e.getMessage());
        }
    }

    private static void generateInventoryReport() {
        try {
            ArrayList<Inventory> inventoryList = inventoryDAO.getAllInventory();
            if (inventoryList.isEmpty()) {
                System.out.println("No inventory data to generate report.");
                return;
            }
            
            System.out.println("\nInventory Report");
            System.out.println("================");
            int totalItems = 0;
            int lowStockItems = 0;
            
            for (Inventory item : inventoryList) {
                Items product = itemDAO.getItemById(item.getItemId());
                totalItems += item.getQuantity();
                
                if (item.getQuantity() <= item.getMinimumStock()) {
                    lowStockItems++;
                }
                
                System.out.println("Item: " + product.getName());
                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Location: " + item.getLocation());
                System.out.println("Last Updated: " + item.getLastUpdated());
                System.out.println("--------------------");
            }
            
            System.out.println("Total Items in Stock: " + totalItems);
            System.out.println("Low Stock Items: " + lowStockItems);
            System.out.println("================");
        } catch (Exception e) {
            System.out.println("Error generating inventory report: " + e.getMessage());
        }
    }
    // Items Management
    private static void handleItemsMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nItems Menu:");
            System.out.println("1. Add new item");
            System.out.println("2. View all items");
            System.out.println("3. Search item by ID");
            System.out.println("4. Search item by name");
            System.out.println("5. Update item information");
            System.out.println("6. Delete item");
            System.out.println("7. Update item price");
            System.out.println("8. Return to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewItem(scanner);
                    break;
                case 2:
                    viewAllItems();
                    break;
                case 3:
                    searchItemById(scanner);
                    break;
                case 4:
                    searchItemByName(scanner);
                    break;
                case 5:
                    updateItem(scanner);
                    break;
                case 6:
                    deleteItem(scanner);
                    break;
                case 7:
                    updateItemPrice(scanner);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void addNewItem(Scanner scanner) {
        try {
            Items newItem = new Items();
            
            System.out.print("Enter item name: ");
            newItem.setName(scanner.nextLine());
            
            System.out.print("Enter item description: ");
            newItem.setDescription(scanner.nextLine());
            
            System.out.print("Enter price: ");
            newItem.setPrice(scanner.nextDouble());
            scanner.nextLine();
            
            System.out.print("Enter category: ");
            newItem.setCategory(scanner.nextLine());
            
            System.out.print("Enter supplier: ");
            newItem.setSupplier(scanner.nextLine());
            
            newItem.setActive(true);
            
            itemDAO.addItem(newItem);
            System.out.println("Item added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding item: " + e.getMessage());
        }
    }

    private static void viewAllItems() {
        try {
            ArrayList<Items> items = itemDAO.getAllItems();
            if (items.isEmpty()) {
                System.out.println("No items found.");
                return;
            }
            
            for (Items item : items) {
                System.out.println(item);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving items: " + e.getMessage());
        }
    }

    private static void searchItemById(Scanner scanner) {
        System.out.print("Enter item ID: ");
        int id = scanner.nextInt();
        
        try {
            Items item = itemDAO.getItemById(id);
            if (item != null) {
                System.out.println(item);
            } else {
                System.out.println("Item not found.");
            }
        } catch (Exception e) {
            System.out.println("Error searching item: " + e.getMessage());
        }
    }

    private static void searchItemByName(Scanner scanner) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        
        try {
            ArrayList<Items> items = itemDAO.searchItemsByName(name);
            if (items.isEmpty()) {
                System.out.println("No items found.");
                return;
            }
            
            for (Items item : items) {
                System.out.println(item);
            }
        } catch (Exception e) {
            System.out.println("Error searching items: " + e.getMessage());
        }
    }

    private static void updateItem(Scanner scanner) {
        try {
            System.out.print("Enter item ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            Items item = itemDAO.getItemById(id);
            if (item == null) {
                System.out.println("Item not found.");
                return;
            }

            System.out.println("Current item details:");
            System.out.println(item);
            System.out.println("\nEnter new details (press Enter to keep current value):");
            
            System.out.print("New name: ");
            String input = scanner.nextLine();
            if (!input.isEmpty()) item.setName(input);
            
            System.out.print("New description: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) item.setDescription(input);
            
            System.out.print("New category: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) item.setCategory(input);
            
            System.out.print("New supplier: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) item.setSupplier(input);
            
            System.out.print("Is item active? (true/false, press Enter to skip): ");
            input = scanner.nextLine();
            if (!input.isEmpty()) item.setActive(Boolean.parseBoolean(input));
            
            itemDAO.updateItem(item);
            System.out.println("Item updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating item: " + e.getMessage());
        }
    }

    private static void deleteItem(Scanner scanner) {
        System.out.print("Enter item ID to delete: ");
        int id = scanner.nextInt();
        
        try {
            itemDAO.deleteItem(id);
            System.out.println("Item deleted successfully!");
        } catch (Exception e) {
            System.out.println("Error deleting item: " + e.getMessage());
        }
    }

    private static void updateItemPrice(Scanner scanner) {
        try {
            System.out.print("Enter item ID: ");
            int id = scanner.nextInt();
            
            Items item = itemDAO.getItemById(id);
            if (item == null) {
                System.out.println("Item not found.");
                return;
            }
            
            System.out.println("Current price: $" + item.getPrice());
            System.out.print("Enter new price: $");
            item.setPrice(scanner.nextDouble());
            
            itemDAO.updateItem(item);
            System.out.println("Price updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating price: " + e.getMessage());
        }
    }
    // Orders Management
    private static void handleOrderMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nOrders Menu:");
            System.out.println("1. Create new order");
            System.out.println("2. View all orders");
            System.out.println("3. Search order by ID");
            System.out.println("4. Search orders by customer");
            System.out.println("5. Update order status");
            System.out.println("6. Cancel order");
            System.out.println("7. Generate order invoice");
            System.out.println("8. Return to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createNewOrder(scanner);
                    break;
                case 2:
                    viewAllOrders();
                    break;
                case 3:
                    searchOrderById(scanner);
                    break;
                case 4:
                    searchOrdersByCustomer(scanner);
                    break;
                case 5:
                    updateOrderStatus(scanner);
                    break;
                case 6:
                    cancelOrder(scanner);
                    break;
                case 7:
                    generateOrderInvoice(scanner);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void createNewOrder(Scanner scanner) {
        try {
            Order newOrder = new Order();
            
            System.out.print("Enter customer ID: ");
            int customerId = scanner.nextInt();
            scanner.nextLine();
            
            // Verify customer exists
            Customers customer = customerDAO.getCustomerById(customerId);
            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }
            
            newOrder.setCustomerId(customerId);
            newOrder.setOrderDate(java.time.LocalDate.now().toString());
            newOrder.setStatus("New");
            
            System.out.print("Enter shipping address: ");
            newOrder.setShippingAddress(scanner.nextLine());
            
            System.out.print("Enter payment method: ");
            newOrder.setPaymentMethod(scanner.nextLine());
            
            // Calculate total amount (could be expanded to handle multiple items)
            System.out.print("Enter total amount: ");
            newOrder.setTotalAmount(scanner.nextDouble());
            
            orderDAO.addOrder(newOrder);
            System.out.println("Order created successfully!");
        } catch (Exception e) {
            System.out.println("Error creating order: " + e.getMessage());
        }
    }

    private static void viewAllOrders() {
        try {
            ArrayList<Order> orders = orderDAO.getAllOrders();
            if (orders.isEmpty()) {
                System.out.println("No orders found.");
                return;
            }
            
            for (Order order : orders) {
                printOrderDetails(order);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving orders: " + e.getMessage());
        }
    }

    private static void searchOrderById(Scanner scanner) {
        System.out.print("Enter order ID: ");
        int id = scanner.nextInt();
        
        try {
            Order order = orderDAO.getOrderById(id);
            if (order != null) {
                printOrderDetails(order);
            } else {
                System.out.println("Order not found.");
            }
        } catch (Exception e) {
            System.out.println("Error searching order: " + e.getMessage());
        }
    }

    private static void printOrderDetails(Order order) {
        try {
            Customers customer = customerDAO.getCustomerById(order.getCustomerId());
            System.out.println("\nOrder Details:");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
            System.out.println("Date: " + order.getOrderDate());
            System.out.println("Status: " + order.getStatus());
            System.out.println("Total Amount: $" + order.getTotalAmount());
            System.out.println("Shipping Address: " + order.getShippingAddress());
            System.out.println("Payment Method: " + order.getPaymentMethod());
            System.out.println("------------------------");
        } catch (Exception e) {
            System.out.println("Error printing order details: " + e.getMessage());
        }
    }

    private static void searchOrdersByCustomer(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        
        try {
            ArrayList<Order> orders = orderDAO.getOrdersByCustomerId(customerId);
            if (orders.isEmpty()) {
                System.out.println("No orders found for this customer.");
                return;
            }
            
            for (Order order : orders) {
                printOrderDetails(order);
            }
        } catch (Exception e) {
            System.out.println("Error searching orders: " + e.getMessage());
        }
    }

    private static void updateOrderStatus(Scanner scanner) {
        try {
            System.out.print("Enter order ID: ");
            int orderId = scanner.nextInt();
            scanner.nextLine();
            
            Order order = orderDAO.getOrderById(orderId);
            if (order == null) {
                System.out.println("Order not found.");
                return;
            }
            
            System.out.println("Current status: " + order.getStatus());
            System.out.println("Available statuses: New, Processing, Shipped, Delivered, Cancelled");
            System.out.print("Enter new status: ");
            String newStatus = scanner.nextLine();
            
            order.setStatus(newStatus);
            orderDAO.updateOrder(order);
            System.out.println("Order status updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating order status: " + e.getMessage());
        }
    }

    private static void cancelOrder(Scanner scanner) {
        try {
            System.out.print("Enter order ID to cancel: ");
            int orderId = scanner.nextInt();
            
            Order order = orderDAO.getOrderById(orderId);
            if (order == null) {
                System.out.println("Order not found.");
                return;
            }
            
            if (order.getStatus().equals("Delivered")) {
                System.out.println("Cannot cancel a delivered order!");
                return;
            }
            
            order.setStatus("Cancelled");
            orderDAO.updateOrder(order);
            System.out.println("Order cancelled successfully!");
        } catch (Exception e) {
            System.out.println("Error cancelling order: " + e.getMessage());
        }
    }

    private static void generateOrderInvoice(Scanner scanner) {
        try {
            System.out.print("Enter order ID for invoice: ");
            int orderId = scanner.nextInt();
            
            Order order = orderDAO.getOrderById(orderId);
            if (order == null) {
                System.out.println("Order not found.");
                return;
            }
            
            Customers customer = customerDAO.getCustomerById(order.getCustomerId());
            
            System.out.println("\n=================================");
            System.out.println("           INVOICE");
            System.out.println("=================================");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Date: " + order.getOrderDate());
            System.out.println("---------------------------------");
            System.out.println("Customer Details:");
            System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone: " + customer.getPhone());
            System.out.println("---------------------------------");
            System.out.println("Shipping Address: " + order.getShippingAddress());
            System.out.println("Payment Method: " + order.getPaymentMethod());
            System.out.println("---------------------------------");
            System.out.println("Total Amount: $" + order.getTotalAmount());
            System.out.println("=================================");
        } catch (Exception e) {
            System.out.println("Error generating invoice: " + e.getMessage());
        }
    }
    // Tasks Management
    private static void handleTaskMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nTasks Menu:");
            System.out.println("1. Create new task");
            System.out.println("2. View all tasks");
            System.out.println("3. View tasks by employee");
            System.out.println("4. View tasks by status");
            System.out.println("5. Update task status");
            System.out.println("6. Assign task to employee");
            System.out.println("7. Delete task");
            System.out.println("8. Set task priority");
            System.out.println("9. Return to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createNewTask(scanner);
                    break;
                case 2:
                    viewAllTasks();
                    break;
                case 3:
                    viewTasksByEmployee(scanner);
                    break;
                case 4:
                    viewTasksByStatus(scanner);
                    break;
                case 5:
                    updateTaskStatus(scanner);
                    break;
                case 6:
                    assignTask(scanner);
                    break;
                case 7:
                    deleteTask(scanner);
                    break;
                case 8:
                    setTaskPriority(scanner);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void createNewTask(Scanner scanner) {
        try {
            Task newTask = new Task();
            
            System.out.print("Enter task title: ");
            newTask.setTitle(scanner.nextLine());
            
            System.out.print("Enter task description: ");
            newTask.setDescription(scanner.nextLine());
            
            System.out.print("Enter employee ID to assign: ");
            int employeeId = scanner.nextInt();
            scanner.nextLine();
            
            // Verify employee exists
            Employee employee = employeeDAO.getEmployeeById(employeeId);
            if (employee == null) {
                System.out.println("Employee not found.");
                return;
            }
            newTask.setEmployeeId(employeeId);
            
            System.out.print("Enter due date (YYYY-MM-DD): ");
            newTask.setDueDate(scanner.nextLine());
            
            System.out.print("Enter priority (High/Medium/Low): ");
            newTask.setPriority(scanner.nextLine());
            
            newTask.setStatus("New");
            
            taskDAO.addTask(newTask);
            System.out.println("Task created successfully!");
        } catch (Exception e) {
            System.out.println("Error creating task: " + e.getMessage());
        }
    }

    private static void viewAllTasks() {
        try {
            ArrayList<Task> tasks = taskDAO.getAllTasks();
            if (tasks.isEmpty()) {
                System.out.println("No tasks found.");
                return;
            }
            
            for (Task task : tasks) {
                printTaskDetails(task);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
    }

    private static void printTaskDetails(Task task) {
        try {
            Employee employee = employeeDAO.getEmployeeById(task.getEmployeeId());
            System.out.println("\nTask Details:");
            System.out.println("ID: " + task.getTaskId());
            System.out.println("Title: " + task.getTitle());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Assigned to: " + employee.getFirstName() + " " + employee.getLastName());
            System.out.println("Due Date: " + task.getDueDate());
            System.out.println("Priority: " + task.getPriority());
            System.out.println("Status: " + task.getStatus());
            System.out.println("------------------------");
        } catch (Exception e) {
            System.out.println("Error printing task details: " + e.getMessage());
        }
    }

    private static void viewTasksByEmployee(Scanner scanner) {
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        
        try {
            ArrayList<Task> tasks = taskDAO.getTasksByEmployeeId(employeeId);
            if (tasks.isEmpty()) {
                System.out.println("No tasks found for this employee.");
                return;
            }
            
            for (Task task : tasks) {
                printTaskDetails(task);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
    }

    private static void viewTasksByStatus(Scanner scanner) {
        System.out.print("Enter status (New/In Progress/Completed): ");
        String status = scanner.nextLine();
        
        try {
            ArrayList<Task> tasks = taskDAO.getTasksByStatus(status);
            if (tasks.isEmpty()) {
                System.out.println("No tasks found with this status.");
                return;
            }
            
            for (Task task : tasks) {
                printTaskDetails(task);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
    }

    private static void updateTaskStatus(Scanner scanner) {
        try {
            System.out.print("Enter task ID: ");
            int taskId = scanner.nextInt();
            scanner.nextLine();
            
            Task task = taskDAO.getTaskById(taskId);
            if (task == null) {
                System.out.println("Task not found.");
                return;
            }
            
            System.out.println("Current status: " + task.getStatus());
            System.out.println("Available statuses: New, In Progress, Completed");
            System.out.print("Enter new status: ");
            task.setStatus(scanner.nextLine());
            
            taskDAO.updateTask(task);
            System.out.println("Task status updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating task status: " + e.getMessage());
        }
    }

    private static void assignTask(Scanner scanner) {
        try {
            System.out.print("Enter task ID: ");
            int taskId = scanner.nextInt();
            
            Task task = taskDAO.getTaskById(taskId);
            if (task == null) {
                System.out.println("Task not found.");
                return;
            }
            
            System.out.print("Enter employee ID to assign to: ");
            int employeeId = scanner.nextInt();
            
            Employee employee = employeeDAO.getEmployeeById(employeeId);
            if (employee == null) {
                System.out.println("Employee not found.");
                return;
            }
            
            task.setEmployeeId(employeeId);
            taskDAO.updateTask(task);
            System.out.println("Task assigned successfully!");
        } catch (Exception e) {
            System.out.println("Error assigning task: " + e.getMessage());
        }
    }

    private static void deleteTask(Scanner scanner) {
        System.out.print("Enter task ID to delete: ");
        int taskId = scanner.nextInt();
        
        try {
            taskDAO.deleteTask(taskId);
            System.out.println("Task deleted successfully!");
        } catch (Exception e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }

    private static void setTaskPriority(Scanner scanner) {
        try {
            System.out.print("Enter task ID: ");
            int taskId = scanner.nextInt();
            scanner.nextLine();
            
            Task task = taskDAO.getTaskById(taskId);
            if (task == null) {
                System.out.println("Task not found.");
                return;
            }
            
            System.out.println("Current priority: " + task.getPriority());
            System.out.println("Available priorities: High, Medium, Low");
            System.out.print("Enter new priority: ");
            task.setPriority(scanner.nextLine());
            
            taskDAO.updateTask(task);
            System.out.println("Task priority updated successfully!");
        } catch (Exception e) {
            System.out.println("Error setting task priority: " + e.getMessage());
        }
    }
}
