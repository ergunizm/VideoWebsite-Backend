package com.ergun.videos.business;

import com.ergun.videos.entities.Video;

import java.util.List;

public interface VideoService {
    List<Video> getAll();
    void save(Video video);
    void delete(int id);
    Video getById(int id);
    List<Video> getByTitle(String name);
}
