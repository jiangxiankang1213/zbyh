package com.zbyh.rlsb;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.*;

/**
 * @USER: Jiang-XK
 * @DESCRIPTION:
 * @DATE: 2021/1/13 16:05
 * @param:用于获取实名核身结果信息增强版
 */
public class GetDetectInfoEnhanced
{
    public static void main(String [] args) {
        try{

            Credential cred = new Credential("AKIDQoYHq8wkgLj2EAvb8F5dcLtA1h6RQb6e", "g6HBqFSOBiyyhBONC2rlPKBra2UrhJo7");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, "ap-guangzhou", clientProfile);

            GetDetectInfoEnhancedRequest req = new GetDetectInfoEnhancedRequest();
            req.setBizToken("E104028A-B8CD-429B-B0CF-4C88C09CCBDC");
            req.setRuleId("0");

            GetDetectInfoEnhancedResponse resp = client.GetDetectInfoEnhanced(req);

            System.out.println(GetDetectInfoEnhancedResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}
