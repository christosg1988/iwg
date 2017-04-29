package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Coupon;
import gr.codingschool.iwg.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Coupon getCouponIfAvailable(String code) {
        Coupon coupon = couponRepository.findByCode(code);
        if(!(coupon == null) && !coupon.getIsUsed())
            return coupon;
        else
            return null;
    }

    @Override
    public Boolean useCoupon(String code) {
        Coupon coupon = couponRepository.findByCode(code);
        if(!coupon.getIsUsed()) {
            coupon.setIsUsed(true);
            couponRepository.save(coupon);
            return true;
        }
        else
            return false;
    }
}
