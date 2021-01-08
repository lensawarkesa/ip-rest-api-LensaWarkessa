package com.lensa.ipaddressmanagement.repoistory;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.lensa.ipaddressmanagement.entity.IpAdress;
@Repository
public class IpAdressRepo {

	private HashMap<String,String> IpAdresses= new HashMap<>();

	public HashMap<String, String> getIpAdresses() {
		return IpAdresses;
	}

	public void setIpAdresses(HashMap<String, String> ipAdresses) {
		IpAdresses = ipAdresses;
	}

	
	
	
}
