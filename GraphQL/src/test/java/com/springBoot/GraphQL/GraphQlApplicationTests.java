package com.springBoot.GraphQL;

import com.springBoot.GraphQL.entity.Order;
import com.springBoot.GraphQL.entity.User;
import com.springBoot.GraphQL.repository.OrderRepository;
import com.springBoot.GraphQL.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GraphQlApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	void allUserServiceFileMethods() {

//		Adding new Users:
		User user1 = User.builder()
				.email("abc@gmail.com")
				.username("abc")
				.phoneNumber("1234567890")
				.orders(new ArrayList<>())
				.build();

		User user2 = User.builder()
				.email("def@gmail.com")
				.username("def")
				.phoneNumber("2345678901")
				.orders(new ArrayList<>())
				.build();

		User user3 = User.builder()
				.email("ghi@gmail.com")
				.username("ghi")
				.phoneNumber("3456789012")
				.orders(new ArrayList<>())
				.build();

		User user4 = User.builder()
				.email("jkl@gmail.com")
				.username("jkl")
				.phoneNumber("4567890123")
				.orders(new ArrayList<>())
				.build();

		List<User> users = new ArrayList<>(List.of(user1, user2, user3, user4));

		users = userRepository.saveAll(users);

		System.out.println("New added Users: ");
		for (User user : users) System.out.println(user);

		List<User> userList = userRepository.findAll();

//		Fetching all the Users:
		System.out.println("List of all the Users: ");
		for (User user : userList) System.out.println(user);

//		Fetching User by userId:
		System.out.println("Fetching Users by their userId: ");

		User userWithId1 = userRepository.findById(1L).orElse(null);
		System.out.println("User with userId: 1 is as follows: " + userWithId1);

		User userWithId7 = userRepository.findById(7L).orElse(null);
		System.out.println("User with userId: 7 is as follows: " + userWithId7);

//		Updating User with by userId:
		System.out.println("Updating User by their userId: ");

		User updatedUser = User.builder()
				.email("mno@gmail.com")
				.username("mno")
				.phoneNumber("5678901234")
				.orders(new ArrayList<>())
				.build();

		User oldUser = userRepository.findById(1L).orElse(null);

        oldUser.setEmail(updatedUser.getEmail());
		oldUser.setUsername(updatedUser.getUsername());
		oldUser.setPhoneNumber(updatedUser.getPhoneNumber());

		oldUser = userRepository.save(oldUser);
		System.out.println("The newly Updated User is: " + oldUser);

//		Deleting User by userId:
		System.out.println("Deleting the User by userId: ");

		User userToDelete = userRepository.findById(1L).orElse(null);

		userRepository.delete(userToDelete);

		System.out.println("The user with userId: " + 1L + " has been deleted Successfully.");

	}

	@Test
	void allOrderServiceFileMethods() {

//		Adding new Orders By User:
		System.out.println("Add new Orders by userId: ");
		User user1 = User.builder()
				.email("abc@gmail.com")
				.username("abc")
				.phoneNumber("1234567890")
				.orders(new ArrayList<>())
				.build();

		User user2 = User.builder()
				.email("def@gmail.com")
				.username("def")
				.phoneNumber("2345678901")
				.orders(new ArrayList<>())
				.build();

		List<User> users = new ArrayList<>(List.of(user1, user2));
		users = userRepository.saveAll(users);

        Order order1 = Order.builder()
				.orderDetails("abc order")
				.address("abc address")
				.price(1999.00)
				.user(user1)
				.build();
		user1.getOrders().add(order1);

		Order order2 = Order.builder()
				.orderDetails("def order")
				.address("def address")
				.price(2999.00)
				.user(user1)
				.build();
		user1.getOrders().add(order2);

		Order order3 = Order.builder()
				.orderDetails("ghi order")
				.address("ghi address")
				.price(3999.00)
				.user(user2)
				.build();
		user2.getOrders().add(order3);

		Order order4 = Order.builder()
				.orderDetails("jkl order")
				.address("jkl address")
				.price(4999.00)
				.user(user2)
				.build();
		user2.getOrders().add(order4);

        List<Order> orders = new ArrayList<>(List.of(order1, order2, order3, order4));
		orders = orderRepository.saveAll(orders);

		System.out.println("List of newly added orders are as follows: ");
		for (Order order : orders) System.out.println(order);

//		Get All Orders:
		System.out.println("List of all the Orders available in the database are as follows: ");
		List<Order> availableOrders = orderRepository.findAll();
		for (Order order : availableOrders) System.out.println(order);

//		Get All Orders by UserId:
		System.out.println("List of all Orders that are done by User with userId: " + 1L + " is as follows: ");

		User userWithId1 = userRepository.findById(1L).orElse(null);
		List<Order> orderListOfUser1 = orderRepository.findByUser(userWithId1);

		if (orderListOfUser1 == null || orderListOfUser1.isEmpty()) {

			System.out.println("No orders done by User with userId: 1.");

		} else {

			for (Order order : orderListOfUser1) System.out.println(order);

		}

		System.out.println("List of all Orders that are done by User with userId: " + 2L + " is as follows: ");

		User userWithId2 = userRepository.findById(2L).orElse(null);
		List<Order> orderListOfUser2 = orderRepository.findByUser(userWithId2);

		if (orderListOfUser2 == null || orderListOfUser2.isEmpty()) {

			System.out.println("No orders done by User with userId: 2.");

		} else {

			for (Order order : orderListOfUser2) System.out.println(order);

		}

//		Update Order by orderId:
		System.out.println("Updating the Order: ");
		Order updatedOrder = Order.builder()
				.price(5999.00)
				.orderDetails("mno order")
				.address("mno address")
				.build();

		Order orderWithId1 = orderRepository.findById(1L).orElse(null);

		User user = orderWithId1.getUser();

		orderWithId1.setOrderDetails(updatedOrder.getOrderDetails());
		orderWithId1.setPrice(updatedOrder.getPrice());
		orderWithId1.setAddress(updatedOrder.getAddress());

		orderWithId1 = orderRepository.save(orderWithId1);

		System.out.println("The Updated order is as follows: ");
		System.out.println(orderWithId1);

		List<Order> orderList = orderRepository.findByUser(user);

		System.out.println("The updated list of Orders for user1 is as follows: ");
		if (orderList == null || orderList.isEmpty()) {

			System.out.println("No orders done by User with userId: 1.");

		} else {

			for (Order order : orderList) System.out.println(order);

		}

//		Delete Order by orderId:
		System.out.println("Deleting the Order with orderId: 1");
		Order orderToDelete = orderRepository.findById(1L).orElse(null);

		orderRepository.delete(orderToDelete);

		System.out.println("The order with orderId: " + orderToDelete.getOrderId() + " has been deleted Successfully.");

		System.out.println("The updated list of orders for user1 is as follows: ");
		List<Order> orderList1 = orderRepository.findByUser(user1);
		for (Order order : orderList1) System.out.println(order);

	}

}
