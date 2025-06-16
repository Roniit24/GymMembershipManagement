package in.sp.main.controllers;

import java.util.List;

import jakarta.servlet.http.HttpSession;
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

    // ✅ Show all plans (secured)
    @GetMapping("/plans")
    public String listPlans(Model model, HttpSession session) {
        if (session.getAttribute("email") == null) {
            return "redirect:/"; // redirect if not logged in
        }

        List<MembershipPlan> plans = service.getAllPlans();
        model.addAttribute("plans", plans);
        return "plans";
    }

    // ✅ Show plan selection form (secured)
    @GetMapping("/addPlan")
    public String showAddForm(Model model, HttpSession session) {
        if (session.getAttribute("email") == null) {
            return "redirect:/";
        }

        model.addAttribute("plans", service.getAllPlans());
        return "addPlan";
    }

    // ✅ Process selected plan, fetch price & duration, and go to payment (secured)
    @PostMapping("/addPlan")
    public String addPlan(@RequestParam String planName,
                          @RequestParam Double price,
                          @RequestParam String duration,
                          Model model,
                          HttpSession session) {
        if (session.getAttribute("email") == null) {
            return "redirect:/";
        }

        System.out.println("Adding new plan: " + planName);

        MembershipPlan newPlan = new MembershipPlan();
        newPlan.setPlanName(planName);
        newPlan.setPrice(price);
        newPlan.setDuration(duration);

        MembershipPlan savedPlan = service.savePlan(newPlan);

        if (savedPlan != null) {
            model.addAttribute("planName", savedPlan.getPlanName());
            model.addAttribute("price", savedPlan.getPrice());
            model.addAttribute("duration", savedPlan.getDuration());
        } else {
            model.addAttribute("error", "Error saving plan.");
        }

        return "payment";
    }
}
