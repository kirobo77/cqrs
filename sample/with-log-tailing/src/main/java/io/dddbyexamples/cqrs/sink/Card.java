package io.dddbyexamples.cqrs.sink;

class Card {

    private String id;
    private String used_limit;

    long getUsed_limit() {
        return Long.parseLong(used_limit);
    }

    void setUsed_limit(String used_limit) {
        this.used_limit = used_limit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
