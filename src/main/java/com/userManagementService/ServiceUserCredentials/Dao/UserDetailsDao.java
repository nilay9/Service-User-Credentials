package com.userManagementService.ServiceUserCredentials.Dao;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.userManagementService.ServiceUserCredentials.pojo.UserBasicDetails;





@Repository
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "userBasicDetails")
public interface UserDetailsDao extends CouchbasePagingAndSortingRepository<UserBasicDetails, String> {

	@Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and username = $1 and $2 within #{#n1ql.bucket} LIMIT 1")
	List<UserBasicDetails> existsByUsernameAndPassword(String username, String password);

	@Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and username = $1 LIMIT 1")
	UserBasicDetails findByUsername(String username);

//	@Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and username = $1 within #{#n1ql.bucket} LIMIT 1")
//	UserBasicDetails findByUsername(String username);

	
	
	

}
