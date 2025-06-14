package in.sp.main.service;

import java.util.List;

import in.sp.main.entities.MembershipPlan;

public interface MembershipPlanService
{
	MembershipPlan savePlan(MembershipPlan plan);
	List<MembershipPlan>getAllPlans();
	MembershipPlan getPlanById(Long id);
	void deletePlan(Long id);
	MembershipPlan getPlanByName(String planName);

}
