package pl.shonsu.GithubReader.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.shonsu.GithubReader.model.GHBranch;
import pl.shonsu.GithubReader.model.GHRepository;
import pl.shonsu.GithubReader.model.GHUser;
import pl.shonsu.GithubReader.model.SimpleRepository;
import pl.shonsu.GithubReader.webclient.github.GithubClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final GithubClient githubClient;


    public GHUser getUser(String username) {
        return githubClient.getUser(username);
    }

    public List<GHRepository> getReposByUserName(String username) {
        return githubClient.getReposByUserName(username);
    }

    public List<GHBranch> getBranchesForUserRepository(String username, String repository) {
        return githubClient.getBranchesForUserRepository(username, repository);
    }

    public List<SimpleRepository> createUserSimpleRepos(List<GHRepository> ghRepositories) {
        return githubClient.createUserSimpleRepos(ghRepositories);
    }

    public List<GHRepository> getReposNoFork(List<GHRepository> username) {
        return  githubClient.getReposNoFork(username);
    }
}
