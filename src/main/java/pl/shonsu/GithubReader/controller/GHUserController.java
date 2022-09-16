package pl.shonsu.GithubReader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.shonsu.GithubReader.model.GHRepository;
import pl.shonsu.GithubReader.model.GHUser;
import pl.shonsu.GithubReader.model.SimpleRepository;
import pl.shonsu.GithubReader.service.GithubService;

@RestController
public class GHUserController {

        @Autowired
        private GithubService githubService;

        @GetMapping("/githubuser/{userName}")
        public GHUser getGHUser(@PathVariable String userName) {

                GHUser ghUser = githubService.getUser(userName);
                return ghUser;
        }

        @GetMapping("githubuser/{userName}/repos")
        public List<GHRepository> getGHRepositoriesByUserName(@PathVariable String userName) {

                List<GHRepository> ghRepository = githubService.getReposByUserName(userName);
                return ghRepository;
        }

        // https://api.github.com/repos/OWNER/REPO/branches
        @GetMapping("githubuser/{userName}/simplerepos")
        public List<SimpleRepository> getUserSimpleRepositories(@PathVariable String userName) {

                return githubService.getUserSimpleRepos(userName);
        }

}
