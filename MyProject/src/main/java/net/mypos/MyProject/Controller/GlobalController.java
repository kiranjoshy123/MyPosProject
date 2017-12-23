package net.mypos.MyProject.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.mypos.MyProject.Model.UserModel;
import net.mypos.MyProjectBackend.dao.UserinfoDAO;
import net.mypos.MyProjectBackend.dto.Userinfo;

@ControllerAdvice
public class GlobalController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserinfoDAO userinfoDAO;
	
	private UserModel userModel = null;
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	

	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		
		if(session.getAttribute("userModel") == null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Userinfo user = userinfoDAO.getbyEmail(auth.getName());
			if(user != null)
			{
				// Create a new usermodel object to pass the user details.
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());
				session.setAttribute("userModel", userModel);
				
				return userModel;
			}
		}
		
		return (UserModel)session.getAttribute("userModel");
	}
}
