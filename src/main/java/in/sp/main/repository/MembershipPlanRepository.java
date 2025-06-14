package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.MembershipPlan;

public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, Long>
{
	MembershipPlan findByPlanName(String planName);

}
