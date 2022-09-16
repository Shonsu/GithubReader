package pl.shonsu.GithubReader.model;

import java.util.List;

public record SimpleRepository(String repositoryName,
        String ownerLogin,
        List<SimpleBranch> branch) {

}
