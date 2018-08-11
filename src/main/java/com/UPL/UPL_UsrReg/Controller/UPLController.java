package com.UPL.UPL_UsrReg.Controller;

import com.UPL.UPL_UsrReg.BO.UPLBo;
import com.UPL.UPL_UsrReg.Model.*;
import com.UPL.UPL_UsrReg.Repository.UPLUserLogRepo;
import com.UPL.UPL_UsrReg.Repository.UPLUserRepo;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/UPL")
public class UPLController {

    @Autowired
    UPLUserRepo userRepo;
    @Autowired
    UPLUserLogRepo userLogRepo;
    @Autowired
    UPLBo uplBo;

    @PostMapping("/saveUserDetails")
    public JSONObject saveUserDetail(@RequestBody UPLUsersDetails userDetails)
    {
        UPLUsersDetails modUserDetail = uplBo.EncryptPassword(userDetails);
        String errorMsg;
        System.out.println("modUserDetail :"+modUserDetail.equals(null));
        System.out.println("modUserDetail :"+modUserDetail.toString());
        boolean checkMobPresnt = uplBo.MobileRegisterationCheck(userDetails.getMobile_no());
        JSONObject message = new JSONObject();
        if(checkMobPresnt == true)
        {
            UPLUsersDetails usrDtl = userRepo.save(modUserDetail);
            errorMsg = "User successfully inserted...";
            message.put("message",errorMsg);
            message.put("IsSubmitted","True");
            return message;
        }
        else{
            errorMsg = "Mobile No already registered...";
            message.put("message",errorMsg);
            message.put("IsSubmitted","false");
            return message;
        }
    }

    @PostMapping("/checkUserLogin")
    public JSONObject UPLUserLogin(@RequestBody UPLLogin loginParam){
        String encPass = uplBo.EncryptPassword(loginParam.getPassword());
        UPLUsersDetails usrDtl = userRepo.findUserLogin(loginParam.getMobile_no(),encPass);
        JSONObject response = new JSONObject();
        if(usrDtl != null){
            response.put("IsExisted","True");
            response.put("UserId",usrDtl.getUser_id());
        }
        else{
            response.put("IsExisted","false");
            response.put("UserId","null");
        }
        return response;
    }

    @RequestMapping(value = "/saveUserLogDetails", produces = "application/json")
    public JSONObject saveUserLogDetail(@RequestBody UPLUsersLogDetails userLogDetails)throws Exception{

        JSONObject responseJson = new JSONObject();
        try{
            String imgBase64 = userLogDetails.getCaptured_img_base64();
            //not need to be handle
            String modImgBase64 = imgBase64.split(",")[1];
            byte[] imageByteArray = decodeImage(modImgBase64);
            String imgName = userLogDetails.getDate_time().toString().replace(":","_");

            File resourcesDirectory = new File("/static/StoredImage/");
            String filePath = resourcesDirectory.getAbsolutePath();
            System.out.println("filePath :" + filePath);
            //String folder = getClass().getResource("/StoredImages/");
            //getClass().getResource("/StoredImages/")
            String imgPath = filePath+imgName+".jpeg";
            FileOutputStream imageOutFile = new FileOutputStream(imgPath);
            imageOutFile.write(imageByteArray);
            imageOutFile.close();
            userLogDetails.setCaptured_img_base64(null);
            userLogDetails.setCaptured_img(imgPath);
            UPLUsersLogDetails insertedUsrLog = userLogRepo.save(userLogDetails);
            System.out.println("insertedUsrLog"+insertedUsrLog);
            if(insertedUsrLog != null){
                responseJson.put("log_id",insertedUsrLog.getLog_id());
                responseJson.put("captured_img",insertedUsrLog.getCaptured_img());
                responseJson.put("botanical_img_name",insertedUsrLog.getBotanical_img_name());
                responseJson.put("regional_name",insertedUsrLog.getRegional_name());
                responseJson.put("product",insertedUsrLog.getProduct());
                responseJson.put("dosage",insertedUsrLog.getDosage());
                responseJson.put("distribution",insertedUsrLog.getDistribution());
                responseJson.put("coupen",insertedUsrLog.getCoupen());
                responseJson.put("has_coupen",insertedUsrLog.isHas_coupen());
                responseJson.put("date_time",insertedUsrLog.getDate_time());
                responseJson.put("latitude",insertedUsrLog.getLatitude());
                responseJson.put("longitude",insertedUsrLog.getLongitude());
                responseJson.put("user_id",insertedUsrLog.getUser_id());
                responseJson.put("isInserted","true");
            }
            else{
                responseJson.put("log_id",null);
                responseJson.put("captured_img",null);
                responseJson.put("botanical_img_name",null);
                responseJson.put("regional_name",null);
                responseJson.put("product",null);
                responseJson.put("dosage",null);
                responseJson.put("distribution",null);
                responseJson.put("coupen",null);
                responseJson.put("has_coupen",null);
                responseJson.put("date_time",null);
                responseJson.put("latitude",null);
                responseJson.put("longitude",null);
                responseJson.put("user_id",null);
                responseJson.put("isInserted","false");
            }
        }
        catch (Exception e){
            System.out.println("Exception --> "+e);
        }
    return responseJson;
    }

    public static byte[] decodeImage(String imageDataString) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(imageDataString);
    }

    @RequestMapping(value="/getHistoryByLogId")
    public JSONObject getHistoryByLogId(@RequestBody UPLLogId usrLogId)
    {
        UPLUsersLogDetails usrLogDetail = userLogRepo.findHistoryByLogId(usrLogId.getLog_id());
        JSONObject responseJson = new JSONObject();
        if(usrLogDetail != null)
        {
            responseJson.put("log_id",usrLogDetail.getLog_id());
            responseJson.put("captured_img",usrLogDetail.getCaptured_img());
            responseJson.put("botanical_img_name",usrLogDetail.getBotanical_img_name());
            responseJson.put("regional_name",usrLogDetail.getRegional_name());
            responseJson.put("product",usrLogDetail.getProduct());
            responseJson.put("dosage",usrLogDetail.getDosage());
            responseJson.put("distribution",usrLogDetail.getDistribution());
            responseJson.put("coupen",usrLogDetail.getCoupen());
            responseJson.put("has_coupen",usrLogDetail.isHas_coupen());
            responseJson.put("date_time",usrLogDetail.getDate_time());
            responseJson.put("latitude",usrLogDetail.getLatitude());
            responseJson.put("longitude",usrLogDetail.getLongitude());
            responseJson.put("user_id",usrLogDetail.getUser_id());
            responseJson.put("logFetched",true);
        }
        else{
            responseJson.put("log_id",null);
            responseJson.put("captured_img",null);
            responseJson.put("botanical_img_name",null);
            responseJson.put("regional_name",null);
            responseJson.put("product",null);
            responseJson.put("dosage",null);
            responseJson.put("distribution",null);
            responseJson.put("coupen",null);
            responseJson.put("has_coupen",null);
            responseJson.put("date_time",null);
            responseJson.put("latitude",null);
            responseJson.put("longitude",null);
            responseJson.put("user_id",null);
            responseJson.put("logFetched",false);
        }
        return responseJson;
    }

    @RequestMapping(value="/getHistoryByUserId")
    public JSONObject getHistoryByUserId(@RequestBody UPLUserId usrId)
    {

        List<UPLUsersLogDetails> usrLogDetail = (List<UPLUsersLogDetails>) userLogRepo.findHistoryByUserId(usrId.getUser_id());
        JSONObject responseJson = new JSONObject();
        JSONArray respJsonArr = new JSONArray();
        for(UPLUsersLogDetails usrDetail : usrLogDetail)
        {
            JSONObject jsonObject = new JSONObject();
            if(usrDetail != null)
            {
                jsonObject.put("log_id",usrDetail.getLog_id());
                jsonObject.put("captured_img",usrDetail.getCaptured_img());
                jsonObject.put("botanical_img_name",usrDetail.getBotanical_img_name());
                jsonObject.put("regional_name",usrDetail.getRegional_name());
                jsonObject.put("product",usrDetail.getProduct());
                jsonObject.put("dosage",usrDetail.getDosage());
                jsonObject.put("distribution",usrDetail.getDistribution());
                jsonObject.put("coupen",usrDetail.getCoupen());
                jsonObject.put("has_coupen",usrDetail.isHas_coupen());
                jsonObject.put("date_time",usrDetail.getDate_time());
                jsonObject.put("latitude",usrDetail.getLatitude());
                jsonObject.put("longitude",usrDetail.getLongitude());
                jsonObject.put("user_id",usrDetail.getUser_id());
                jsonObject.put("logFetched",true);
            }
            else{
                jsonObject.put("log_id",null);
                jsonObject.put("captured_img",null);
                jsonObject.put("botanical_img_name",null);
                jsonObject.put("regional_name",null);
                jsonObject.put("product",null);
                jsonObject.put("dosage",null);
                jsonObject.put("distribution",null);
                jsonObject.put("coupen",null);
                jsonObject.put("has_coupen",null);
                jsonObject.put("date_time",null);
                jsonObject.put("latitude",null);
                jsonObject.put("longitude",null);
                jsonObject.put("user_id",null);
                jsonObject.put("logFetched",false);
            }
            respJsonArr.add(jsonObject);
        }
        responseJson.put("UsersInfo",respJsonArr);
        return responseJson;
    }

}
