package com.zbyh.rlsb;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.*;;

/**
 * @USER: Jiang-XK
 * @DESCRIPTION:
 * @DATE: 2021/1/13 12:27
 * @param: 获取 BizToken，用于调用您配置的人脸核身验证的流程。
 */
public class DetectAuth
{
    public static void main(String [] args) {
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
            req.setRuleId("0");
            DetectAuthResponse resp = client.DetectAuth(req);
            JSONObject jsonObject = JSON.parseObject(DetectAuthResponse.toJsonString(resp));
            System.out.println(jsonObject.toJSONString());
            if(jsonObject.toJSONString() !=null&&!"".equals(jsonObject.toJSONString())) {
                String url = jsonObject.get("Url").toString();
                String requestId = jsonObject.get("RequestId").toString();
                String bizToken = jsonObject.get("BizToken").toString();
                System.out.println(url);
                System.out.println(requestId);
                System.out.println(bizToken);
            }
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}
