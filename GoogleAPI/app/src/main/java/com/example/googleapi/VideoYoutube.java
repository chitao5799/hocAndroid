package com.example.googleapi;

public class VideoYoutube {
    private String title,thumbnail,idVideo;

    public VideoYoutube(String title, String thumbnail, String idVideo) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.idVideo = idVideo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }
}
