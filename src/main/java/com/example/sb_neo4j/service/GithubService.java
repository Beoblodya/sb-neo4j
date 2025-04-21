package com.example.sb_neo4j.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class GithubService {

    private final WebClient webClient;

    public GithubService(@Value("${github.token}") String githubToken) {
            this.webClient = WebClient.builder()
                .baseUrl("https://api.github.com/graphql")
//                .defaultHeader("Authorization", "Bearer "+githubToken)
                .build();
    }

    public Mono<Map> getCollaborators(String owner, String repo, String patToken){
        String query = """
                    query GetProjectV2Items($owner: String!, $repo: String!) {
                      repository(owner: $owner, name: $repo) {
                        collaborators(first: 100) {
                          edges {
                            node {
                              login
                            }
                            permission
                          }
                        }
                      }
                    }
                    """;
        Map<String, Object> variables = Map.of(
                "owner", owner,
                "repo", repo
        );
        return executeQuery(query, variables, patToken);
    }

    public Mono<Map> getIssues(String owner, String repo, int projectNumber, String patToken){
        String query = """
                    query GetProjectV2Items($owner: String!, $repo: String!, $projectNumber: Int!) {
                       repository(owner: $owner, name: $repo) {
                         projectV2(number: $projectNumber) {
                           title
                           items(first: 20) {
                             nodes {
                               content {
                                 ... on Issue {
                                   title
                                   body
                                   state
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
        return executeQuery(query, variables, patToken);
    }

    private Mono<Map> executeQuery(String query, Map<String, Object> variables, String patToken){
        Map<String, Object> requestBody = Map.of(
                "query", query,
                "variables", variables
        );
        return webClient.post()
                .bodyValue(requestBody)
                .header("Authorization", "Bearer "+patToken)
                .retrieve()
                .bodyToMono(Map.class);
    }
}