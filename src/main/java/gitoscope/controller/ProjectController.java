package gitoscope.controller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import gitoscope.domain.*;
import gitoscope.service.ProjectService;

@Controller
public class ProjectController {

	@RequestMapping(value="/project/list", method=RequestMethod.GET)
	public ModelAndView list() {
		List<Project> projectList = projectService.listProjects();
		return new ModelAndView("project.list", "projectList", projectList);
	}

	@RequestMapping(value="/project/{projectName}.git", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable String projectName) {

		Project project = projectService.findProjectByName(projectName);
		List<Commit> commits = projectService.listCommits(project, null);

		ModelAndView modelAndView = new ModelAndView("project.show");
		modelAndView.addObject("project", project);
		modelAndView.addObject("title", project.getName() + ".git");
		modelAndView.addObject("commits", commits);
		return modelAndView;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	private ProjectService projectService;
}
