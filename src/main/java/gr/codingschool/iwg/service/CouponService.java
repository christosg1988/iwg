package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Coupon;

public interface CouponService {
    Coupon getCouponIfAvailable(String code);
    Boolean useCoupon(String code);
}
