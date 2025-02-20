package org.example.backend_calisthenx.services;

import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.Athlete;
import org.example.backend_calisthenx.models.Goal;
import org.example.backend_calisthenx.repositories.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    public List<Goal> getGoalsByAthlete(Athlete athlete) {

        return goalRepository.findGoalsByAthlete(athlete).orElseThrow(()-> new ResourceNotFoundException("Goal not found"));
    }

    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public Goal insertSubGoal(Goal goal, List<Goal> subGoal) {
        goal.setSubGoals(subGoal);
        return goalRepository.save(goal);
    }

    public Goal validateGoal(Goal goal) {
        goal.setValidated(true);
        return goalRepository.save(goal);
    }

    public Goal updateGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public void deleteGoal(Goal goal) {
        goalRepository.delete(goal);
    }

}
