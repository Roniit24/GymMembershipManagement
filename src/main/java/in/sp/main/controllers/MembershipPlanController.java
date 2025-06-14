package in.sp.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.sp.main.entities.MembershipPlan;
import in.sp.main.service.MembershipPlanService;

@Controller
public class MembershipPlanController {

    @Autowired
    private MembershipPlanService service;

    // Show all plans (overview page)
    @GetMapping("/plans")
    public String listPlans(Model model) {
        List<MembershipPlan> plans = service.getAllPlans();
        model.addAttribute("plans", plans);
        return "plans"; // This is the overview page
    }

    // Show plan selection form (dropdown page)
    @GetMapping("/addPlan")
    public String showAddForm(Model model) {
        model.addAttribute("plans", service.getAllPlans());
        return "addPlan"; // This is the plan selection form
    }


    // Process selected plan, fetch price & duration, and go to payment
    @PostMapping("/addPlan")
    public String addPlan(@RequestParam String planName, @RequestParam Double price, @RequestParam String duration, Model model) {
        System.out.println("Adding new plan: " + planName); // Debugging

        // Create a new MembershipPlan instance
        MembershipPlan newPlan = new MembershipPlan();
        newPlan.setPlanName(planName);
        newPlan.setPrice(price);
        newPlan.setDuration(duration);

        // Save the new plan to the database
        MembershipPlan savedPlan = service.savePlan(newPlan);  // Ensure the save method is working

        if (savedPlan != null) {
            model.addAttribute("planName", savedPlan.getPlanName());
            model.addAttribute("price", savedPlan.getPrice());
            model.addAttribute("duration", savedPlan.getDuration());
        } else {
            model.addAttribute("error", "Error saving plan.");
        }

        return "payment"; // Redirect to the payment page
    }




}
