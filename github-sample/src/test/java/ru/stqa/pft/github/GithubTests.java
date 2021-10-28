package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import javax.management.ImmutableDescriptor;
import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("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");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("naumovamv", "java_pft")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}