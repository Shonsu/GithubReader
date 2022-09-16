package pl.shonsu.GithubReader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import pl.shonsu.GithubReader.exceptions.GHNotFoundException;
import pl.shonsu.GithubReader.model.GHBranch;
import pl.shonsu.GithubReader.model.GHRepository;
import pl.shonsu.GithubReader.model.GHUser;
import pl.shonsu.GithubReader.model.SimpleBranch;
import pl.shonsu.GithubReader.model.SimpleRepository;
import reactor.core.publisher.Mono;

@Service
public class GithubService {

    private static final String TOKEN = "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";
    
    @Autowired
    private WebClient webClient;

    // https://api.github.com/repos/OWNER/REPO/branches
    public List<GHBranch> getBranchesForUserRepository(String userName, String repository) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/repos/{userName}/{repository}/branches")
                        .build(userName, repository))
                .headers(headers -> headers.setBearerAuth(TOKEN))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> Mono.error(
                                new GHNotFoundException("Not found")))
                .bodyToMono(new ParameterizedTypeReference<List<GHBranch>>() {
                }).block();

    }

    public GHUser getUser(String userName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/{userName}")
                        .build(userName))
                .headers(headers -> headers.setBearerAuth(TOKEN))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> Mono.error(
                                new GHNotFoundException("Username not found")))
                .bodyToMono(GHUser.class)
                .block();

    }

    public List<GHRepository> getReposByUserName(String userName) {
        List<GHRepository> ghRepository = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{userName}/repos").build(userName))
                .headers(headers -> headers.setBearerAuth(TOKEN))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> Mono.error(
                                new GHNotFoundException("Username not found")))
                .bodyToMono(new ParameterizedTypeReference<List<GHRepository>>() {
                }).block();
        return ghRepository;
    }

    public List<GHRepository> getReposNoFork(List<GHRepository> ghRepository) {
        return ghRepository.stream()
                .filter(repo -> !repo.getFork())
                .map(repo -> repo)
                .toList();

    }

    public List<SimpleBranch> createSimpleBranches(List<GHBranch> ghBranches) {
        return ghBranches.stream()
                .map(branch -> new SimpleBranch(branch.getName(), branch.getCommit().getSha()))
                .toList();
    }

    public List<SimpleRepository> createUserSimpleRepos(List<GHRepository> ghRepository) {

        List<SimpleRepository> simpleRepositories = ghRepository.stream()
                .filter(repo -> !repo.getFork())
                .map(repo -> new SimpleRepository(repo.getName(), repo.getOwner().getLogin(),
                        createSimpleBranches(
                                getBranchesForUserRepository(
                                        repo.getOwner().getLogin(),
                                        repo.getName()))))
                .toList();
        return simpleRepositories;
    }
}
