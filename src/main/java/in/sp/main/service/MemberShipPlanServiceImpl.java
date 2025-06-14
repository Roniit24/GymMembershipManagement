package in.sp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.MembershipPlan;
import in.sp.main.repository.MembershipPlanRepository;

@Service
public class MemberShipPlanServiceImpl implements MembershipPlanService{

	@Autowired
	private MembershipPlanRepository repo;

	@Override
	 public MembershipPlan savePlan(MembershipPlan plan) {
        return repo.save(plan);  // Ensure the plan is saved to the database
    }


	@Override
	public List<MembershipPlan> getAllPlans() {

		return repo.findAll();
	}

	@Override
	public MembershipPlan getPlanById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void deletePlan(Long id) {
		repo.deleteById(id);

	}

	@Override
	public MembershipPlan getPlanByName(String planName) {
	    // Ensure the planName is trimmed to avoid issues with spaces
	    return repo.findByPlanName(planName.trim());
	}


}
