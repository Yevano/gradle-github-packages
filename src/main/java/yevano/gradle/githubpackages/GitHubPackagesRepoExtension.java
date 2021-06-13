package yevano.gradle.githubpackages;

import java.net.URI;
import java.net.URISyntaxException;

import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;

public class GitHubPackagesRepoExtension {
    private String username;
    private String password;
    String repo = null;

    public GitHubPackagesRepoExtension(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MavenArtifactRepository gitPackage(RepositoryHandler handler) {
        return handler.maven(repo -> {
            try {
                if(repo == null) {
                    throw new RuntimeException("Property `repo` was not specified.");
                }

                repo.setUrl(new URI("https://maven.pkg.github.com/" + repo));
                repo.setName("GitHubPackages");

                repo.credentials(cred -> {
                    cred.setUsername(this.username);
                    cred.setPassword(this.password);
                });
            } catch (URISyntaxException e) {
                throw new RuntimeException("Specified repository violated the URI syntax.");
            }
        });
    }
}
