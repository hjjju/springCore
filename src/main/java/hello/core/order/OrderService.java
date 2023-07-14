package hello.core.order;

//에러나면 alt + enter
public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
