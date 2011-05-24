package gitoscope.controller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import gitoscope.domain.*;
import gitoscope.service.*;

@Controller
public class CommitController {
	
	private CommitService commitService;
	private ProjectService projectService;

	@RequestMapping(value="/project/{projectName}.git/commit/{commitId}", method=RequestMethod.GET)
	public ModelAndView show(
		@PathVariable String projectName,
		@PathVariable String commitId) {

		Project project = projectService.findProjectByName(projectName);
		Commit commit = commitService.findById(project, commitId);
		ModelAndView modelAndView = new ModelAndView("commit.show");
		modelAndView.addObject("project", project);
		modelAndView.addObject("title", projectName + ": " + commitId);
		modelAndView.addObject("commit", commit);
		modelAndView.addObject("parents", commit.getParents());
		return modelAndView;
	}

	public void setCommitService(CommitService commitService) {
		this.commitService = commitService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}
