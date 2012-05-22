package gitoscope.controller;

import gitoscope.domain.Commit;
import gitoscope.domain.Project;
import gitoscope.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProjectController {

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public ModelAndView list() {
        List<Project> projectList = projectService.listProjects();
        return new ModelAndView("project.list", "projectList", projectList);
    }

    @RequestMapping(value = "/projects/{projectName}.git", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String projectName) {

        Project project = projectService.findProjectByName(projectName);
        List<Commit> commits = projectService.listCommits(project, null);

        ModelAndView modelAndView = new ModelAndView("project.show");
        modelAndView.addObject("project", project);
        modelAndView.addObject("title", project.getName() + ".git");
        modelAndView.addObject("commits", commits);
        return modelAndView;
    }

    @SuppressWarnings("unused")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    private ProjectService projectService;
}
