package com.UPL.UPL_UsrReg.Repository;

import com.UPL.UPL_UsrReg.Model.UPLUsersDetails;
import com.UPL.UPL_UsrReg.Model.UPLUsersLogDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UPLUserLogRepo extends CrudRepository<UPLUsersLogDetails,Integer> {

    @Query(value = "Select * from user_log_details as upl where upl.log_id = :log_id",nativeQuery = true)
    UPLUsersLogDetails findHistoryByLogId(@Param("log_id") int log_id);

    @Query(value = "Select * from user_log_details as upl where upl.user_id = :user_id",nativeQuery = true)
    List<UPLUsersLogDetails> findHistoryByUserId(@Param("user_id") int user_id);

}
