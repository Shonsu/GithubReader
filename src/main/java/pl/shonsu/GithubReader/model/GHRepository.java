package pl.shonsu.GithubReader.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GHRepository {
    public Integer id;
    public String node_id;
    public String name;
    public String full_name;
    public SimpleGHUser owner;
    public Boolean _private;
    public String html_url;
    public String description;
    public Boolean fork;
    public String url;
    public String archive_url;
    public String assignees_url;
    public String blobs_url;
    public String branches_url;
    public String collaborators_url;
    public String comments_url;
    public String commits_url;
    public String compare_url;
    public String contents_url;
    public String contributors_url;
    public String deployments_url;
    public String downloads_url;
    public String events_url;
    public String forks_url;
    public String git_commits_url;
    public String git_refs_url;
    public String git_tags_url;
    public String git_url;
    public String issue_comment_url;
    public String issue_events_url;
    public String issues_url;
    public String keys_url;
    public String labels_url;
    public String languages_url;
    public String merges_url;
    public String milestones_url;
    public String notifications_url;
    public String pulls_url;
    public String releases_url;
    public String ssh_url;
    public String stargazers_url;
    public String statuses_url;
    public String subscribers_url;
    public String subscription_url;
    public String tags_url;
    public String teams_url;
    public String trees_url;
    public String clone_url;
    public String mirror_url;
    public String hooks_url;
    public String svn_url;
    public String homepage;
    public Object language;
    public Integer forks_count;
    public Integer stargazers_count;
    public Integer watchers_count;
    public Integer size;
    public String default_branch;
    public Integer open_issues_count;
    public Boolean is_template;
    public List<String> topics = null;
    public Boolean has_issues;
    public Boolean has_projects;
    public Boolean has_wiki;
    public Boolean has_pages;
    public Boolean has_downloads;
    public Boolean archived;
    public Boolean disabled;
    public String visibility;
    public String pushed_at;
    public String created_at;
    public String updated_at;
    public GHPermissions permissions;
    public Object template_repository;

}
