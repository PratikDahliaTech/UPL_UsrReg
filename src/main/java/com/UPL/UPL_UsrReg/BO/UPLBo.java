package com.UPL.UPL_UsrReg.BO;

import com.UPL.UPL_UsrReg.Model.UPLUsersDetails;
import com.UPL.UPL_UsrReg.Repository.UPLUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UPLBo {
    @Autowired
    PasswordValidation passCheck;

    @Autowired
    UPLUserRepo userRepo;

    public UPLUsersDetails EncryptPassword(UPLUsersDetails userDtl){
        String encPass = passCheck.encrypt(userDtl.getPassword());
        userDtl.setPassword(encPass);
        return userDtl;
    }

    public String EncryptPassword(String userPass) {
        String encPass = passCheck.encrypt(userPass);
        return encPass;
    }

    public boolean MobileRegisterationCheck(String userMobile){
        UPLUsersDetails userDtl = userRepo.findMobileNoPresents(userMobile);
        if(userDtl == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

