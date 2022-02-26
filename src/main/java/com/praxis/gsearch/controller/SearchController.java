package com.praxis.gsearch.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.customsearch.v1.CustomSearchAPI;
import com.google.api.services.customsearch.v1.model.Result;
import com.google.api.services.customsearch.v1.model.Search;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.praxis.gsearch.constant.ApiConstants;
import com.praxis.gsearch.model.SearchResult;
import com.praxis.gsearch.model.SearchRequest;
import com.praxis.gsearch.model.ApiRequestError;
import com.praxis.gsearch.exceptions.InvalidApiRequestException;

@RestController
@RequestMapping("/search")
public class SearchController {
    @RequestMapping(method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> search(@RequestBody SearchRequest searchRequest) {
        List<SearchResult> searchResult = null;
        try {
            searchResult = this.filterResult(this.customSearch(searchRequest.keyword));
        } catch (InvalidApiRequestException e) {
            return ResponseEntity.status(e.apiRequestError.getCode()).body(e.apiRequestError.getError());
        }
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

    private List<Result> customSearch(String keyword) throws InvalidApiRequestException {
        CustomSearchAPI customSearch = null;
        try {
            customSearch = new CustomSearchAPI(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    new GsonFactory(),
                    new HttpRequestInitializer() {
                        @Override
                        public void initialize(HttpRequest httpRequest) {
                            try {
                                httpRequest.setConnectTimeout(ApiConstants.HTTP_TIMEOUT);
                                httpRequest.setReadTimeout(ApiConstants.HTTP_TIMEOUT);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Result> resultList = null;
        try {
            ApiConstants apiConstants = ApiConstants.context.getBean(ApiConstants.class);

            CustomSearchAPI.Cse.List list = customSearch.cse().list();
            list.setQ(keyword);
            list.setNum(ApiConstants.SEARCH_ITEM_COUNT);
            list.setKey(apiConstants.getGoogleApiKey());
            list.setCx(apiConstants.getGoogleSearchId());
            Search results = list.execute();
            resultList = results.getItems();
        } catch (GoogleJsonResponseException e) {
            throw new InvalidApiRequestException(
                    new ApiRequestError(e.getDetails().getCode(), e.getDetails().getMessage())
                );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private List<SearchResult> filterResult(List<Result> result) {
        List<SearchResult> searchResult = new ArrayList<SearchResult>();
        result.forEach(item ->
            searchResult.add(
                    new SearchResult(
                            item.get("link").toString(),
                            item.get("snippet").toString(),
                            item.get("title").toString()
                        ))
        );
        return searchResult;
    }
}
