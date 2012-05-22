package gitoscope.controller;

import gitoscope.domain.BrowseableObject;
import gitoscope.domain.Project;
import gitoscope.service.ProjectService;
import gitoscope.service.TreeBrowserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TreeBrowserController {

    private ProjectService projectService;
    private TreeBrowserService treeBrowserService;

    @RequestMapping(value = "/projects/{projectName}.git/trees/{treeId}")
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

    @RequestMapping(value = "/projects/{projectName}.git/blobs/{blobId}")
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

    @SuppressWarnings("unused")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @SuppressWarnings("unused")
    public void setTreeBrowserService(TreeBrowserService treeBrowserService) {
        this.treeBrowserService = treeBrowserService;
    }
}
