package com.journaldev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.journaldev.spring.model.Appointment;
import com.journaldev.spring.service.AppointmentService;
import com.journaldev.spring.service.OperationService;
import com.journaldev.spring.service.StaffService;
import com.journaldev.spring.service.UserService;

@Controller
public class UserAppointmentController {

	@Autowired(required=true)
	@Qualifier(value="appointmentService")
	private AppointmentService appointmentService;
	
	@Autowired(required=true)
	@Qualifier(value="staffService")
	private StaffService staffService;
	
	@Autowired(required=true)
	@Qualifier(value="operationService")
	private OperationService operationService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	private UserService userService;
	
	public void setAppointmentService(AppointmentService us, StaffService staffService, OperationService operationService, UserService userService){
		this.appointmentService = us;
		this.staffService = staffService;
		this.operationService = operationService;
		this.userService = userService;
	}
	
	@RequestMapping(value = "/user/appointments", method = RequestMethod.GET)
	public String listAppointments(Model model, @RequestParam(value = "history", required = false, defaultValue = "false") boolean history) {
		model.addAttribute("appointment", new Appointment());
		model.addAttribute("listAppointments", this.appointmentService.listAppointmentsForUser(1, history));
		model.addAttribute("history", history);
		return "userappointment";
	}

	@RequestMapping(value = "/user/appointment/create", method = RequestMethod.GET)
	public String createAppointment(Model model) {
		model.addAttribute("appointment", new Appointment());
		model.addAttribute("listStaff", this.staffService.listStaff());
		model.addAttribute("listOperations", this.operationService.listOperations());
		return "user/new-appointment";
	}
	//new-appointment
	
	//For add and update appointment both
	@RequestMapping(value= "/user/appointment/add", method = RequestMethod.POST)
	public String addAppointment(@ModelAttribute("appointment") Appointment u){
		u.setUser(this.userService.getUserById(1));
		this.appointmentService.addAppointment(u);
		
		return "redirect:/user/appointments";
	}   
}
