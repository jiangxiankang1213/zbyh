package com.zbyh.rlsb.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.DetectAuthRequest;
import com.tencentcloudapi.faceid.v20180301.models.DetectAuthResponse;

/**
 * @USER: Jiang-XK
 * @DESCRIPTION:
 * @DATE: 2021/1/13 15:53
 * @param: 获取 BizToken，用于调用您配置的人脸核身验证的流程。
 */
public class DetectAuthService {
    /**
     *
     * @param ruleId
     * @param redirectUrl  认证结束后重定向的回调链接地址 RedirectUrl
     * @return 返回json字符串中包含核验流程唯一密钥（BizToken）和用于发起核身流程的 URL
     */
    public String DetectAuth(String ruleId,String redirectUrl){
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户 secretId、secretKey
            Credential cred = new Credential("AKIDQoYHq8wkgLj2EAvb8F5dcLtA1h6RQb6e", "g6HBqFSOBiyyhBONC2rlPKBra2UrhJo7");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            FaceidClient client = new FaceidClient(cred, "ap-guangzhou", clientProfile);
            DetectAuthRequest req = new DetectAuthRequest();
            //用于细分客户使用场景，申请开通服务后，可以在腾讯云慧眼人脸核身控制台（https://console.cloud.tencent.com/faceid） 自助接入里面创建
            req.setRuleId(ruleId);
            req.setRedirectUrl(redirectUrl);
            DetectAuthResponse resp = client.DetectAuth(req);
            System.out.println(DetectAuthResponse.toJsonString(resp));
            JSONObject jsonObject = JSON.parseObject(DetectAuthResponse.toJsonString(resp));
            if(jsonObject.toJSONString() !=null&&!"".equals(jsonObject.toJSONString())) {
                String url = jsonObject.get("Url").toString();
                String requestId = jsonObject.get("RequestId").toString();
                String bizToken = jsonObject.get("BizToken").toString();
                System.out.println(url);
                System.out.println(requestId);
                System.out.println(bizToken);
                return jsonObject.toJSONString();
            }
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return "fail";
    }

}
