package gitoscope.controller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import gitoscope.domain.*;
import gitoscope.service.*;

@Controller
public class TreeBrowserController {

	private ProjectService projectService;
	private TreeBrowserService treeBrowserService;

	@RequestMapping(value="/project/{projectName}.git/tree/{treeId}")
	public ModelAndView show(
			@PathVariable String projectName,
			@PathVariable String treeId) {
		Project project = projectService.findProjectByName(projectName);
		List<BrowseableObject> objects = treeBrowserService.listObjects(project, treeId);

		ModelAndView modelAndView = new ModelAndView("tree.show");
		modelAndView.addObject("objList", objects);
		modelAndView.addObject("project", project);
		modelAndView.addObject("title", projectName + ": " + treeId);
		return modelAndView;
	}

	@RequestMapping(value="/project/{projectName}.git/blob/{blobId}")
	public ModelAndView blob(
			@PathVariable String projectName,
			@PathVariable String blobId) {
		Project project = projectService.findProjectByName(projectName);

		String blobText = treeBrowserService.getBlobAsText(project, blobId);
		ModelAndView modelAndView = new ModelAndView("blob.show");
		modelAndView.addObject("text", blobText);
		modelAndView.addObject("title", projectName + ": " + blobId);
		modelAndView.addObject("project", project);
		return modelAndView;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public void setTreeBrowserService(TreeBrowserService treeBrowserService) {
		this.treeBrowserService = treeBrowserService;
	}

}