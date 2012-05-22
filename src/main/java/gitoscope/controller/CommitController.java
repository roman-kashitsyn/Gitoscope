package gitoscope.controller;

import gitoscope.domain.Commit;
import gitoscope.domain.Project;
import gitoscope.service.CommitService;
import gitoscope.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class CommitController {

    private CommitService commitService;
    private ProjectService projectService;

    @RequestMapping(value = "/projects/{projectName}.git/commits/{commitId}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/projects/{projectName}.git/commits/search.html")
    public ModelAndView search(
            @PathVariable String projectName,
            @RequestParam(value = "max", required = false) Integer max,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "committer", required = false) String committer,
            @RequestParam(value = "message", required = false) String message,
            @RequestParam(value = "start", required = false) String start) {
        Project project = projectService.findProjectByName(projectName);
        int maxResults = (max != null) ? max : 0;
        Map<String, String> filterMap = new TreeMap<String, String>();
        if (!nullOrEmpty(message)) filterMap.put("message", message);
        if (!nullOrEmpty(start)) filterMap.put("start", start);
        if (!nullOrEmpty(author)) filterMap.put("author", author);
        if (!nullOrEmpty(committer)) filterMap.put("committer", committer);

        List<Commit> commits = commitService.searchCommits(project, maxResults, filterMap);

        ModelAndView modelAndView = new ModelAndView("commit.search");
        modelAndView.addObject("project", project);
        modelAndView.addObject("title", project.getName());
        modelAndView.addObject("commits", commits);
        return modelAndView;
    }

    @SuppressWarnings("unused")
    public void setCommitService(CommitService commitService) {
        this.commitService = commitService;
    }

    @SuppressWarnings("unused")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    private boolean nullOrEmpty(String param) {
        return param == null || param.trim().length() == 0;
    }
}
