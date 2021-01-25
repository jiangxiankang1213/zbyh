package com.zbyh.rlsb;

import com.zbyh.rlsb.service.DetectAuthService;
import com.zbyh.rlsb.service.GetDetectInfoEnhancedService;

/**
 * @USER: Jiang-XK
 * @DESCRIPTION:
 * @DATE: 2021/1/13 15:58
 * @param:
 */
public class test {
    public static void main(String[] args) {
        DetectAuthService a=new DetectAuthService();
        //String b=a.DetectAuth();
        //System.out.println(b);
        GetDetectInfoEnhancedService c =new GetDetectInfoEnhancedService();
        String d=c.GetDetectInfoEnhanced("E104028A-B8CD-429B-B0CF-4C88C09CCBDC");
        System.out.println(d);
    }
}
