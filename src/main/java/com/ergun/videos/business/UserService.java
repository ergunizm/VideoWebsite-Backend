package com.ergun.videos.business;

import com.ergun.videos.entities.User;
import com.ergun.videos.entities.Video;

import java.util.List;

public interface UserService {
    User login(String username,String password);
    void add(User user);
    List<User> getAllAllowed();
    void addVideoToUser(int videoId);
    void deleteVideoFromUser(int videoId);
    User getById(int id);
    List<Video> getFavoriteVideos(int id);
}
