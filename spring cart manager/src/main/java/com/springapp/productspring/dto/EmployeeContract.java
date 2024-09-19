package com.springapp.productspring.dto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeContract<Tmodel, Tkey> {
	
	public List<Tmodel> getRecords() throws ClassNotFoundException, SQLException, Exception;
	
	Tmodel getRecord(Tkey id)throws ClassNotFoundException, SQLException, Exception;
	
	Integer addRecord(Tmodel modelObject)throws ClassNotFoundException, SQLException, Exception;
	
	Integer updateRecords(Tkey id ,Tmodel modelObject)throws ClassNotFoundException, SQLException, Exception;
	
	Integer deleteRecord(Tkey id)throws ClassNotFoundException, SQLException, Exception;
	

}
