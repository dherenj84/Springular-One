package com.mycomp.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycomp.dashboard.utils.EmailService;
import com.mycomp.dashboard.utils.Mail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class EmailServiceTest {

	@Autowired
	EmailService emailService;

	@Test
	public void testEmail() {
		Mail mail = new Mail();
		mail.setMailFrom("");
		mail.setMailTo("");
		mail.setMailCc("");
		mail.setMailSubject("");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("firstName", "Dheren");
		model.put("lastName", "Jain");
		model.put("location", "US");
		model.put("signature", "www.mycomp.com");
		mail.setModel(model);
		emailService.sendMail(mail);
	}

}
