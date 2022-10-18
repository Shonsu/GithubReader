package pl.shonsu.GithubReader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.shonsu.GithubReader.model.GHBranch;
import pl.shonsu.GithubReader.model.GHRepository;
import pl.shonsu.GithubReader.model.GHUser;
import pl.shonsu.GithubReader.model.SimpleRepository;
import pl.shonsu.GithubReader.service.GithubService;

@RestController
public class GHUserController {

        @Autowired
        private GithubService githubService;

        @ResponseStatus(HttpStatus.OK)
        @GetMapping("/githubuser/{userName}")
        public GHUser getGHUser(@PathVariable String userName) {
                return githubService.getUser(userName);
        }

        @ResponseStatus(HttpStatus.OK)
        @GetMapping("githubuser/{userName}/repos")
        public List<GHRepository> getGHRepositoriesByUserName(@PathVariable String userName) {

                return githubService.getReposByUserName(userName);
        }

        @ResponseStatus(HttpStatus.OK)
        @GetMapping("githubuser/{userName}/{repository}")
        public List<GHBranch> getBranchesForUserRepository(@PathVariable String userName,
                        @PathVariable String repository) {

                return githubService.getBranchesForUserRepository(userName, repository);
        }

        @ResponseStatus(HttpStatus.OK)
        @GetMapping("githubuser/{userName}/simplerepos")
        public List<SimpleRepository> getUserSimpleRepositoriesNoFork(@PathVariable String userName) {

                List<GHRepository> ghRepositories = githubService
                                .getReposNoFork(githubService.getReposByUserName(userName));

                return githubService.createUserSimpleRepos(ghRepositories);
        }

}
