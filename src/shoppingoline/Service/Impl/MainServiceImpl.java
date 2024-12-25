/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingoline.Service.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.swing.JOptionPane;
import shoppingoline.Service.MainService;
import shoppingoline.Utils.DBConnectionDTO;
import shoppingoline.Utils.OderStatusType;
import shoppingoline.model.CartDTO;
import shoppingoline.model.OrderDTO;
import shoppingoline.model.OrderDetailDTO;
import shoppingoline.model.ProductDTO;
import shoppingoline.model.UserDTO;

/**
 * @author choco
 */
public class MainServiceImpl implements MainService {

    public UserDTO login(String user, String pass) {
        UserDTO rc = new UserDTO();
        try {
            String sql = "select * from customers WHERE user_name = '" + user + "' and password = '" + pass + "' ;";

            Connection conn = DBConnectionDTO.getConnection();

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                rc.setId(rs.getString("id") != null ? rs.getString("id") : "");
                rc.setUserName(rs.getString("user_name") != null ? rs.getString("user_name") : "");
                rc.setFullName(rs.getString("full_name") != null ? rs.getString("full_name") : "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rc;
    }

    @Override
    public List<ProductDTO> searchProduct(String name, String type, int offset) {
        List<ProductDTO> products = new ArrayList<>();
        try {
            String sql = "select * from products WHERE quantity > 0 and product_name like '" + name + "%' limit 6 offset " + offset;
            if (!type.equalsIgnoreCase("ALL")) {
                sql += "select * from products WHERE quantity > 0 and product_name like '" + name + "%' and category = '" + type + "'  limit 6 offset " + offset;
            }
            if (!type.equalsIgnoreCase("ALL") && name.trim().length() == 0) {
                sql = "select * from products WHERE quantity > 0 and category = '" + type + "' limit 6 offset " + offset;
            }

            Connection conn = DBConnectionDTO.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ProductDTO rc = new ProductDTO();
                rc.setId(rs.getString("id") != null ? rs.getString("id") : "");
                rc.setTitle(rs.getString("product_name") != null ? rs.getString("product_name") : "");
                rc.setUrlImage(rs.getString("img_url") != null ? rs.getString("img_url") : "");
                rc.setCategory(rs.getString("category") != null ? rs.getString("category") : "");
                rc.setDesc(rs.getString("description") != null ? rs.getString("description") : "");
                rc.setPrice(rs.getString("price") != null ? rs.getString("price") : "");
                rc.setQuantity(rs.getInt("quantity"));
                products.add(rc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public ProductDTO getProductById(String id) {
        ProductDTO rc = new ProductDTO();
        try {
            String sql = "select * from products WHERE id = '" + id + "' ";

            Connection conn = DBConnectionDTO.getConnection();

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                rc.setId(rs.getString("id") != null ? rs.getString("id") : "");
                rc.setTitle(rs.getString("product_name") != null ? rs.getString("product_name") : "");
                rc.setUrlImage(rs.getString("img_url") != null ? rs.getString("img_url") : "");
                rc.setCategory(rs.getString("category") != null ? rs.getString("category") : "");
                rc.setDesc(rs.getString("description") != null ? rs.getString("description") : "");
                rc.setPrice(rs.getString("price") != null ? rs.getString("price") : "");
                rc.setQuantity(rs.getInt("quantity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rc;
    }

    @Override
    public List<String> getAllCategory() {
        List<String> rc = new ArrayList<>();
        try {
            String sql = "select distinct(category) from products";

            Connection conn = DBConnectionDTO.getConnection();

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                rc.add(rs.getString("category") != null ? rs.getString("category") : "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rc;
    }
//    public Boolean updateSPdetail(String codeSPDetail, int soLuong, double donGiaNhap, double donGiaXuatle, double donGiaXuatBuon) {
//        Boolean res = false;
//        LocalDate date = LocalDate.now();
//        String sql = "UPDATE san_pham_detail SET so_luong =  '" + soLuong + "', don_gia_nhap = '" + donGiaNhap + "', don_gia_xuat_ban_le = '" + donGiaXuatle + "', don_gia_xuat_ban_buon = '" + donGiaXuatBuon + "', updated_at =  '" + date + "'  WHERE code_sp_detail = '" + codeSPDetail + "' ";
//        Connection conn = DBConnectionDTO.getConnection();
//        try {
//            Statement st = conn.createStatement();
//            st.executeUpdate(sql);
//            res = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return res;
//    }
////----------------------------------------- cart session===========================================================>

    public Boolean insertCart(CartDTO cart) {
        Boolean res = false;
        LocalDate date = LocalDate.now();
        // kiem tra xem trong Cart co san product chua, neu co thi update so luong
        CartDTO cartExist = getProductInCart(cart.getCustomerId(), cart.getProductId());
        String sql = "INSERT INTO cart(CUSTOMER_ID, PRODUCT_ID, QUANTITY, CREATED_AT, UPDATED_AT) "
                + "VALUES("
                + " '" + cart.getCustomerId() + "', "
                + " '" + cart.getProductId() + "', "
                + " '1', "
                + " '" + date + "', "
                + " '" + date + "' "
                + ")";
        if (!Objects.isNull(cartExist) && !Objects.isNull(cartExist.getId()) && cartExist.getQuantity() > 0) {
            int quantity = cartExist.getQuantity() + 1;
            sql = "update cart set quantity =" + quantity + "  where id ='" + cartExist.getId() + "';";
        }
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<CartDTO> getCart(String customerID) {
        List<CartDTO> listCart = new ArrayList<>();
        String sql = "select t.id as id, t.product_id, t.customer_id,t.quantity,t1.product_name,t1.price "
                + " from cart t join products t1 on t.product_id = t1.id where customer_id ='" + customerID + "';";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                CartDTO rc = new CartDTO();
                rc.setId(rs.getString("id") != null ? rs.getString("id") : "");
                rc.setProductId(rs.getString("product_id") != null ? rs.getString("product_id") : "");
                rc.setProductName(rs.getString("product_name") != null ? rs.getString("product_name") : "");
                rc.setPrice(rs.getString("price") != null ? rs.getString("price") : "");
                rc.setQuantity(rs.getInt("quantity"));
                listCart.add(rc);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listCart;
    }

    private CartDTO getProductInCart(String customerID, String productId) {
        CartDTO rc = new CartDTO();
        String sql = "select * from cart where customer_id ='" + customerID + "' and product_id = '" + productId + "';";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                rc.setId(rs.getString("id") != null ? rs.getString("id") : "");
                rc.setProductId(rs.getString("product_id") != null ? rs.getString("product_id") : "");
                rc.setQuantity(rs.getInt("quantity"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rc;
    }

    public Boolean deleteItemCart(String cartID) {
        Boolean res = false;
        LocalDate date = LocalDate.now();
        String sql = "delete from cart where id = '" + cartID + "' ;";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return res;
    }

    @Override
    public Boolean transactionCheckOut(String customerID) {
        Connection conn = DBConnectionDTO.getConnection();
        LocalDate date = LocalDate.now();

        try {
            List<CartDTO> cartByCus = getCart(customerID);

            if (Objects.isNull(cartByCus) || cartByCus.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không có sản phẩm trong giỏ hàng!");
                return false;
            }
            Statement st = conn.createStatement();
            conn.setAutoCommit(false);
            //orderl
            String orderId = UUID.randomUUID().toString();

            // tạo order detail
            double total = 0;
            for (CartDTO lts : cartByCus) {
                total += lts.getQuantity() * Double.parseDouble(lts.getPrice());
                String sqlCreateNewOrderDetail = String.format("insert into order_detail(order_id, product_id, quantity,price) "
                        + "values('%s','%s','%s','%s');", orderId, lts.getProductId(), lts.getQuantity(), lts.getPrice());
                st.executeUpdate(sqlCreateNewOrderDetail);
            }
            // tạo mơi order
            String sqlCreateNewOrder = String.format("insert into orders(id,customer_id, status, total,created_at, updated_at) "
                    + "values('%s','%s','%s','%s','%s','%s');", orderId, customerID, OderStatusType.ON_PROCESS.name(), total, date, date);
            st.executeUpdate(sqlCreateNewOrder);
            // xóa cart

            String sql = "delete from cart where customer_id = '" + customerID + "' ;";
            st.executeUpdate(sql);

            conn.commit();
            return true;

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ev) {
                ev.printStackTrace();
            }
            e.printStackTrace();
        }

        return false;
    }

    public List<OrderDTO> getOrderByCus(String customerID) {
        List<OrderDTO> res = new ArrayList<>();
        List<OrderDTO> orders = getOrder(customerID);
        if (orders == null || orders.isEmpty()) {
            return res;
        }
        for (OrderDTO lts : orders) {
            OrderDTO rc = new OrderDTO();
            rc.setId(lts.getId());
            rc.setCustomerId(lts.getCustomerId());
            rc.setTotal(lts.getTotal());
            rc.setStatus(lts.getStatus());
            rc.setCreatedAt(lts.getCreatedAt());
            List<OrderDetailDTO> orderDetai = getOrderDetail(lts.getId());
            if (orderDetai == null || orderDetai.isEmpty()) {
                continue;
            }
            rc.setOrderDetailDTO(orderDetai);
            res.add(rc);
        }
        return res;
    }

    private List<OrderDTO> getOrder(String customerID) {
        List<OrderDTO> list = new ArrayList<>();
        String sql = "select * from orders where customer_id ='" + customerID + "' order by created_at desc;";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                OrderDTO rc = new OrderDTO();
                rc.setId(rs.getString("id") != null ? rs.getString("id") : "");
                rc.setCustomerId(rs.getString("customer_id") != null ? rs.getString("customer_id") : "");
                rc.setTotal(rs.getString("total") != null ? rs.getString("total") : "");
                rc.setStatus(rs.getString("status") != null ? rs.getString("status") : "");
                rc.setCreatedAt(rs.getString("created_at") != null ? rs.getString("created_at") : "");
                list.add(rc);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    private List<OrderDetailDTO> getOrderDetail(String orderId) {
        List<OrderDetailDTO> list = new ArrayList<>();
        String sql = "select t.id,t.product_id,p.product_name,t.quantity,t.price from order_detail t join products p on p.id = t.product_id"
                + " where t.order_id  ='" + orderId + "';";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                OrderDetailDTO rc = new OrderDetailDTO();
                rc.setId(rs.getString("id") != null ? rs.getString("id") : "");
                rc.setOrderId(orderId);
                rc.setProductId(rs.getString("product_id") != null ? rs.getString("product_id") : "");
                rc.setProductName(rs.getString("product_name") != null ? rs.getString("product_name") : "");
                rc.setPrice(rs.getString("price") != null ? rs.getString("price") : "");
                list.add(rc);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

}
