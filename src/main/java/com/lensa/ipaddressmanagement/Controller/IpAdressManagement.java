package com.lensa.ipaddressmanagement.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.net.util.SubnetUtils;

import java.util.HashMap;

import org.apache.commons.collections4.*;
import com.lensa.ipaddressmanagement.entity.IpAdress;
import com.lensa.ipaddressmanagement.repoistory.IpAdressRepo;

@RestController
//Rest API

@RequestMapping()
public class IpAdressManagement {
	@Autowired
	IpAdressRepo ipRepo;
	
	@RequestMapping("/")
	public String home( ){
		return "Welocme";
	}
@RequestMapping(value ="/registerIp", method=RequestMethod.POST)
public String register(@ModelAttribute("ipblock") String ipBlock) {
	if(!ipBlock.isEmpty()) {
		System.out.println(ipBlock);
		SubnetUtils utils = new SubnetUtils(ipBlock);
		
        String [] Ipadresses = utils.getInfo().getAllAddresses();
        for(String adress:Ipadresses) {
       	 ipRepo.getIpAdresses().put(adress, "available");
       	 
        }

return "Ip registration done";
		
	}
	else return "Please enter valid block";
	
}

@GetMapping("/list")
public HashMap<String,String> getIpAdresses( ){
		
	return ipRepo.getIpAdresses();
}

@PostMapping("/acquire")
public String aquireIp(@ModelAttribute("ipAdress") String ipAdress) {
	
	ipRepo.getIpAdresses().put(ipAdress,"aquired");
	return "ipAdress " + ipAdress +" status Aquired";

}
@PostMapping("/available")
public String releaseIp(@ModelAttribute("ipAdress") String ipAdress) {
	
	ipRepo.getIpAdresses().put(ipAdress.trim(),"available");
	return "ipAdress " + ipAdress +" status available";

}

}