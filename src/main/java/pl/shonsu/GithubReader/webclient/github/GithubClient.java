package pl.shonsu.GithubReader.webclient.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.shonsu.GithubReader.model.*;

import java.util.List;

@Component
public class GithubClient {

    @Autowired
    private WebClient webClient;

    public List<GHBranch> getBranchesForUserRepository(String userName, String repository) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/repos/{userName}/{repository}/branches")
                        .build(userName, repository))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<GHBranch>>() {
                }).block();

    }

    public GHUser getUser(String userName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{userName}")
                        .build(userName))
                .retrieve()
                .bodyToMono(GHUser.class)
                .block();

    }

    public List<GHRepository> getReposByUserName(String userName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{userName}/repos")
                        .build(userName))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<GHRepository>>() {
                }).block();
    }

    public List<GHRepository> getReposNoFork(List<GHRepository> ghRepository) {
        return ghRepository.stream()
                .filter(repo -> !repo.getFork())
                .toList();

    }

    public List<SimpleBranch> createSimpleBranches(List<GHBranch> ghBranches) {
        return ghBranches.stream()
                .map(branch -> new SimpleBranch(branch.getName(), branch.getCommit().getSha()))
                .toList();
    }

    public List<SimpleRepository> createUserSimpleRepos(List<GHRepository> ghRepository) {

        return ghRepository.stream()
                .filter(repo -> !repo.getFork())
                .map(repo -> new SimpleRepository(repo.getName(), repo.getOwner().getLogin(),
                        createSimpleBranches(
                                getBranchesForUserRepository(
                                        repo.getOwner().getLogin(),
                                        repo.getName()))))
                .toList();
    }
}