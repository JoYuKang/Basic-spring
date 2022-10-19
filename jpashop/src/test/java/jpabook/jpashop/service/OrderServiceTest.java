package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.excepction.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {


    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;


    @Test
    @DisplayName("상품 주문")
    void order() {
        Member member = getMember("혜진");

        Book book = getBook("이것이 코딩 테스트다", 33000, 20);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        Assertions.assertEquals(OrderStatus.ORDER, getOrder.getStatus(),"상품 주문시 상태는 ORDER");
        Assertions.assertEquals(getOrder.getOrderItems().size(), 1, "상품 주문 아이템 확인");
        Assertions.assertEquals(getOrder.getTotalPrice(), orderCount * 33000, "상품 주문 총 금액 확인");
        Assertions.assertEquals(book.getStockQuantity(), 18, "상품 주문 후 재고 확인");
    }




    @Test
    @DisplayName("상품 취소")
    void cancelOrder() {
        Member member = getMember("지원");
        Book book = getBook("토비의 스프링", 30000, 25);

        int orderCount = 3;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order order = orderRepository.findOne(orderId);

        org.assertj.core.api.Assertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
        org.assertj.core.api.Assertions.assertThat(book.getStockQuantity()).isEqualTo(25);

    }

    @Test
    @DisplayName("상품 재고 수량")
    void orderCount() {
        Member member = getMember("정범");
        Book book = getBook("운영체제", 42000, 3);

        int orderCount = 5;
//        try {
//            orderService.order(member.getId(), book.getId(), orderCount);
//
//        } catch (Exception e) {
//            System.out.println("OrderServiceTest.orderCount");
//        }
        assertThrows(NotEnoughStockException.class,
                () -> orderService.order(member.getId(), book.getId(), orderCount), "재고 수량 부족 예외가 발생해야 한다.");

    }

    private Member getMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("경기", "일산", "123-123"));
        em.persist(member);
        return member;
    }

    private Book getBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

}