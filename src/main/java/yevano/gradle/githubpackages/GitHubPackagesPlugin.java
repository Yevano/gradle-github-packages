package yevano.gradle.githubpackages;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

class GitHubPackagesPlugin implements Plugin<Project> {
    public void apply(Project project) {
        String username = (String) project.property("GITHUB_USERNAME");
        String password = (String) project.property("GITHUB_TOKEN");
        project.getExtensions().add("gitPackage", new GitHubPackagesRepoExtension(username, password));
    }
}
