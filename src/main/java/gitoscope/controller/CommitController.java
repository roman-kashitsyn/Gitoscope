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

	@RequestMapping(value="/project/{projectName}.git/commit/search.html")
	public ModelAndView search(
							   @PathVariable String projectName,
							   @RequestParam(value="max", required=false) Integer max,
							   @RequestParam(value="author", required=false) String author,
							   @RequestParam(value="committer", required=false) String committer,
							   @RequestParam(value="message", required=false) String message,
							   @RequestParam(value="start", required=false) String start) {
		Project project = projectService.findProjectByName(projectName);
		int maxResults = (max != null) ? max.intValue() : 0;
		Map<String, String> filterMap = new TreeMap<String, String>();
		if (message != null) filterMap.put("message", message);
		if (start != null) filterMap.put("start", start);
		if (author != null) filterMap.put("author", author);
		if (committer != null) filterMap.put("committer", committer);

		List<Commit> commits = commitService.searchCommits(project, maxResults, filterMap);

		ModelAndView modelAndView = new ModelAndView("project.show");
		modelAndView.addObject("project", project);
		modelAndView.addObject("title", project.getName());
		modelAndView.addObject("commits", commits);
		return modelAndView;
	}

	public void setCommitService(CommitService commitService) {
		this.commitService = commitService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}
