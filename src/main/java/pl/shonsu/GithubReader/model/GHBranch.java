package pl.shonsu.GithubReader.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GHBranch {

    public String name;
    public Commit commit;
    public Boolean _protected;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Commit {

        public String sha;
        public String url;

    }
}
