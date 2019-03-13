package com.gm.gant1213.domain;

public class article {
    private String title;
    private String text;



    public article() {
    }

    public article(String text) {
        this.text = text;
    }

    public article(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof article)) return false;

        article article = (article) o;

        if (getTitle() != null ? !getTitle().equals( article.getTitle() ) : article.getTitle() != null) return false;
        return getText() != null ? getText().equals( article.getText() ) : article.getText() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "article{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
