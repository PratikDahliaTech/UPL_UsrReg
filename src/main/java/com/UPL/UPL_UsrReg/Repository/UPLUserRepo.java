package com.UPL.UPL_UsrReg.Repository;

import com.UPL.UPL_UsrReg.Model.UPLUsersDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UPLUserRepo extends CrudRepository<UPLUsersDetails,Integer>
{


    @Query(value = "select * from user_details ud where ud.mobile_no = :mobile_no and ud.password = :password",nativeQuery = true)
    UPLUsersDetails findUserLogin(@Param("mobile_no") String mobile_no, @Param("password") String password);

    @Query(value = "select * from user_details ud where ud.mobile_no = :mobile_no",nativeQuery = true)
    UPLUsersDetails findMobileNoPresents(@Param("mobile_no") String mobile_no);
}
