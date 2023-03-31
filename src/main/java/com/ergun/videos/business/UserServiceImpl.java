package com.ergun.videos.business;

import com.ergun.videos.core.utilities.exceptions.UserException;
import com.ergun.videos.dataAccess.UserRepository;
import com.ergun.videos.dataAccess.VideoRepository;
import com.ergun.videos.entities.User;
import com.ergun.videos.entities.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private VideoRepository videoRepository;
    private User currentUser;
    public UserServiceImpl(UserRepository userRepository,VideoRepository videoRepository){
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserException("There's no such user!");
        }
        if(! user.getPassword().equals(password)){
            throw new UserException("Wrong password!");
        }
        currentUser = user;

        return user;
    }

    @Override
    public void add(User user) {
        if(user.getUsername() == null){
            throw new UserException("Username must have at least 8 characters!");
        }else if(user.getEmail() == null){
            throw new UserException("Invalid email!");
        }else if(user.getPassword() == null){
            throw new UserException("Passwords must have at least 8 characters!");
        }

        if(user.getUsername().length() <8){
            throw new UserException("Username must have at least 8 characters!");
        }else if(! user.getEmail().contains("@")){
            throw new UserException("Invalid email!");
        }else if(user.getPassword().length() < 8){
            throw new UserException("Passwords must have at least 8 characters!");
        }

        if(userRepository.findByUsername(user.getUsername()) != null){
            throw new UserException("username exists");
        }else if(userRepository.findByEmail(user.getEmail()) != null){
            throw new UserException("email exists");
        }else{
            userRepository.save(user);
        }
    }

    @Override
    public List<User> getAllAllowed() {
        return userRepository.findByAllowNotificationTrue();
    }

    @Override
    public void addVideoToUser(int videoId) {
        if(! currentUser.equals(null)) {
            if (videoRepository.findById(videoId) != null) {
                Video fav = videoRepository.findById(videoId).orElseThrow();
                if(! currentUser.getFavorites().contains(fav)){
                    currentUser.addFavoriteVideo(fav);
                    userRepository.save(currentUser);
                }
            }
        }
    }

    @Override
    public void deleteVideoFromUser(int videoId) {
        if(! currentUser.equals(null)) {
            if (videoRepository.findById(videoId) != null) {
                Video fav = videoRepository.findById(videoId).orElseThrow();
                currentUser.deleteFavoriteVideo(fav);
                userRepository.save(currentUser);
            }
        }
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Video> getFavoriteVideos(int id) {
        return userRepository.findById(id).getFavorites();
    }
}
