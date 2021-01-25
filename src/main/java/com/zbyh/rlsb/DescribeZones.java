package com.zbyh.rlsb;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
// 导入对应产品模块的 client
import com.tencentcloudapi.cvm.v20170312.CvmClient;
// 导入要请求接口对应的 request response 类
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesResponse;
//导入可选配置类
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.faceid.v20180301.models.DetectAuthResponse;

/**
 * @USER: Jiang-XK
 * @DESCRIPTION:
 * @DATE: 2021/1/13 14:49
 * @param: 获取金融区域
 */

public class DescribeZones{
    public static void main(String [] args) {
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户 secretId、secretKey
            Credential cred = new Credential("AKIDQoYHq8wkgLj2EAvb8F5dcLtA1h6RQb6e", "g6HBqFSOBiyyhBONC2rlPKBra2UrhJo7");

            // 实例化要请求产品（以 cvm 为例）的 client 对象
            CvmClient client = new CvmClient(cred, "ap-guangzhou");

            // 实例化一个请求对象
            DescribeZonesRequest req = new DescribeZonesRequest();

            // 通过 client 对象调用想要访问的接口，需要传入请求对象
            DescribeZonesResponse resp = client.DescribeZones(req);
            JSONObject jsonObject = JSON.parseObject(DescribeZonesResponse.toJsonString(resp));
            if(jsonObject.toJSONString() !=null&&!"".equals(jsonObject.toJSONString())) {
                String url = jsonObject.get("TotalCount").toString();
                String requestId = jsonObject.get("RequestId").toString();
                String bizToken = jsonObject.get("BizToken").toString();
                System.out.println(url);
                System.out.println(requestId);
                System.out.println(bizToken);
            }

            // 输出 json 格式的字符串回包
            System.out.println(DescribeZonesRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}