package com.example.safing.shop.DAO;

import android.util.Log;

import com.example.safing.async.AskParam;
import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonMethod;
import com.example.safing.async.CommonVal;
import com.example.safing.shop.VO.AddressVO;
import com.example.safing.shop.VO.CartVO;
import com.example.safing.shop.VO.Order_Detail_CntVO;
import com.example.safing.shop.VO.ProductVO;
import com.example.safing.shop.VO.Product_DetailVO;
import com.example.safing.shop.VO.Product_PackageVO;
import com.example.safing.shop.VO.Product_Package_DetailVO;
import com.example.safing.shop.VO.PurchaseHistoryVO;
import com.example.safing.shop.VO.ReviewVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO {
    private static final String TAG = "shop_dao";
    CommonAsk service ;
    InputStream in;
    Gson gson = new Gson();

    //패키지 리스트
    public ArrayList<Product_PackageVO> package_list(){
        service = new CommonAsk("package_list.sh");
        in = CommonMethod.excuteAsk(service);
        ArrayList<Product_PackageVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<Product_PackageVO> >(){}.getType());
    } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }
        return list;
    }

    //상품 리스트
    public ArrayList<ProductVO> product_list(String query){
        service = new CommonAsk("product_list.sh");
        service.params.add(new AskParam("search", query));
        in = CommonMethod.excuteAsk(service);
        ArrayList<ProductVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<ProductVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return list;
    }

    //패키지 상세정보
    public Product_Package_DetailVO package_detail(int package_num){
        service = new CommonAsk("package_detail.sh");
        service.params.add(new AskParam("package_num", package_num+""));
        in = CommonMethod.excuteAsk(service);
        Product_Package_DetailVO vo = new Product_Package_DetailVO();
        try{
            vo = gson.fromJson(new InputStreamReader(in), Product_Package_DetailVO.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return vo;
    }

    //상품 상세정보
    public Product_DetailVO product_detail(int product_num){
        service = new CommonAsk("product_detail.sh");
        service.params.add(new AskParam("product_num", product_num+""));
        in = CommonMethod.excuteAsk(service);
        Product_DetailVO vo = new Product_DetailVO();
        try{
            vo = gson.fromJson(new InputStreamReader(in), Product_DetailVO.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return vo;
    }

    //패키지 상품 상세정보 페이지
    public ArrayList<Product_DetailVO> product_details_page_pack(int num){
        service = new CommonAsk("product_details_page_pack.sh");
        service.params.add(new AskParam("num", num+""));
        in = CommonMethod.excuteAsk(service);
        ArrayList<Product_DetailVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<Product_DetailVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }
        return list;
    }

    //장바구니 담기 상품
    public int insert_cart_pro(Product_DetailVO vo){
        service = new CommonAsk("insert_cart_pro.sh");
        CartVO cart = new CartVO();
        cart.setMember_id(CommonVal.loginInfo.getMember_id());
        cart.setProduct_num(vo.getProduct_num());
        cart.setProduct_price(vo.getProduct_price()*vo.getOrder_count());
        cart.setOrder_count(vo.getOrder_count());
        service.params.add(new AskParam("vo", gson.toJson(cart)));

        in = CommonMethod.excuteAsk(service);
        int result = 0;
        try{
            result = gson.fromJson(new InputStreamReader(in), Integer.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return result;
    }

    //장바구니 담기 패키지
    public int insert_cart_pack(ArrayList<Product_DetailVO> list, int packge_num, int priceSum){
        CartVO cartvo = new CartVO();
        service = new CommonAsk("insert_cart_pack.sh");
        service.params.add(new AskParam("priceSum",priceSum+""));
        int i = 0;
        for (Product_DetailVO vo: list) {
            cartvo.setMember_id(CommonVal.loginInfo.getMember_id());
            cartvo.setProduct_num(vo.getProduct_num());
            cartvo.setPackage_num(packge_num);
            cartvo.setProduct_price(priceSum);
            cartvo.setOrder_count(vo.getOrder_count());
            service.params.add(new AskParam("list"+i, gson.toJson(cartvo)));
            i++;
        }

        in = CommonMethod.excuteAsk(service);
        int result = 0;
        try{
            result = gson.fromJson(new InputStreamReader(in), Integer.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return result;
    }

    //상품 리뷰리스트
    public ArrayList<ReviewVO> review_list_pro(int product_num){
        service = new CommonAsk("review_list_pro.sh");
        service.params.add(new AskParam("num", product_num+""));
        in = CommonMethod.excuteAsk(service);
        ArrayList<ReviewVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<ReviewVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return list;
    }

    //패키지 리뷰리스트
    public ArrayList<ReviewVO> review_list_pack(int pakcage_num){
        service = new CommonAsk("review_list_pack.sh");
        service.params.add(new AskParam("num", pakcage_num+""));
        in = CommonMethod.excuteAsk(service);
        ArrayList<ReviewVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<ReviewVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }
        return list;
    }

    //리뷰등록
    public int review_intsert(ReviewVO reviewVO){
        service = new CommonAsk("review_intsert.sh");
        service.params.add(new AskParam("vo", gson.toJson(reviewVO)));
        in = CommonMethod.excuteAsk(service);
        int result = 0;
        try{
            result = gson.fromJson(new InputStreamReader(in), Integer.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }
        return result;
    }

    //리뷰 좋아요 수정
    public void board_like_cnt_update(int review_num, int like_cnt){
        service = new CommonAsk("board_like_cnt_update.sh");
        service.params.add(new AskParam("review_num", review_num+""));
        service.params.add(new AskParam("like_cnt", like_cnt+""));
        in = CommonMethod.excuteAsk(service);
    }

    //장바구니 리스트
    public ArrayList<CartVO> cart_list(String member_id){
        service = new CommonAsk("cart_list.sh");
        service.params.add(new AskParam("member_id", member_id));
        in = CommonMethod.excuteAsk(service);
        ArrayList<CartVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<CartVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }
        return list;
    }

    //장바구니 삭제
    public void delete_cart(int cart_num){
        service = new CommonAsk("delete_cart.sh");
        service.params.add(new AskParam("cart_num", cart_num+""));
        in = CommonMethod.excuteAsk(service);
    }

    //구매내역 리스트
    public ArrayList<PurchaseHistoryVO> purchaseHistory_list(String member_id){
        service = new CommonAsk("purchaseHistory_list.sh");
        service.params.add(new AskParam("member_id", member_id));
        in = CommonMethod.excuteAsk(service);
        ArrayList<PurchaseHistoryVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<PurchaseHistoryVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }
        return list;
    }

    //구매내역 환불여부
    public void update_refund(PurchaseHistoryVO vo){
        service = new CommonAsk("update_refund.sh");
        service.params.add(new AskParam("vo", gson.toJson(vo)));
        in = CommonMethod.excuteAsk(service);
    }


    //기본주소 불러오기
    public AddressVO default_addrss(String member_id){
        service = new CommonAsk("default_addrss.sh");
        service.params.add(new AskParam("member_id", member_id));
        in = CommonMethod.excuteAsk(service);
        AddressVO vo = new AddressVO();
        try{
            vo = gson.fromJson(new InputStreamReader(in), AddressVO.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return vo;
    }

    //주소 리스트
    public ArrayList<AddressVO> addrss_list(String member_id){
        service = new CommonAsk("addrss_list.sh");
        service.params.add(new AskParam("member_id", member_id));
        in = CommonMethod.excuteAsk(service);
        ArrayList<AddressVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<AddressVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return list;
    }

    //기본 주소 변경
    public void update_address(int addr_num){
        service = new CommonAsk("update_address.sh");
        service.params.add(new AskParam("addr_num", addr_num+""));
        in = CommonMethod.excuteAsk(service);
    }

    //주소등록
    public int insert_address(AddressVO newVo){
        service = new CommonAsk("insert_address.sh");
        service.params.add(new AskParam("member_id", CommonVal.loginInfo.getMember_id()));
        service.params.add(new AskParam("vo", gson.toJson(newVo)));
        in = CommonMethod.excuteAsk(service);
        int addr_num = 0;
        try{
            addr_num = gson.fromJson(new InputStreamReader(in), Integer.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return addr_num;
    }

    //주소삭제
    public void delete_addr(int addr_num){
        service = new CommonAsk("delete_addr.sh");
        service.params.add(new AskParam("addr_num", addr_num+""));
        in = CommonMethod.excuteAsk(service);
    }

    //결제하기 장바구니
    public int insert_order_ing_cart(ArrayList<CartVO> cartList, AddressVO addressVo){
        service = new CommonAsk("insert_order_ing_cart.sh");
        service.params.add(new AskParam("address", gson.toJson(addressVo)));
        int i = 0;
        for (CartVO vo: cartList) {
            vo.setMember_id(CommonVal.loginInfo.getMember_id());
            service.params.add(new AskParam("list"+i, gson.toJson(vo)));
            i++;
        }
        in = CommonMethod.excuteAsk(service);
        int result = 0;
        try{
            result = gson.fromJson(new InputStreamReader(in), Integer.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }
        return result;
    }


    //결제하기 패키지
    public int insert_order_ing_pack(CartVO cartvo, ArrayList<Order_Detail_CntVO> packCntList, AddressVO addressVo){
        service = new CommonAsk("insert_order_ing_pack.sh");
        cartvo.setMember_id(CommonVal.loginInfo.getMember_id());
        service.params.add(new AskParam("vo", gson.toJson(cartvo)));
        service.params.add(new AskParam("address", gson.toJson(addressVo)));

        int i = 0;
        for (Order_Detail_CntVO vo: packCntList) {
            service.params.add(new AskParam("list"+i, gson.toJson(vo)));
            i++;
        }

        in = CommonMethod.excuteAsk(service);
        int result = 0;
        try{
            result = gson.fromJson(new InputStreamReader(in), Integer.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }
        return result;
    }

    //결제하기 상품
    public int insert_order_ing_pro(CartVO vo, AddressVO addressVo){
        service = new CommonAsk("insert_order_ing_pro.sh");
        vo.setMember_id(CommonVal.loginInfo.getMember_id());
        service.params.add(new AskParam("vo", gson.toJson(vo)));
        service.params.add(new AskParam("address", gson.toJson(addressVo)));
        in = CommonMethod.excuteAsk(service);
        int result = 0;
        try{
            result = gson.fromJson(new InputStreamReader(in), Integer.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }
        return result;
    }
}
