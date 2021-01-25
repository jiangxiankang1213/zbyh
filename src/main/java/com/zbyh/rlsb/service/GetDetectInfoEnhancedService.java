package com.zbyh.rlsb.service;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.GetDetectInfoEnhancedRequest;
import com.tencentcloudapi.faceid.v20180301.models.GetDetectInfoEnhancedResponse;

/**
 * @USER: Jiang-XK
 * @DESCRIPTION:
 * @DATE: 2021/1/13 16:07
 * @param: 根据DetectAuthService中返回的BizToken，获取用户上传的图片，视屏截帧等相关信息
 */
public class GetDetectInfoEnhancedService {
    /**
     *
     * @param bizToken 人脸核身流程中产生的标识
     *        ruleId    用户注册产生
     *        infoType  是否返回所有信息
     *        bestFramesCount 截屏数
     *
     * @return {
     * 	"Text": {
     * 		    "IdCard": "",
     * 		    "Name": "",
     * 		    "OcrName": "",
     * 		    "OcrIdCard": "",
     * 		    "Extra": "",
     * 		    "LivenessDetail": [],
     * 		    "Mobile": ""
     *          },
     * 	"IdCardData": {},
     * 	"BestFrame": {},
     * 	"VideoData": {},
     * 	"RequestId": "0b6de75c-3e6f-4ec0-86e2-f941ab7644e9"
     * }
     */
    public String GetDetectInfoEnhanced(String ruleId,String bizToken,String infoType,Long bestFramesCount){
        try{

            Credential cred = new Credential("AKIDQoYHq8wkgLj2EAvb8F5dcLtA1h6RQb6e", "g6HBqFSOBiyyhBONC2rlPKBra2UrhJo7");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            FaceidClient client = new FaceidClient(cred, "ap-guangzhou", clientProfile);
            GetDetectInfoEnhancedRequest req = new GetDetectInfoEnhancedRequest();
            //req.setBizToken("E104028A-B8CD-429B-B0CF-4C88C09CCBDC");
            //实名核身鉴权返回，有效期7200s
            req.setBizToken(bizToken);
            //申请开通服务后产生,测试环境回先默认给0
            req.setRuleId(ruleId);
            //指定拉取的结果信息，取值（0：全部；1：文本类；2：身份证信息；3：视频最佳截图信息）。默认值为0
            req.setInfoType(infoType);
            //从活体视频中截取一定张数的最佳帧（仅部分服务支持，若需使用请与慧眼小助手沟通）。默认为0，最大为10，超出10的最多只给10张。（InfoType需要包含3）
            req.setBestFramesCount(bestFramesCount);
            GetDetectInfoEnhancedResponse resp = client.GetDetectInfoEnhanced(req);
            System.out.println(GetDetectInfoEnhancedResponse.toJsonString(resp));
            return GetDetectInfoEnhancedResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return "fail";
    }
}
