package pl.shonsu.GithubReader.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GHUser {
    public String login;
    public Integer id;
    public String node_id;
    public String avatar_url;
    public String gravatar_id;
    public String url;
    public String html_url;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String starred_url;
    public String subscriptions_url;
    public String organizations_url;
    public String repos_url;
    public String events_url;
    public String received_events_url;
    public String type;
    public Boolean site_admin;
    public String name;
    public String company;
    public String blog;
    public String location;
    public String email;
    public Boolean hireable;
    public String bio;
    public String twitter_username;
    public Integer public_repos;
    public Integer public_gists;
    public Integer followers;
    public Integer following;
    public String created_at;
    public String updated_at;
}
