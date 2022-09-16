package pl.shonsu.GithubReader.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GHPermissions {
    public Boolean admin;
    public Boolean push;
    public Boolean pull;
}
