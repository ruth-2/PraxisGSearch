package com.praxis.gsearch.model;

public class SearchResult {
    public String link;
    public String snippet;
    public String title;

    public SearchResult(String _link, String _snippet, String _title) {
        this.link = _link;
        this.snippet = _snippet;
        this.title = _title;
    }
}