package com.example.sb_neo4j.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class GraphQLService {

    private final WebClient webClient;

    public GraphQLService(@Value("${github.token}") String githubToken) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.github.com/graphql")
                .defaultHeader("Authorization", "Bearer " + githubToken)
                .build();
    }

    public Mono<Map> getProjectIssues(String owner, String repo, int projectNumber) {
        String query = """
            query GetProjectIssues($owner: String!, $repo: String!, $projectNumber: Int!) {
              repository(owner: $owner, name: $repo) {
                project(number: $projectNumber) {
                  name
                  columns(first: 10) {
                    nodes {
                      name
                      cards(first: 100) {
                        nodes {
                          content {
                            ... on Issue {
                              title
                              url
                              number
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            """;

        Map<String, Object> variables = Map.of(
                "owner", owner,
                "repo", repo,
                "projectNumber", projectNumber
        );

        Map<String, Object> requestBody = Map.of(
                "query", query,
                "variables", variables
        );

        return webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class);
    }
}