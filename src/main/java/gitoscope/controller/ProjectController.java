package gitoscope.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import gitoscope.domain.*;
import gitoscope.service.ProjectService;

@Controller
public class ProjectController {

    @RequestMapping(value = "/project/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Project> projectList = projectService.listProjects();
        model.addAttribute(projectList);
        return "project.list";
    }

    @RequestMapping(value = "/project/{projectName}.git", method = RequestMethod.GET)
    public String show(@PathVariable String projectName, Model model) {

        Project project = projectService.findProjectByName(projectName);
        List<Commit> commits = projectService.listCommits(project, null);

        model.addAttribute(project);
        model.addAttribute("title", project.getName() + ".git");
        model.addAttribute("commits", commits);
        return "project.show";
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    private ProjectService projectService;
}
