/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package shoppingoline.Service;

import java.util.List;
import shoppingoline.model.CartDTO;
import shoppingoline.model.OrderDTO;
import shoppingoline.model.ProductDTO;
import shoppingoline.model.UserDTO;

/**
 *
 * @author choco
 */
public interface MainService {

    List<ProductDTO> searchProduct(String name, String type, int offset);

    ProductDTO getProductById(String id);

    List<String> getAllCategory();

    Boolean insertCart(CartDTO cart);

    List<CartDTO> getCart(String customerID);

    Boolean deleteItemCart(String cartID);

    Boolean transactionCheckOut(String customerID);

    List<OrderDTO> getOrderByCus(String customerID);

    UserDTO login(String user, String pass);

}
