package com.hanul.app.command;

import org.springframework.ui.Model;

import com.hanul.app.dao.AnDao;

public class AnJoinCommand implements AnCommand{

	@Override
	public void execute(Model model) {
		
		//5. controller ���� �Ѱܹ��� model���� �� ����
		String id = (String) model.asMap().get("id");
		String passwd = (String)model.asMap().get("passwd");	
		String name = (String)model.asMap().get("name");
		String phonenumber = (String)model.asMap().get("phonenumber");
		String address = (String)model.asMap().get("address");
		
		//6. ������ ���� Dao�� �Ѱ��ش�
		AnDao adao = new AnDao(); 
		//9. Dao���� �Ѱ��� ���� ���� Ÿ������ �޴´�
		int state = adao.anJoin(id, passwd, name, phonenumber, address);
		
		//10. jsp���� ����ϱ� ���� �𵨿� �����Ѵ�.
		model.addAttribute("anJoin", String.valueOf(state));
	}	
	
	

}
